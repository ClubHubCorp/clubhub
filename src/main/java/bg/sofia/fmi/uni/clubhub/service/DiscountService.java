package bg.sofia.fmi.uni.clubhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.repository.DiscountRepository;

@Service
public class DiscountService implements IDiscountService{
	private final DiscountRepository discountRepository;

	@Autowired
	public DiscountService(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}
}
