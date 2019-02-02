package bg.sofia.fmi.uni.clubhub.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private static final int LEGAL_DRINKING_AGE = 18;

    @Null
    private UUID id;

    @NotNull
    @Size(min = 5, max = 255)
    private final String username;

    @NotNull
    @Size(min = 5, max = 255)
    private final String password;

    @NotNull
    @Email(regexp = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$")
    private final String email;

    @NotNull
    @Size(min = 5, max = 255)
    private final String firstName;

    @NotNull
    @Size(min = 5, max = 255)
    private final String lastName;

    @Positive
    @Min(LEGAL_DRINKING_AGE)
    private final int age;

    @NotNull
    private final Set<Booking> bookings;
}
