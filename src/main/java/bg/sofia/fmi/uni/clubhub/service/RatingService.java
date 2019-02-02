package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import bg.sofia.fmi.uni.clubhub.model.Rating;
import bg.sofia.fmi.uni.clubhub.repository.RatingRepository;

@Service
public class RatingService implements IRatingService{
	
	private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
	
	@Override
	public Rating createNew(Rating rating, UUID clubId) {
		 RatingEntity entity = toEntity(rating);
	     entity.setId(UUID.randomUUID());

	     return toModel(ratingRepository.save(entity));
	}
	
	public RatingEntity.Score getAverageScoreForClub(UUID clubId) {
		Double averageScore = ratingRepository.findAllByclubId(clubId).stream() //
			.map(RatingEntity::getScore) //
			.map(score -> score.ordinal()) //
			.collect(Collectors.averagingInt(s -> s));
		
		return RatingEntity.Score.values()[averageScore.intValue()];
	}
	
	@Override
	public List<Rating> getAllForClub(UUID clubId) {
		
		return ratingRepository.findAllByclubId(clubId).stream()//
				.map(DataConverter::toModel)//
				.collect(Collectors.toList());
	}

}
