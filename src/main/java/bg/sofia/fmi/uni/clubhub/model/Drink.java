package bg.sofia.fmi.uni.clubhub.model;

import bg.sofia.fmi.uni.clubhub.enums.DrinkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.util.UUID;

@Data
public class Drink {

    @Null
    private UUID id;

    @NotNull
    @Size(min = 10)
    final private String name;

    @NotNull
    final private byte[] picture;

    @NotNull
    final private DrinkType type;

    @NotNull
    @Size(min = 20, max = 40)
    final private String description;
}