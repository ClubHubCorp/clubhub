package bg.sofia.fmi.uni.clubhub.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.sofia.fmi.uni.clubhub.model.Discount;
import bg.sofia.fmi.uni.clubhub.service.IDiscountService;

@RestController
@RequestMapping(value = "discounts")
public class DiscountController {
	
	private final IDiscountService discountService;
	
	@Autowired
	public DiscountController(IDiscountService discountService) {
		this.discountService = discountService;
	}
	
	@GetMapping(value = "{clubId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discount>> getClubDiscountss(@PathVariable("clubId") UUID id) {
       List<Discount> discounts = discountService.getAllDiscountForClub(id);

        return ok(discounts);
    }
    
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discount>> getAllDiscounts() {
    	List<Discount> discounts = discountService.getAllDiscounts();

        return ok(discounts);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> createRating(@Valid @RequestBody Discount discount) {
        return status(CREATED).body(discountService.createNew(discount));
    }
}
