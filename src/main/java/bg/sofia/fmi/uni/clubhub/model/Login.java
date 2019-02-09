package bg.sofia.fmi.uni.clubhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Login {
    @NotNull
    @Size(min = 5, max = 255)
    private final String username;

    @NotNull
    @Size(min = 5, max = 255)
    private final String password;
}
