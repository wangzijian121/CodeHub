package 消灭战舰游戏v2;

import java.awt.*;
import java.util.List;

public class Game {

    private List<WarShip> warShipList;
    private GameHelper gameHelper = new GameHelper();

    //play
    public void play() {
        //Initialization warShip
        warShipList = gameHelper.initWarship();
        //Random position
        warShipList = gameHelper.randomPosition(warShipList);
        Integer num = 0;
        while (true) {

            //Use input
            Point inputPoint = gameHelper.userInput();
            //hit or not
            if (checkHit(inputPoint)) {
                System.out.println("Hit✅！");
            } else {
                System.out.println("Miss❌！");
            }
            num++;
            //Sinking or not
            WarShip warShip = checkSinking();
            if (warShip != null) {
                System.out.println("Sank🚢:" + warShip.getName());
            }

            //win
            if(warShipList.size()==0||warShipList==null){
                System.out.println("You Win!!!");
                System.out.println("You hit count"+num);

            }

            //result

        }
    }


    /**
     * Check hit
     */
    public Boolean checkHit(Point point) {
        //Hit or Not ?
        for (WarShip warShip : warShipList) {
            if (warShip.getPosition().contains(point)) {
                //Delete point when hit warship
                warShip.getPosition().remove(point);
                return true;
            }
        }
        return false;
    }

    /**
     * Check Sinking
     */
    public WarShip checkSinking() {
        for (WarShip warShip : warShipList) {
            if (warShip.getPosition().size() == 0) {
                warShipList.remove(warShip);
                return warShip;
            }
        }
        return null;
    }


    /**
     * result
     */
    public String over(int score) {
        if (score >= 10) {
            System.out.println("good");
        } else {
            System.out.println("all right");
        }

        return "";
    }

    public List<WarShip> getWarShipList() {
        return warShipList;
    }

    public void setWarShipList(List<WarShip> warShipList) {
        this.warShipList = warShipList;
    }

}
