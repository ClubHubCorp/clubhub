package bg.sofia.fmi.uni.clubhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import java.util.UUID;

import lombok.Data;

import static javax.persistence.EnumType.STRING;

@MappedSuperclass
public abstract class UserEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
}
