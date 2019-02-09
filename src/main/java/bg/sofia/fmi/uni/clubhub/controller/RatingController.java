package bg.sofia.fmi.uni.clubhub.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import bg.sofia.fmi.uni.clubhub.model.Rating;
import bg.sofia.fmi.uni.clubhub.service.IRatingService;

@RestController
@RequestMapping(value = "ratings")
public class RatingController {

    private final IRatingService ratingService;

    @Autowired
    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(value = "clubs/{clubId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rating>> getClubRatings(@PathVariable("clubId") UUID id) {
        return ok(ratingService.getAllRatingsForClub(id));
    }

    @GetMapping(value = "clubs/{clubId}/average", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingEntity.Score> getAverageClubRating(@PathVariable("clubId") UUID id) {
        return ok(ratingService.getAverageScoreForClub(id));
    }

    @PostMapping(value = "clubs/{clubId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Rating> createRating(@PathVariable("clubId") String clubId, @Valid @RequestBody Rating rating) {
        return status(CREATED).body(ratingService.createNewForClub(rating));
    }
}
