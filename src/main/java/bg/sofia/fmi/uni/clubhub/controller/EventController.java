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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping(value = "clubs/{clubId}")
    public String getEventsForClub(Model model, @PathVariable("clubId") UUID clubId) {
        model.addAttribute("events", eventService.getEventsHostedByAClub(clubId));
        return "events/club-events";
    }

    @GetMapping(value = "create-event")
    public String createEvent(@ModelAttribute("event") Event event) {
        return "events/create-event";
    }

    @PostMapping(value = "create-event")
    public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "clubs/create-event";
        }

        eventService.createNew(event);
        return "redirect:/";
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEvent(UUID id) {
        eventService.deleteById(id);
        return ok().build();
    }
}
