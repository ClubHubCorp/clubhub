package bg.sofia.fmi.uni.clubhub.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.sofia.fmi.uni.clubhub.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
	
	@Query("Select e from EVENT e where e.name like %:word%")
	List<EventEntity> findByNameContaining(String word);
}
