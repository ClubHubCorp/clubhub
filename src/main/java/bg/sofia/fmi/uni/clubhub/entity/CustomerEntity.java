package bg.sofia.fmi.uni.clubhub.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends UserEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "leaderboard_points", nullable = false)
    private int leaderboardPoints;

    @OneToMany(mappedBy = "customer")
    private Set<BookingEntity> bookings;

    public CustomerEntity(UUID id) {
        super(id);
    }

    public CustomerEntity(UUID id, String username, String password, String email, String firstName, String lastName, int age,
            int leaderboardPoints, Set<BookingEntity> bookings) {
        super(id, username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.leaderboardPoints = leaderboardPoints;
        this.bookings = bookings;
    }
}
