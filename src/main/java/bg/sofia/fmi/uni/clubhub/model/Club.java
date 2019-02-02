package bg.sofia.fmi.uni.clubhub.model;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.entity.EventEntity;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.annotation.AdviceName;

import javax.persistence.NamedAttributeNode;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

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
    @Email
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")
    private final String email;

    @NotNull
    @Size(min = 20)
    private String address;

    @NotNull
    @Size(min = 50)
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
