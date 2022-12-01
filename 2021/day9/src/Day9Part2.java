import java.util.ArrayList;
import java.util.Collections;

public class Day9Part2 {

    public static void main(String[] args) {
        int[][] map = Day9Part1.getMap();
        ArrayList<int[]> lowPoints = Day9Part1.getLowpoints(map);
        ArrayList<Integer> result = new ArrayList<>();
        while (lowPoints.size() > 0) {
            Location basin = new Location(lowPoints.get(0), map);
            result.add(basin.getBasinCors().size());
            if (result.size() > 3) {
                Collections.sort(result);
                result.remove(0);
            }
            for (int[] cor : basin.getBasinCors()) {
                lowPoints.remove(cor);
            }
        }
        long endResult = 1;
        for (int size : result) {
            endResult *= size;
        }
        System.out.println(endResult);
    }
}
