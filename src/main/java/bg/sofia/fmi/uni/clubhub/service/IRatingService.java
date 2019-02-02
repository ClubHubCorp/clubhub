package bg.sofia.fmi.uni.clubhub.service;

import java.util.List;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Rating;

public interface IRatingService {
	Rating createNew(Rating rating, UUID clubId);
    List<Rating> getAllForClub(UUID clubId);
 }
