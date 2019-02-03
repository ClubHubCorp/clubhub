package bg.sofia.fmi.uni.clubhub.repository;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.enums.DrinkType;
import bg.sofia.fmi.uni.clubhub.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.sofia.fmi.uni.clubhub.entity.DrinkEntity;

@Repository
public interface DrinkRepository  extends JpaRepository<DrinkEntity, UUID> {

    List<DrinkEntity> findAllByType(DrinkType type);

    List<DrinkEntity> findAllByClubId(UUID club_id);
}
