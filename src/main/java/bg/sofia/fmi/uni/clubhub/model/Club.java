package bg.sofia.fmi.uni.clubhub.model;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity;
import bg.sofia.fmi.uni.clubhub.entity.RatingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.annotation.AdviceName;

import javax.persistence.NamedAttributeNode;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Club {

    @Null
    private UUID id;

    @NotNull
    @Size(min = 20)
    private String address;

    @NotNull
    @Positive
    private BigDecimal entranceFee;

    @NotNull
    private Set<BookingEntity> bookings;

    @NotNull
    private Set<RatingEntity> ratings;

}
