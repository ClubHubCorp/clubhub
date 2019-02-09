package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;
import static java.util.stream.Collectors.averagingInt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import bg.sofia.fmi.uni.clubhub.model.Rating;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.RatingRepository;

@Service
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, ClubRepository clubRepository) {
        this.ratingRepository = ratingRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    @Transactional
    public Rating createNewForClub(Rating rating) {
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
        return ratingRepository.findAllByClubId(clubId).stream() //
                .map(DataConverter::toModel) //
                .collect(Collectors.toList());
    }

    @Override
    public RatingEntity.Score getAverageScoreForClub(UUID clubId) {
        Double averageScore = ratingRepository.findAllByClubId(clubId).stream() //
                .map(RatingEntity::getScore) //
                .map(Enum::ordinal) //
                .collect(averagingInt(s -> s));

        return RatingEntity.Score.values()[averageScore.intValue()];
    }
}
