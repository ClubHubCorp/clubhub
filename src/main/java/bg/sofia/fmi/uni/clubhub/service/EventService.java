package bg.sofia.fmi.uni.clubhub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.repository.EventRepository;

public class EventService implements IEventService{
	
	private final EventRepository eventRepository;
	
	@Autowired
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Optional<Event> getById(UUID id) {
		return eventRepository.findById(id) //
                .map(DataConverter::toModel);
	}

	@Override
	public List<Event> getEventsThatContain(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event createNew(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub
		
	}
}
