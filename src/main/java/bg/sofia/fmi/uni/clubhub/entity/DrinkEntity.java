package bg.sofia.fmi.uni.clubhub.entity;

import bg.sofia.fmi.uni.clubhub.enums.DrinkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "DRINK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkEntity {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "picture")
    private Blob picture;

    @Enumerated
    @Column(name = "type", nullable = false)
    private DrinkType type;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClubEntity club;
}
