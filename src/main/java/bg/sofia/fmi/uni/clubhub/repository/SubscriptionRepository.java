package bg.sofia.fmi.uni.clubhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.SubscriptionEntity;
import bg.sofia.fmi.uni.clubhub.entity.SubscriptionEntity.SubscriptionId;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, SubscriptionId> {

    @Query(value = "SELECT * FROM subscription WHERE customer_id = :id", nativeQuery = true)
    List<SubscriptionEntity> findAllByCustomerId(UUID id);
}
