package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import bg.sofia.fmi.uni.clubhub.model.Rating;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.RatingRepository;

@Service
public class RatingService implements IRatingService{
	
	private final RatingRepository ratingRepository;
	private final ClubRepository clubRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, ClubRepository clubRepository) {
        this.ratingRepository = ratingRepository;
        this.clubRepository = clubRepository;
    }
	
	@Override
	public Rating createNew(Rating rating) {
		Optional<ClubEntity> club = clubRepository.findById(rating.getClubId());
		if (!club.isPresent()) {
			throw new RuntimeException("No such club found");
		}

		RatingEntity entity = toEntity(rating);
		entity.setId(UUID.randomUUID());
		entity.setClub(club.get());

		return toModel(ratingRepository.save(entity));
	}
	
	
	@Override
	public List<Rating> getAllRatingsForClub(UUID clubId) {
		List<RatingEntity> ratings = ratingRepository.findAllByclubId(clubId);
		
		return ratings.stream().map(DataConverter::toModel).collect(Collectors.toList());
	}
	
	@Override
	public RatingEntity.Score getAverageScoreForClub(UUID clubId) {
		Double averageScore = ratingRepository.findAllByclubId(clubId).stream() //
			.map(RatingEntity::getScore) //
			.map(score -> score.ordinal()) //
			.collect(Collectors.averagingInt(s -> s));
		
		return RatingEntity.Score.values()[averageScore.intValue()];
	}

}
