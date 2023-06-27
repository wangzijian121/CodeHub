package 消灭战舰游戏v2;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class WarShip {

    /**
     * warship name
     */
    private String name;
    /**
     * position
     */
    private List<Point> position;


    public List<Point> getPosition() {
        return position;
    }

    public void setPosition(List<Point> position) {
        this.position = position;
    }



    public WarShip() {
    }

    public WarShip(String name, Map<Integer, Integer> position) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WarShip{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }

}
