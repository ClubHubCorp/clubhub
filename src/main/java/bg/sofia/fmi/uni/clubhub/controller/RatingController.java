package bg.sofia.fmi.uni.clubhub.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import bg.sofia.fmi.uni.clubhub.model.Rating;
import bg.sofia.fmi.uni.clubhub.service.IRatingService;

@Controller
@RequestMapping(value = "ratings")
public class RatingController {

    private final IRatingService ratingService;

    @Autowired
    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(value = "clubs/{clubId}", produces = APPLICATION_JSON_VALUE)
    public String getClubRatings(@PathVariable("clubId") UUID id, Model model) {
        model.addAttribute("ratings", ratingService.getAllRatingsForClub(id));
        return "ratings/all-for-club";
    }

    @GetMapping(value = "clubs/{clubId}/average", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingEntity.Score> getAverageClubRating(@PathVariable("clubId") UUID id) {
        return ok(ratingService.getAverageScoreForClub(id));
    }

    @GetMapping("create-rating/clubs/{clubId}")
    public String createRating(@ModelAttribute("rating") Rating rating, @PathVariable("clubId") UUID clubId) {
        rating.setClubId(clubId);
        return "ratings/create-rating";
    }

    @PostMapping("create-rating/{clubId}")
    public String createRating(@Valid @ModelAttribute("rating") Rating rating, @PathVariable("clubId") UUID clubId, //
            BindingResult result) {
        if (result.hasErrors()) {
            return "ratings/create-rating/" + clubId.toString();
        }

        ratingService.createNewForClub(rating);
        return "redirect:/ratings/clubs/" + clubId.toString();
    }

    @PostMapping(value = "clubs/{clubId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Rating> createRating(@PathVariable("clubId") String clubId, @Valid @RequestBody Rating rating) {
        return status(CREATED).body(ratingService.createNewForClub(rating));
    }
}
