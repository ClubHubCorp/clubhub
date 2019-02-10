package bg.sofia.fmi.uni.clubhub.model;

import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.BookingEntity.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class Booking {

    @Null
    @Setter
    private UUID id;

    @NotNull
    private final UUID customerId;

    @NotNull
    private final UUID clubId;

    @Positive
    @Min(1)
    private final Integer countOfPeople;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date bookingDate;

    @Positive
    private BigDecimal overallPrice;

    private final AttendanceStatus attendanceStatus;
}
