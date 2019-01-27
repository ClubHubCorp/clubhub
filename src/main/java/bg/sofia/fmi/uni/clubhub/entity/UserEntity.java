package bg.sofia.fmi.uni.clubhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.UUID;

import lombok.Data;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "USER")
@Data
public class UserEntity {

    enum Role {
        USER, //
        CLUB, //
        ADMIN, //
        ;
    }

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Role role;
}
