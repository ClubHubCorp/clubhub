package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.EventEntity;
import bg.sofia.fmi.uni.clubhub.model.Event;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

	private final EventRepository eventRepository;
	private final ClubRepository clubRepository;

	@Autowired
	public EventService(EventRepository eventRepository, ClubRepository clubRepository) {
		this.eventRepository = eventRepository;
		this.clubRepository = clubRepository;
	}

	@Override
	public Optional<Event> getById(UUID id) {
		return eventRepository.findById(id) //
				.map(DataConverter::toModel);
	}

	@Override
	public List<Event> getEventsHostedByAClub(UUID clubId) {
		List<EventEntity> events = eventRepository.findAllByClubId(clubId);
		return events.stream().map(DataConverter :: toModel).collect(Collectors.toList());
	}

	@Override
	public List<Event> getEventsThatContain(String word) {
		List<EventEntity> entities = eventRepository.findByNameIgnoreCaseContaining(word);

		return entities.stream().map(DataConverter::toModel)//
				.collect(Collectors.toList());
	}

	@Override
	public Event createNew(Event event) {
		Optional<ClubEntity> club = clubRepository.findById(event.getClubId());
		if (!club.isPresent()) {
			throw new RuntimeException("No such club found");
		}
		
		EventEntity entity = toEntity(event);
		entity.setEntity_id(UUID.randomUUID());
		entity.setClub(club.get());
		
		return toModel(eventRepository.save(entity));
	}

	@Override
	public void deleteById(UUID id) {
		eventRepository.deleteById(id);
	}
}
