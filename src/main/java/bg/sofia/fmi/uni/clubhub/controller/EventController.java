package bg.sofia.fmi.uni.clubhub.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.service.IEventService;


@Controller
@RequestMapping("events")
public class EventController {
    private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("id") UUID id) {
        return eventService.getById(id) //
                .map(ResponseEntity::ok) //
                .orElse(notFound().build());
    }

    @GetMapping(value = "search/{word}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEvents(@PathVariable("word") String word) {
        return ok(eventService.getEventsThatContain(word));
    }

    @GetMapping(value = "clubs/{clubId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEventsForClub(@PathVariable("clubId") UUID clubId) {
        return ok(eventService.getEventsHostedByAClub(clubId));
    }

    @PostMapping(value = "clubs/{clubId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@PathVariable("clubId") UUID clubId, @Valid @RequestBody Event event) {
        return status(CREATED).body(eventService.createNew(event));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEvent(UUID id) {
        eventService.deleteById(id);
        return ok().build();
    }
}
