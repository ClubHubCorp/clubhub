package bg.sofia.fmi.uni.clubhub.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, UUID>{
	List<RatingEntity> findAllByclubId(UUID clubId);
}
