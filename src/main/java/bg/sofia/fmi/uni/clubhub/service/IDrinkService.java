package bg.sofia.fmi.uni.clubhub.service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.DrinkEntity;
import bg.sofia.fmi.uni.clubhub.enums.DrinkType;
import bg.sofia.fmi.uni.clubhub.model.Drink;
import bg.sofia.fmi.uni.clubhub.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public interface IDrinkService {


    public Drink createNew(Drink drink);


    public List<Drink> getAllDrinks();


    public Optional<Drink> getById(UUID id) ;


    public void delete(UUID id);


    public List<Drink> getAllDrinksByType(DrinkType drinkType) ;

    public List<Drink> getAllDrinksByClubId(UUID clubID);
}
