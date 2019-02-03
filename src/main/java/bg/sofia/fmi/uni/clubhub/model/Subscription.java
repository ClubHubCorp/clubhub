package bg.sofia.fmi.uni.clubhub.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscription {

    @NotNull
    private final UUID customerId;

    @NotNull
    private final UUID clubId;

    @Null
    private Date date;
}
