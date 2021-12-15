import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day15Part1 {


    public static void main(String[] args) {
        Position[][] map = new Position[100][100];
        try (BufferedReader br = new BufferedReader(new FileReader("day15/data/day15.txt"))) {
            String line;
            int counterY = 0;
            while ((line = br.readLine()) != null) {
                int counterX = 0;
                for (char num : line.toCharArray()) {
                    ArrayList<Position> neighbours = new ArrayList<>();
                    if (counterX > 0) {
                        neighbours.add(map[counterY][counterX-1]);
                    }
                    if (counterY > 0) {
                        neighbours.add(map[counterY-1][counterX]);
                    }
                    map[counterY][counterX] = new Position(Integer.parseInt("" + num), (counterX == 0 && counterY == 0), neighbours);
                    counterX++;
                }
                counterY++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(map[99][99].getLowestRiskPath());
    }
}
