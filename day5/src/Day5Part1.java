import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day5Part1 {

    public static int[][] getBetweensStraight(int[][] endpoints) {
        int[][] result;
        if (endpoints[0][0] == endpoints[1][0]) {
            int[] startCor = endpoints[0][1] < endpoints [1][1] ? endpoints[0] : endpoints[1];
            int length = endpoints[0][1] - endpoints[1][1];
            if (length < 0) {length *= -1;}
            length++;
            if (length == 984) {
                System.out.println("test");
            }
            result = new int[length][2];
            result[0] = startCor;
            for (int i = 1; i < length; i++) {
                result[i] = new int[]{startCor[0], startCor[1] + i};
            }
        } else {
            int[] startCor = endpoints[0][0] < endpoints [1][0] ? endpoints[0] : endpoints[1];
            int length = endpoints[0][0] - endpoints[1][0];
            if (length < 0) {length *= -1;}
            length++;
            if (length == 984) {
                System.out.println("test");
            }
            result = new int[length][2];
            result[0] = startCor;
            for (int i = 1; i < length; i++) {
                result[i] = new int[]{startCor[0] + i, startCor[1]};
            }
        }
        return result;
    }

    public static int[] getBiggestCors(String filepath) {
        int highestXCor = 0;
        int highestYCor = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(" -> |,");
                for (int i = 0; i < coordinates.length; i++) {
                    int testCor = Integer.parseInt(coordinates[i]);
                    if (i == 0 || i == 2) {
                        if (testCor > highestXCor) {
                            highestXCor = testCor;
                        }
                    } else {
                        if (testCor > highestYCor) {
                            highestYCor = testCor;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[]{highestXCor, highestYCor};
    }

    public static int overlapCounter(int[][] map) {
        int overlapCounter = 0;
        for (int[] ints : map) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] > 1) {
                    overlapCounter++;
                }
            }
        }
        return overlapCounter;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[] highestCors = getBiggestCors("day5/data/day5Part1");
        int[][] map = new int[highestCors[0] + 1][highestCors[1] + 1];
        try (BufferedReader br = new BufferedReader(new FileReader("day5/data/day5Part1"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(" -> |,");
                int x1, x2, y1, y2;
                x1 = Integer.parseInt(coordinates[0]);
                y1 = Integer.parseInt(coordinates[1]);
                x2 = Integer.parseInt(coordinates[2]);
                y2 = Integer.parseInt(coordinates[3]);
                int[][] cors = getBetweensStraight(new int[][]{{x1, y1}, {x2, y2}});
                for (int i = 0; i < cors.length; i++) {
                    map[cors[i][0]][cors[i][1]]++;
                }
            }
            System.out.println(overlapCounter(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
