package bg.sofia.fmi.uni.clubhub.controller;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.enums.DrinkType;
import bg.sofia.fmi.uni.clubhub.model.Drink;
import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.service.IDrinkService;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.resolve;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController("drinks")
public class DrinkController {
    private final IDrinkService drinkService;

    @Autowired
    public DrinkController(IDrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Drink> createNew(@Valid @RequestBody Drink drink) {
        return status(CREATED).body(drinkService.createNew(drink));
    }

    @GetMapping

    public ResponseEntity<List<Drink>> getDrinksByClubId(@RequestParam("clubId") UUID clubId) {
        return ok(drinkService.getAllDrinksByClubId(clubId));
    }


    @GetMapping("type/{type}")
    public ResponseEntity<List<Drink>> getAllDrinksByType(@PathVariable DrinkType type) {
        return ok(drinkService.getAllDrinksByType(type));
    }

    @GetMapping("{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable UUID id) {
        Optional<Drink> optionalDrink = drinkService.getById(id);
        if (!optionalDrink.isPresent()) {
            notFound().build();
        }
        return ok(optionalDrink.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteDrink(@PathVariable UUID id) {
        drinkService.delete(id);
        return ok().build();
    }
}