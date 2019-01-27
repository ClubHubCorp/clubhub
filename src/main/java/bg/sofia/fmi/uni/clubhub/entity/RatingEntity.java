package bg.sofia.fmi.uni.clubhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.UUID;

import lombok.Data;

@Entity
@Table(name = "RATING")
@Data
public class RatingEntity {

    enum Score {
        AWFUL, //
        NOT_BAD, //
        GOOD, //
        EXCELLENT, //
        ;
    }

    @Column(name = "club_id", nullable = false)
    private UUID clubId;

    @Column(nullable = false)
    private int score;

    @Column
    private Score comment;
}
