package bg.sofia.fmi.uni.clubhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bg.sofia.fmi.uni.clubhub.repository.SubscriptionRepository;

@Service
public class SubscriptionService implements ISubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	@Autowired
	public SubscriptionService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

}
