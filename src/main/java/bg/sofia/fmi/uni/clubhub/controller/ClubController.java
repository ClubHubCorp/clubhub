package bg.sofia.fmi.uni.clubhub.controller;

import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.service.IClubService;
import bg.sofia.fmi.uni.clubhub.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "clubs")
public class ClubController {
    IClubService clubService;
    IEventService eventService;

    @Autowired

    public ClubController(IClubService clubService, IEventService eventService) {
        this.clubService = clubService;
        this.eventService = eventService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Club> createClub(@Valid @RequestBody  Club club) {
        return status(CREATED).body(clubService.createNew(club));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delteClub(UUID id) {
        clubService.deleteById(id);
        return ok().build();
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Club> getClub(@PathVariable("id") UUID id) {
        Optional<Club> club = clubService.findByID(id);
        if (!club.isPresent()) {
            return notFound().build();
        }
        return ok().body(club.get());
    }

    @GetMapping(value = "{id}/events")
    public ResponseEntity<List<String>> getEventsHostedByClub(@PathVariable("id") UUID id) {
        List<Event> events = eventService.getEventsHostedByAClub(id);
        if (events == null) {
            return notFound().build();
        }
        List<String> eventNames = events.stream().map(Event::getName).collect(Collectors.toList());
        return ok().body(eventNames);
    }

    @PostMapping(value = "{club_id}/events")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        return status(CREATED).body(eventService.createNew(event));
    }
}
