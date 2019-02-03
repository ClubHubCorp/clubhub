package bg.sofia.fmi.uni.clubhub.service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.DrinkEntity;
import bg.sofia.fmi.uni.clubhub.enums.DrinkType;
import bg.sofia.fmi.uni.clubhub.model.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.repository.DrinkRepository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class DrinkService implements IDrinkService {
    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public Drink createNew(Drink drink) {
        DrinkEntity entity = DataConverter.toEntity(drink);
        entity.setId(UUID.randomUUID());
        drinkRepository.save(entity);
        return DataConverter.toModel(entity);
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll().stream().
                map(DataConverter::toModel).collect(Collectors.toList());
    }

    public Optional<Drink> getById(UUID id) {
        return drinkRepository.findById(id).map(DataConverter::toModel);
    }

    public void delete(UUID id) {
        drinkRepository.deleteById(id);
    }

    public List<Drink> getAllDrinksByType(DrinkType type) {
        return drinkRepository.findAllByType(type).stream().
                map(DataConverter::toModel).collect(Collectors.toList());
    }

    public List<Drink> getAllDrinksByClubId(UUID clubID) {
        return drinkRepository.findAllByClubId(clubID).stream().
                map(DataConverter::toModel).collect(Collectors.toList());
    }
}