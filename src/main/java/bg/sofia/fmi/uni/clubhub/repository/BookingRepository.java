package bg.sofia.fmi.uni.clubhub.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.entity.BookingEntity.AttendanceStatus;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    List<BookingEntity> findAllByCustomerId(UUID customerId);

    Optional<BookingEntity> findByIdAndCustomerId(UUID id, UUID customerId);

    List<BookingEntity> findByCustomerIdAndAttendanceStatus(UUID customerId, AttendanceStatus attendanceStatus);

    void deleteByIdAndCustomerId(UUID id, UUID customerID);
}
