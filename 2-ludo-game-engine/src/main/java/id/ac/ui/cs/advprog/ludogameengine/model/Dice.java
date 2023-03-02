package id.ac.ui.cs.advprog.ludogameengine.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="dice")
@Data
@Setter
@Getter
@NoArgsConstructor
public class Dice {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "flag")
    private boolean flag;

    @Column(name = "chance")
    private boolean chance;

    @Column(name = "count")
    private double count;

    @Column(name = "is_six")
    private int isSix;

    public Dice (int id, String colorName, boolean flag, int count, int isSix, boolean chance) {
        this.id = id;
        this.colorName = colorName;
        this.flag = flag;
        this.isSix = isSix;
        this.chance = chance;
        this.count = count;
    }
}
