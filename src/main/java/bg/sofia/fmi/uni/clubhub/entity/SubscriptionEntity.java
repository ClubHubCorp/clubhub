package bg.sofia.fmi.uni.clubhub.entity;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUBSCRIPTION")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class SubscriptionEntity {

    @EmbeddedId
    private SubscriptionId subscriptionId;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Embeddable
    @Data
    @NoArgsConstructor(access = PROTECTED)
    @AllArgsConstructor
    public static class SubscriptionId implements Serializable {

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "customer_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private CustomerEntity customer;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "club_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private ClubEntity club;
    }
}
