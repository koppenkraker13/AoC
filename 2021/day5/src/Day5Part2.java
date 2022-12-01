import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5Part2 {

    public static int[][] getBetweenOblique(int[][] endpoints) {
        int[] startCor;
        int[] endCor;
        if (endpoints[0][1] < endpoints[1][1]) {
            startCor = endpoints[0];
            endCor = endpoints[1];
        } else {
            startCor = endpoints[1];
            endCor = endpoints[0];
        }
        int length = endCor[1] - startCor[1] + 1;
        int[][] result = new int[length][2];
        result[0] = startCor;
        if (endCor[0] < startCor[0]) {
            for (int i = 1; i < length; i++) {
                result[i] = new int[]{startCor[0] - i, startCor[1] + i};
            }
        } else {
            for (int i = 1; i < length; i++) {
                result[i] = new int[]{startCor[0] + i, startCor[1] + i};
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] highestCors = Day5Part1.getBiggestCors("day5/data/day5.txt");
        int[][] map = new int[highestCors[0] + 2][highestCors[1] + 2];
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day5/data/day5.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(" -> |,");
                int x1, x2, y1, y2;
                x1 = Integer.parseInt(coordinates[0]);
                y1 = Integer.parseInt(coordinates[1]);
                x2 = Integer.parseInt(coordinates[2]);
                y2 = Integer.parseInt(coordinates[3]);
                int[][] cors;
                if (x1 == x2 || y1 == y2) {
                    cors = Day5Part1.getBetweensStraight(new int[][]{{x1, y1}, {x2, y2}});
                } else {
                    cors = getBetweenOblique(new int[][]{{x1, y1}, {x2, y2}});
                }
                for (int i = 0; i < cors.length; i++) {
                    map[cors[i][0]][cors[i][1]]++;
                }
            }
            System.out.println(Day5Part1.overlapCounter(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
