package bg.sofia.fmi.uni.clubhub.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import bg.sofia.fmi.uni.clubhub.entity.RatingEntity.Score;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rating {

	@Null
	private UUID id;

	@NotNull
	private UUID clubId;

	@NotNull
	private Score score;

	@Size(min = 5, max = 255)
	private String comment;
}
