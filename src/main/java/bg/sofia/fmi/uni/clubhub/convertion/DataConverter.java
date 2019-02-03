package bg.sofia.fmi.uni.clubhub.convertion;

import bg.sofia.fmi.uni.clubhub.entity.*;
import bg.sofia.fmi.uni.clubhub.model.*;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.SetUtils.emptyIfNull;

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
                entity.getId(), entity.getUsername(), //
                entity.getPassword(), //
                entity.getEmail(), //
                entity.getFirstName(), //
                entity.getLastName(), //
                entity.getAge(), //
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
                emptyIfNull(model.getBookings()).stream().map(DataConverter::toEntity).collect(toSet()) //
        );
    }

    public static Event toModel(EventEntity entity) {
        return new Event( //
                entity.getEntity_id(), //
                toModel(entity.getClub()), //
                entity.getName(), //
                entity.getDate(), //
                entity.getDescription());
    }

    public static EventEntity toEntity(Event model) {
        return new EventEntity( //
                model.getEntity_id(), //
                model.getName(), //
                model.getDate(), //
                model.getDescription(), //
                toEntity(model.getClub()));
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
                emptyIfNull(entity.getEvents()));

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
                emptyIfNull(model.getEvents()));
    }

    public static RatingEntity toEntity(Rating model) {
        return new RatingEntity(//
                model.getId(), //
                model.getClubId(), //
                model.getScore(), //
                model.getComment());
    }

    public static Rating toModel(RatingEntity entity) {
        return new Rating(//
                entity.getId(), //
                entity.getClubId(), //
                entity.getScore(), //
                entity.getComment());
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

    public static DrinkEntity toEntity(Drink model){
        return new DrinkEntity (
                model.getId(), model.getName(), convertToBlob(model.getPicture()).get(),
                model.getType(), model.getDescription(), null
        );
    }

    private static Optional<Blob> convertToBlob(byte[] bytes) {
        Optional<Blob>  blob = Optional.empty();
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
