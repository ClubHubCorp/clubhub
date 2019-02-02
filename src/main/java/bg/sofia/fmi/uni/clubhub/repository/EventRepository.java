package bg.sofia.fmi.uni.clubhub.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.sofia.fmi.uni.clubhub.entity.EventEntity;

import javax.swing.text.html.Option;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
	
	List<EventEntity> findByNameIgnoreCaseContaining(String word);

	List<EventEntity> findAllByClubId(UUID clubId);
}
