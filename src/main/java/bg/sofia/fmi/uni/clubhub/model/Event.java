package bg.sofia.fmi.uni.clubhub.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class Event {

    @Null
    private UUID id;
    
    @NotNull
	private UUID clubId;

    @NotNull
    @Size(min = 5, max = 255)
    private String name;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @Size(min = 5)
    private String description;
}
