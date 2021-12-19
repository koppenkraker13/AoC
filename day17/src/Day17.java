import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day17 {

    public static int getXMaxVelocity(int[][] targetArea) {
        boolean notDone = true;
        int velocity = 0;
        while (notDone) {
            int distance = 0;
            for (int i = velocity; i > 0 ; i--) {
                distance += i;
            }
            if (distance > targetArea[0][1]) {
                notDone = false;
            }
            velocity++;
        }
        return velocity - 2;
    }

    public static boolean inTarget(int[][] target, int xVelocity, int yVelocity) {
        int xPower = xVelocity;
        int yPower = yVelocity;
        int[] position = new int[]{xPower, yPower};
        while (position[0] <= target[0][1] && position[1] >= target[1][0]) {
            if (position[0] >= target[0][0] && position[1] <= target[1][1]) {
                return true;
            }
            xPower = xPower > 0 ? xPower - 1 : 0;
            yPower--;
            position = new int[]{position[0] + xPower, position[1] + yPower};
        }
        return false;
    }

    public static int getYMaxVelocity(int[][] target) {
        int xVelocity = getXMaxVelocity(target);
        int highestInTarget = Integer.MIN_VALUE;
        for (int y = xVelocity + target[1][0]; y < xVelocity * 300; y++) {
            if (inTarget(target, xVelocity, y)) {
                highestInTarget = y;
            }
        }
        return highestInTarget;
    }

    public static int getHighestPoint(int yVelocity) {
        int result = yVelocity;
        for (int y = yVelocity - 1; y > 0; y--) {
            result += y;
        }
        return result;
    }

    public static int getAmountOfValues(int[][] target) {
        int result = 0;
        int xVelocity = getXMaxVelocity(target);
        for (int x = 0; x <= target[0][1]; x++) {
            for (int y = target[1][0]; y < xVelocity * 5; y++) {
                if (inTarget(target, x, y)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] targetArea = new int[2][2];
        try (BufferedReader br = new BufferedReader(new FileReader("day17/data/day17.txt"))) {
            String[] coors = br.readLine().split("=|\\..|\\, y=");
            targetArea = new int[][]{{Integer.parseInt(coors[1]), Integer.parseInt(coors[2])}, {Integer.parseInt(coors[3]), Integer.parseInt(coors[4])}};
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Highest point: " + getHighestPoint(getYMaxVelocity(targetArea)));
        System.out.println("Amount of velocitys: " + getAmountOfValues(targetArea));
    }
}
