package bg.sofia.fmi.uni.clubhub.service;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Discount;

public interface IDiscountService {
	Discount createNew(Discount discount);
	List<Discount> getAllDiscountForClub(UUID clubId);
	List<Discount> getAllDiscounts();
}
