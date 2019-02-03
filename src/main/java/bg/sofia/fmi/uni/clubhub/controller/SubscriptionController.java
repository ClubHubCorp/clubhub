package bg.sofia.fmi.uni.clubhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Subscription;
import bg.sofia.fmi.uni.clubhub.service.ISubscriptionService;
import bg.sofia.fmi.uni.clubhub.service.SubscriptionService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("subscriptions")
public class SubscriptionController {

    private final ISubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription) {
        return status(CREATED).body(subscriptionService.createNew(subscription));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllForCustomer(@RequestParam("customerId") UUID customerId) {
        return ok(subscriptionService.getSubscriptionsForCustomer(customerId));
    }
}
