import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9Part1 {
    public static int[] getDimensions(String filePath) {
        int[] result = new int[2];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                result[0]++;
                result[1] = line.length();
            }
            while (br.readLine() != null) result[0]++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int[][] getMap() {
        int[] dimensions = getDimensions("day9/data/day9.txt");
        int[][] map = new int[dimensions[0]][dimensions[1]];
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day9/data/day9.txt"))) {
            String line;
            int ycor = 0;
            while ((line = br.readLine()) != null) {
                char[] deptsChar = line.toCharArray();
                for (int i = 0; i < deptsChar.length; i++) {
                    map[ycor][i] = Integer.parseInt("" + deptsChar[i]);
                }
                ycor++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static ArrayList<int[]> getLowpoints(int[][] map) {
        ArrayList<int[]> result = new ArrayList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int currentDept = map[y][x];
                int up = 9, down = 9, left = 9, right = 9;
                if (x == 0) {
                    right = map[y][x + 1];
                } else if (x == map.length - 1) {
                    left = map[y][x - 1];
                } else {
                    right = map[y][x + 1];
                    left = map[y][x - 1];
                }
                if (y == 0) {
                    down = map[y + 1][x];
                } else if (y == map[x].length - 1) {
                    up = map[y - 1][x];
                } else {
                    down = map[y + 1][x];
                    up = map[y - 1][x];
                }
                if (currentDept < up && currentDept < right && currentDept < down && currentDept < left) {
                    result.add(new int[] {x, y});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] map = getMap();
        ArrayList<int[]> lowpoints = getLowpoints(map);
        int result = 0;
        for (int[] cors : lowpoints) {
            result += map[cors[1]][cors[0]] + 1;
        }
        System.out.println(result);
    }
}
