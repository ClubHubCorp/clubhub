package bg.sofia.fmi.uni.clubhub.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {

    @Null
    private UUID id;

    @NotNull
    private UUID clubId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @Min(0)
    private int thresholdPoints;

    @NotNull
    @Min(1)
    @Max(100)
    private int percentOff;
}
