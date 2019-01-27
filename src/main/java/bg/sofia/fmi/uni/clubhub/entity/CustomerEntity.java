package bg.sofia.fmi.uni.clubhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CUSTOMER")
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends UserEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
}
