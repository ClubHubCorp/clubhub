package bg.sofia.fmi.uni.clubhub.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.sofia.fmi.uni.clubhub.entity.DrinkEntity;

@Repository
public interface DrinkRepository  extends JpaRepository<DrinkEntity, UUID>{

}
