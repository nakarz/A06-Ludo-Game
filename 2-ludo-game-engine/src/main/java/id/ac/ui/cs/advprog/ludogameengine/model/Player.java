package id.ac.ui.cs.advprog.ludogameengine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Player {
    public String colorName;
    public boolean flag;
    public int isSix;
    public boolean chance;
    public double count;

    public Player(String colorName, boolean flag, int count, int isSix, boolean chance) {
        this.colorName = colorName;
        this.flag = flag;
        this.isSix = isSix;
        this.chance = chance;
        this.count = count;
    }
}
