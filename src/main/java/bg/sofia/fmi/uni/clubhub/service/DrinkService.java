package bg.sofia.fmi.uni.clubhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.repository.DrinkRepository;

@Service
public class DrinkService implements IDrinkService{
	private final DrinkRepository drinkRepository;

	@Autowired
	public DrinkService(DrinkRepository drinkRepository) {
		this.drinkRepository = drinkRepository;
	}
}
