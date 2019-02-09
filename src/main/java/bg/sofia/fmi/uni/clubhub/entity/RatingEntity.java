package bg.sofia.fmi.uni.clubhub.entity;

import static lombok.AccessLevel.PROTECTED;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
        DECENT, //
        GOOD, //
        EXCELLENT, //
        ;
    }

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClubEntity club;

    @Column(nullable = false)
    private Score score;

    @Column
    private String comment;
}
