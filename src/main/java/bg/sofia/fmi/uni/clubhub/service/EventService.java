package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.EventEntity;
import bg.sofia.fmi.uni.clubhub.mail.IMailSender;
import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.model.Subscription;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.EventRepository;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    private final ISubscriptionService subscriptionService;
    private final CustomerService customerService;

    private final IMailSender mailSender;

    @Autowired
    public EventService(EventRepository eventRepository, ClubRepository clubRepository, //
            ISubscriptionService subscriptionService, CustomerService customerService, IMailSender mailSender) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.subscriptionService = subscriptionService;
        this.customerService = customerService;
        this.mailSender = mailSender;
    }

    @Override
    public Optional<Event> getById(UUID id) {
        return eventRepository.findById(id) //
                .map(DataConverter::toModel);
    }

    @Override
    public List<Event> getEventsHostedByAClub(UUID clubId) {
        List<EventEntity> events = eventRepository.findAllByClubId(clubId);
        return events.stream().map(DataConverter::toModel).collect(toList());
    }

    @Override
    public List<Event> getEventsThatContain(String word) {
        List<EventEntity> entities = eventRepository.findByNameIgnoreCaseContaining(word);

        return entities.stream().map(DataConverter::toModel)//
                .collect(toList());
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll().stream() //
                .map(DataConverter::toModel) //
                .collect(toList());
    }

    @Override
    @Transactional
    public Event createNew(Event event) {
        Optional<ClubEntity> club = clubRepository.findById(event.getClubId());
        if (!club.isPresent()) {
            throw new RuntimeException("No such club found");
        }

        EventEntity entity = toEntity(event);
        entity.setId(UUID.randomUUID());
        entity.setClub(club.get());

        Event createdEvent = toModel(eventRepository.save(entity));

        sendMailsToSubscribedCustomers(event, toModel(club.get()));
        return createdEvent;
    }

    @Override
    public void deleteById(UUID id) {
        eventRepository.deleteById(id);
    }

    private void sendMailsToSubscribedCustomers(Event event, Club club) {
        new Thread(() -> //
                subscriptionService.getSubscriptionsForClub(club.getId()).stream() //
                        .map(Subscription::getCustomerId) //
                        .map(customerService::getById) //
                        .filter(Optional::isPresent) //
                        .forEach(customer -> mailSender.sendEventNotification(event, club, customer.get()))) //
                .start();
    }
}
