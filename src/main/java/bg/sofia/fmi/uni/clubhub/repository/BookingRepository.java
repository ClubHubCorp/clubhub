package bg.sofia.fmi.uni.clubhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    List<BookingEntity> findAllByCustomerId(UUID customerId);

    void deleteByIdAndCustomerId(UUID id, UUID customerID);
}
