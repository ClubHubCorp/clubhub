package bg.sofia.fmi.uni.clubhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "CLUB")
public class ClubEntity extends UserEntity {

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "entrance_fee", nullable = false)
    private BigDecimal entranceFee;

    @OneToMany(mappedBy = "club")
    private Set<BookingEntity> bookings;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    private Set<RatingEntity> ratings;
}
