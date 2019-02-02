package bg.sofia.fmi.uni.clubhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.model.Booking;
import bg.sofia.fmi.uni.clubhub.repository.BookingRepository;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;
import static java.util.stream.Collectors.toList;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createNew(Booking booking, UUID customerId) {
        BookingEntity bookingEntity = toEntity(booking, customerId);
        bookingEntity.setId(UUID.randomUUID());

        return toModel(bookingRepository.save(bookingEntity));
    }

    @Override
    public Optional<Booking> getByIdForCustomer(UUID id, UUID customerId) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        if (!booking.isPresent() || !extractCustomerId(booking.get()).equals(customerId)) {
            return Optional.empty();
        }

        return booking.map(DataConverter::toModel);
    }

    @Override
    public List<Booking> getAllForCustomer(UUID customerId) {
        return bookingRepository.findAllByCustomerId(customerId).stream() //
                .map(DataConverter::toModel) //
                .collect(toList());
    }

    @Override
    public void deleteByIdForCustomer(UUID id, UUID customerId) {
        bookingRepository.deleteByIdAndCustomerId(id, customerId);
    }

    private static UUID extractCustomerId(BookingEntity booking) {
        return booking.getCustomer().getId();
    }
}
