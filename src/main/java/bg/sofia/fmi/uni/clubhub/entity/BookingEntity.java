package bg.sofia.fmi.uni.clubhub.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "BOOKING")
@Data
public class BookingEntity {
	
	enum AttendanceStatus {
        UPCOMING, //
        ATTENDED, //
        MISSED, //
        ;
    }
	
	 @Id
	 private UUID id;
	 
	 @Column(name = "count_people", nullable = false)
	 private int countOfPeople;
	 
	 @Column(name = "date", nullable = false)
	 @Temporal(TemporalType.TIME)
	 private Date date;
	 
	 @Column(name = "leaderboard_points", nullable = false)
	 private int leaderboardPoints;
	 
	 @Column(name = "overall_price", nullable = false)
	 private double overallPrice;
	 
	 @Column(name = "attendance_status", nullable = false)
	 private AttendanceStatus attendanceStatus;
	 
	 //TO-DO relation with user and club
}
