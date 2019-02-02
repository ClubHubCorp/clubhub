package bg.sofia.fmi.uni.clubhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static lombok.AccessLevel.PROTECTED;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RATING")
@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class RatingEntity {

    public enum Score {
        AWFUL, //
        NOT_BAD, //
        GOOD, //
        EXCELLENT, //
        ;
    }

    @Id
    private UUID id;

    @Column(name = "club_id", nullable = false)
    private UUID clubId;

    @Column(nullable = false)
    private Score score;

    @Column
    private String comment;
}
