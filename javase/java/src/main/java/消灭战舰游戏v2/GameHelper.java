package 消灭战舰游戏v2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * GameHelper
 */
public class GameHelper {


    /**
     * load Warship
     */
    public List<WarShip> initWarship() {
        // Create  warships
        List<WarShip> warShipList = new ArrayList<>();
        warShipList.add(new WarShip());
        warShipList.add(new WarShip());
        warShipList.add(new WarShip());

        int i = 1;
        for (WarShip warShip : warShipList) {
            warShip.setName("Warship" + i);
            i++;
        }
        return warShipList;
    }


    /**
     * Get User input.
     *
     * @return The string by user input.
     */
    public Point userInput() {

        //Get user input.
        Point point =new Point();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input position,Use comma split：");
            String input = scanner.next();

            Integer x = Integer.valueOf(input.split(",")[0]);
            Integer y = Integer.valueOf(input.split(",")[1]);
            if (x > 6 || y > 6) {
                System.out.println("X和Y must be less then 7！");
            }
            point.setLocation(x, y);
            break;
        }

        return point;
    }

    /**
     * Generate random position for  warships
     *
     * @return List<WarShip>
     */
    public List<WarShip> randomPosition(List<WarShip> warShipList) {

        for (WarShip warShip : warShipList) {
            while (true) {
                //Random 0 or 1 express x or y.
                Random random = new Random();
                Integer direction = random.nextInt(2);

                //Random generate three continuous number
                Integer numberOne = random.nextInt(7 - 2);

                //Generate position offset for x or y
                Integer offset = random.nextInt(7);
                List<Point> listPoint = new ArrayList<>();

                if (direction == 0) {
                    listPoint.add(new Point(numberOne, offset));
                    listPoint.add(new Point(numberOne + 1, offset));
                    listPoint.add(new Point(numberOne + 2, offset));

                } else {
                    listPoint.add(new Point(offset, numberOne));
                    listPoint.add(new Point(offset, numberOne + 1));
                    listPoint.add(new Point(offset, numberOne + 2));
                }

                //Overlay or not
                Boolean overlay = false;
                for (WarShip warShipCheck : warShipList) {
                    List<Point> listPointCheck = warShipCheck.getPosition();
                    if (listPointCheck == null || listPointCheck.size() < 3) {
                        break;
                    }
                    //check
                    for (Point pointCheck : listPointCheck) {
                        for (Point point : listPoint) {
                            if (point.equals(pointCheck)) {
                                overlay = true;
                            }
                        }
                    }
                }
                if (!overlay) {
                    //set
                    warShip.setPosition(listPoint);
                    //作弊器
                    System.out.println(listPoint);
                    break;
                }

            }
        }
        return warShipList;
    }
}
