package bg.sofia.fmi.uni.clubhub.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "DRINK")
@Data
public class DrinkEntity {

    enum DrinkType {
        Alchoholic, //
        NotAlchoholic, //
        ;
    }

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "picture")
    private Blob Picture;

    @Column(name = "type")
    private DrinkType Type;

    @Column(name = "description")
    private String Description;
}
