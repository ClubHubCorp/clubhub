package bg.sofia.fmi.uni.clubhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;

@Repository
public interface ClubRepository extends CrudRepository<ClubEntity, UUID> {

}
