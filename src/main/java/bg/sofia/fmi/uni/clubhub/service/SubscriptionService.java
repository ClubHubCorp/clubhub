package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.CustomerEntity;
import bg.sofia.fmi.uni.clubhub.entity.SubscriptionEntity;
import bg.sofia.fmi.uni.clubhub.model.Subscription;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.CustomerRepository;
import bg.sofia.fmi.uni.clubhub.repository.SubscriptionRepository;

@Service
public class SubscriptionService implements ISubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ClubRepository clubRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, ClubRepository clubRepository,
            CustomerRepository customerRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.clubRepository = clubRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Subscription createNew(Subscription subscription) {
        Optional<CustomerEntity> customer = customerRepository.findById(subscription.getCustomerId());
        if (!customer.isPresent()) {
            throw new RuntimeException(format("Customer with id '%s' does not exist!", subscription.getCustomerId()));
        }

        Optional<ClubEntity> club = clubRepository.findById(subscription.getClubId());
        if (!club.isPresent()) {
            throw new RuntimeException(format("Club with id '%s' does not exist!", subscription.getClubId()));
        }

        SubscriptionEntity entity = toEntity(customer.get(), club.get(), new Date());
        return toModel(subscriptionRepository.save(entity));
    }

    @Override
    public List<Subscription> getSubscriptionsForCustomer(UUID customerId) {
        return subscriptionRepository.findAllByCustomerId(customerId).stream() //
                .map(DataConverter::toModel) //
                .collect(toList());
    }
}
