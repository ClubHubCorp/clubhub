package bg.sofia.fmi.uni.clubhub.service;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Subscription;

public interface ISubscriptionService {

    Subscription createNew(Subscription subscription);

    List<Subscription> getSubscriptionsForCustomer(UUID customerId);
}
