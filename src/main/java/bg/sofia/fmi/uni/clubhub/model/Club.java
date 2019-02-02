package bg.sofia.fmi.uni.clubhub.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
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
    private int capacity;

    @NotNull
    @Positive
    private BigDecimal entranceFee;

    @NotNull
    private Set<BookingEntity> bookings;

    @NotNull
    private Set<RatingEntity> ratings;

    @NotNull
    private Set<EventEntity> events;

}
