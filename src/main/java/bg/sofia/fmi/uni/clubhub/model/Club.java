package bg.sofia.fmi.uni.clubhub.model;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.entity.DiscountEntity;
import bg.sofia.fmi.uni.clubhub.entity.EventEntity;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Club {

    @Null
    private UUID id;

    @NotNull
    @Size(min = 5, max = 255)
    private final String username;

    @NotNull
    @Size(min = 5, max = 255)
    private final String password;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$")
    private final String email;

    @NotNull
    @Size(min = 20)
    private String address;

    @NotNull
    @Min(50)
    private Integer capacity;

    @NotNull
    @Min(0)
    private BigDecimal entranceFee;

    private Set<BookingEntity> bookings;

    private Set<RatingEntity> ratings;

    private Set<EventEntity> events;

    private Set<DiscountEntity> discounts;

    @PositiveOrZero
    private final Integer leaderboardPoints;

}
