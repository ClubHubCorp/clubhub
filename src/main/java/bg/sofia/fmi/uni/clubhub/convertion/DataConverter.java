package bg.sofia.fmi.uni.clubhub.convertion;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.SetUtils.emptyIfNull;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.CustomerEntity;
import bg.sofia.fmi.uni.clubhub.entity.DiscountEntity;
import bg.sofia.fmi.uni.clubhub.entity.DrinkEntity;
import bg.sofia.fmi.uni.clubhub.entity.EventEntity;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import bg.sofia.fmi.uni.clubhub.entity.SubscriptionEntity;
import bg.sofia.fmi.uni.clubhub.model.Booking;
import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.model.Discount;
import bg.sofia.fmi.uni.clubhub.model.Drink;
import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.model.Rating;
import bg.sofia.fmi.uni.clubhub.model.Subscription;

public class DataConverter {

    public static Booking toModel(BookingEntity entity) {
        return new Booking( //
                entity.getId(), //
                entity.getCustomer().getId(), //
                entity.getClub().getId(), //
                entity.getCountOfPeople(), //
                entity.getDate(), //
                entity.getLeaderboardPoints(), //
                entity.getOverallPrice(), //
                entity.getAttendanceStatus() //
        );
    }

    public static BookingEntity toEntity(Booking model) {
        return new BookingEntity( //
                model.getId(), //
                null, //
                null, model.getCountOfPeople(), //
                model.getDate(), //
                model.getLeaderboardPoints(), //
                model.getOverallPrice(), //
                model.getAttendanceStatus() //
        );
    }

    public static Customer toModel(CustomerEntity entity) {
        return new Customer( //
                entity.getId(), //
                entity.getUsername(), //
                entity.getPassword(), //
                entity.getEmail(), //
                entity.getFirstName(), //
                entity.getLastName(), //
                entity.getAge(), //
                entity.getLeaderboardPoints(), //,
                emptyIfNull(entity.getBookings()).stream().map(DataConverter::toModel).collect(toSet()) //
        );
    }

    public static CustomerEntity toEntity(Customer model) {
        return new CustomerEntity( //
                model.getId(), //
                model.getUsername(), //
                model.getPassword(), //
                model.getEmail(), //
                model.getFirstName(), //
                model.getLastName(), //
                model.getAge(), //
                model.getLeaderboardPoints() == null ? 0 : model.getLeaderboardPoints(), //
                emptyIfNull(model.getBookings()).stream().map(DataConverter::toEntity).collect(toSet()) //
        );
    }

    public static Event toModel(EventEntity entity) {
        return new Event( //
                entity.getId(), //
                entity.getClub().getId(), //
                entity.getName(), //
                entity.getDate(), //
                entity.getDescription());
    }

    public static EventEntity toEntity(Event model) {
        return new EventEntity( //
                model.getId(), //
                model.getName(), //
                model.getDate(), //
                model.getDescription(), //
                null);
    }

    public static Club toModel(ClubEntity entity) {
        return new Club( //
                entity.getId(), //
                entity.getUsername(), //
                entity.getPassword(), //
                entity.getEmail(), //
                entity.getAddress(), //
                entity.getCapacity(), //
                entity.getEntranceFee(), //
                emptyIfNull(entity.getBookings()), //
                emptyIfNull(entity.getRatings()), //
                emptyIfNull(entity.getEvents()), //
                emptyIfNull(entity.getDiscounts()));

    }

    public static ClubEntity toEntity(Club model) {
        return new ClubEntity( //
                model.getId(), //
                model.getUsername(), //
                model.getPassword(), //
                model.getEmail(), //
                model.getAddress(), //
                model.getCapacity(), //
                model.getEntranceFee(), //
                emptyIfNull(model.getBookings()), //
                emptyIfNull(model.getRatings()), //
                emptyIfNull(model.getEvents()), //
                emptyIfNull(model.getDiscounts()));
    }

    public static RatingEntity toEntity(Rating model) {
        return new RatingEntity(//
                model.getId(), //
                null, //
                model.getScore(), //
                model.getComment());
    }

    public static Rating toModel(RatingEntity entity) {
        return new Rating(//
                entity.getId(), //
                entity.getClub().getId(), //
                entity.getScore(), //
                entity.getComment());
    }

    public static DiscountEntity toEntity(Discount model) {
        return new DiscountEntity(//
                model.getId(),//
                null,//
                model.getStartDate(),//
                model.getEndDate(), //
                model.getThresholdPoints(), //
                model.getPercentOff());
    }

    public static Discount toModel(DiscountEntity entity) {
        return new Discount(//
                entity.getId(),//
                entity.getClub().getId(), //
                entity.getStartDate(), //
                entity.getEndDate(), //
                entity.getThresholdPoints(), //
                entity.getPercentOff());
    }

    public static SubscriptionEntity toEntity(CustomerEntity customer, ClubEntity club, Date date) {
        return new SubscriptionEntity( //
                new SubscriptionEntity.SubscriptionId(customer, club), //
                date //
        );
    }

    public static Subscription toModel(SubscriptionEntity entity) {
        return new Subscription( //
                entity.getSubscriptionId().getCustomer().getId(), //
                entity.getSubscriptionId().getClub().getId(), //
                entity.getDate() //
        );
    }


    public static Drink toModel(DrinkEntity entity) {
        return new Drink(
                entity.getName(), handleBlob(entity.getPicture()),
                entity.getType(), entity.getDescription()
        );
    }

    private static byte[] handleBlob(Blob blob) {
        long length = 0;
        try {
            length = blob.length();
            return blob.getBytes(0, (int) length);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static DrinkEntity toEntity(Drink model) {
        return new DrinkEntity(
                model.getId(), model.getName(), convertToBlob(model.getPicture()).get(),
                model.getType(), model.getDescription(), null
        );
    }

    private static Optional<Blob> convertToBlob(byte[] bytes) {
        Optional<Blob> blob = Optional.empty();
        try {
            blob = Optional.of(new javax.sql.rowset.serial.SerialBlob(bytes));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return blob;
    }

    private DataConverter() {

    }
}
