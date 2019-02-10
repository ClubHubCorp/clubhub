package bg.sofia.fmi.uni.clubhub.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, UUID> {

    Optional<ClubEntity> findByUsername(String username);

}
