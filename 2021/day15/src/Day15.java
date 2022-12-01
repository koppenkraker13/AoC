import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day15 {

    public static int[][] createMap(boolean bigMap) {
        ArrayList<ArrayList<Integer>> mapArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day15/data/day15.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> list = new ArrayList<>();
                for (char chari : line.toCharArray()) {
                    list.add(Integer.parseInt("" + chari));
                }
                mapArray.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int times = bigMap ? 5 : 1;
        int[][] map = new int[mapArray.size() * times][mapArray.size() * times];
        for (int i = 0; i < mapArray.size(); i++) {
            for (int j = 0; j < mapArray.size(); j++) {
                map[i][j] = mapArray.get(i).get(j);
            }
        }
        if (bigMap) {
            for (int y = 0; y < mapArray.size(); y++) {
                for (int x = mapArray.size(); x < map.length; x++) {
                    int risk = map[y][x-mapArray.size()] + 1;
                    map[y][x] = risk > 9 ? 1 : risk;
                }
            }
            for (int x = 0; x < map.length; x++) {
                for (int y = mapArray.size(); y < map.length; y++) {
                    int risk = map[y-mapArray.size()][x] + 1;
                    map[y][x] = risk > 9 ? 1 : risk;
                }
            }
        }
        return map;
    }

    public static Position[][] createPositionMap(boolean big) {
        int[][] bigMap = createMap(big);
        Position[][] result = new Position[bigMap.length][bigMap.length];
        for (int y = 0; y < bigMap.length; y++) {
            for (int x = 0; x < bigMap.length; x++) {
                ArrayList<Position> neighbours = new ArrayList<>();
                if (x > 0) {
                    neighbours.add(result[y][x - 1]);
                }
                if (y > 0) {
                    neighbours.add(result[y - 1][x]);
                }
                result[y][x] = new Position(bigMap[y][x], (x == 0 && y == 0), neighbours);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Position[][] map = createPositionMap(false);
        System.out.println("Smoll map: " + map[map.length - 1][map.length - 1].getLowestRiskPath());

        Position[][] bigMap = createPositionMap(true);
        System.out.println("Big map: " + bigMap[bigMap.length - 1][bigMap.length - 1].getLowestRiskPath());
    }
}
