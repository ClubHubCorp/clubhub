package bg.sofia.fmi.uni.clubhub.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity.AttendanceStatus;
import lombok.Data;
import lombok.Setter;

@Data
public class Booking {

    @Null
    @Setter
    private UUID id;

    @NotNull
    private final UUID customerId;

    @NotNull
    private final UUID clubId;

    @Positive
    @Min(10)
    private final int countOfPeople;

    @Future
    private final Date date;

    @PositiveOrZero
    private final int leaderboardPoints;

    @Positive
    private final BigDecimal overallPrice;

    @NotNull
    private final AttendanceStatus attendanceStatus;
}
