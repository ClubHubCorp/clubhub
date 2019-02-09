package bg.sofia.fmi.uni.clubhub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity.AttendanceStatus;
import bg.sofia.fmi.uni.clubhub.model.Booking;

public interface IBookingService {

    Booking createNew(Booking booking);

    Optional<Booking> getByIdForCustomer(UUID id, UUID customerId);

    List<Booking> getAllForCustomer(UUID customerId);

    List<Booking> getAllForCustomerByStatus(UUID customerId, AttendanceStatus status);

    Optional<Booking> cancelByIdForCustomer(UUID id, UUID customerId);


}
