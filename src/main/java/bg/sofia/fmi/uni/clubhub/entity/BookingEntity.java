package bg.sofia.fmi.uni.clubhub.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "BOOKING")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class BookingEntity {

    public enum AttendanceStatus {
        UPCOMING, //
        ATTENDED, //
        MISSED, //
        ;
    }

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClubEntity club;

    @Column(name = "count_people", nullable = false)
    private int countOfPeople;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date date;

    @Column(name = "leaderboard_points", nullable = false)
    private int leaderboardPoints;

    @Column(name = "overall_price", nullable = false)
    private BigDecimal overallPrice;

    @Column(name = "attendance_status", nullable = false)
    private AttendanceStatus attendanceStatus;
}
