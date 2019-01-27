package bg.sofia.fmi.uni.clubhub.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SUBSCRIPTION")
@Data
public class SubscriptionEntity {

    @EmbeddedId
    private SubscriptionId subscriptionId;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date date;

    @Embeddable
    @Data
    public class SubscriptionId implements Serializable {
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
