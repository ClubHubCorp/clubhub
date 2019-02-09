package bg.sofia.fmi.uni.clubhub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Event;


public interface IEventService {
	Optional<Event> getById(UUID id);
	List<Event> getEventsHostedByAClub(UUID clubId);
	List<Event> getEventsThatContain(String word);
	List<Event> getAllEvents();
	Event createNew(Event event);
	void deleteById(UUID id);
}
