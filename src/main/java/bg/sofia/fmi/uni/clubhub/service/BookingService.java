package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.entity.BookingEntity.AttendanceStatus;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.CustomerEntity;
import bg.sofia.fmi.uni.clubhub.model.Booking;
import bg.sofia.fmi.uni.clubhub.model.Discount;
import bg.sofia.fmi.uni.clubhub.repository.BookingRepository;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.CustomerRepository;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final ClubRepository clubRepository;
    private final CustomerRepository customerRepository;
    private final IDiscountService discountService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ClubRepository clubRepository, CustomerRepository customerRepository,
            IDiscountService discountService) {
        this.bookingRepository = bookingRepository;
        this.clubRepository = clubRepository;
        this.customerRepository = customerRepository;
        this.discountService = discountService;
    }

    @Override
    @Transactional
    public Booking createNew(Booking booking) {
        Optional<CustomerEntity> customer = customerRepository.findById(booking.getCustomerId());
        if (!customer.isPresent()) {
            throw new RuntimeException("No such customer found");
        }

        Optional<ClubEntity> club = clubRepository.findById(booking.getClubId());
        if (!club.isPresent()) {
            throw new RuntimeException("No such club found");
        }

        addDiscount(customer.get(), booking, club.get());

        BookingEntity bookingEntity = toEntity(booking);
        bookingEntity.setId(UUID.randomUUID());
        bookingEntity.setCustomer(customer.get());
        bookingEntity.setClub(club.get());

        return toModel(bookingRepository.save(bookingEntity));
    }

    private void addDiscount(CustomerEntity customer, Booking booking, ClubEntity club) {
        int customerPoints = customer.getLeaderboardPoints();
        customer.setLeaderboardPoints(customerPoints + booking.getLeaderboardPoints());
        customerRepository.save(customer);

        Optional<Discount> discount = discountService.getAllDiscountForClub(club.getId()).stream() //
                .filter(d -> customer.getLeaderboardPoints() >= d.getThresholdPoints()) //
                .max(comparingInt(Discount::getPercentOff));

        if (!discount.isPresent()) {
            return;
        }

        BigDecimal discountPercentage = new BigDecimal(discount.get().getPercentOff() / 100.0);
        BigDecimal discountedPrice = booking.getOverallPrice().multiply(discountPercentage) //
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);

        booking.setOverallPrice(booking.getOverallPrice().subtract(discountedPrice));
    }

    @Override
    public Optional<Booking> getByIdForCustomer(UUID id, UUID customerId) {
        return bookingRepository.findByIdAndCustomerId(id, customerId) //
                .map(DataConverter::toModel);
    }

    @Override
    public List<Booking> getAllForCustomer(UUID customerId) {
        return bookingRepository.findAllByCustomerId(customerId).stream() //
                .map(DataConverter::toModel) //
                .collect(toList());
    }

    @Override
    public List<Booking> getAllForCustomerByStatus(UUID customerId, AttendanceStatus status) {
        return bookingRepository.findByCustomerIdAndAttendanceStatus(customerId, status).stream() //
                .map(DataConverter::toModel) //
                .collect(toList());
    }

    @Override
    @Transactional
    public Optional<Booking> cancelByIdForCustomer(UUID id, UUID customerId) {
        return bookingRepository.findByIdAndCustomerId(id, customerId) //
                .map(this::cancelBooking) //
                .map(bookingRepository::save)
                .map(DataConverter::toModel);
    }

    private BookingEntity cancelBooking(BookingEntity entity) {
        entity.setAttendanceStatus(AttendanceStatus.CANCELED);
        return entity;
    }
}
