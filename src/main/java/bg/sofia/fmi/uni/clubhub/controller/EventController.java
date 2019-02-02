package bg.sofia.fmi.uni.clubhub.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.service.IEventService;


@RestController
@RequestMapping(value = "events")
public class EventController {
	private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("id") UUID id) {
        Optional<Event> event = eventService.getById(id);
        if (!event.isPresent()) {
            return notFound().build();
        }

        return ok(event.get());
    }
    
    @GetMapping(value = "search/{word}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEvents(@PathVariable("word") String word) {
        List<Event> events = eventService.getEventsThatContain(word);
        return ok(events);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        return status(CREATED).body(eventService.createNew(event));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEvent(UUID id) {
        eventService.deleteById(id);
        return ok().build();
    }
}