import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day11 {
    private static Octopuse[][] map;

    public static Octopuse[][] readMap(String filePath) {
        ArrayList<Octopuse[]> map = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                ArrayList<Octopuse> xcors = new ArrayList<>();
                for (char chari : chars) {
                    xcors.add(new Octopuse(Integer.parseInt(Character.toString(chari))));
                }
                Octopuse[] xcorArr = new Octopuse[xcors.size()];
                for (int i = 0; i < xcors.size(); i++) {
                    xcorArr[i] = xcors.get(i);
                }
                map.add(xcorArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Octopuse[][] result = new Octopuse[map.get(0).length][map.size()];
        for (int i = 0; i < map.size(); i++) {
            result[i] = map.get(i);
        }
        return result;
    }

    public static ArrayList<int[]> flashNeighbours(int[] cors) {
        ArrayList<int[]> flashed = new ArrayList<>();
        for (int y = cors[1] - 1; y <= cors[1] + 1; y++) {
            for (int x = cors[0] - 1; x <= cors[0] + 1; x++) {
                if (y >= 0 && y < map.length && x >= 0 && x < map[y].length) {
                    if (map[x][y].increase()) {
                        flashed.add(new int[] {x, y});
                    }
                }
            }
        }
        return flashed;
    }

    public static int step(boolean resetFlashAtEnd) {
        int flashAmount = 0;
        LinkedList<int[]> flashed = new LinkedList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[x][y].increase()) {
                    flashed.push(new int[] {x, y});
                    flashAmount++;
                }
            }
        }
        while (!flashed.isEmpty()) {
            for (int[] pos : flashNeighbours(flashed.pop())) {
                flashAmount++;
                flashed.push(pos);
            }
        }
        if (resetFlashAtEnd) {
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    map[x][y].setFlashFalse();
                }
            }
        }
        return flashAmount;
    }

    public static boolean checkAllFlashStep() {
        step(false);
        boolean allFlash = true;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (!map[x][y].didFlash()) {
                    allFlash = false;
                }
                map[x][y].setFlashFalse();
            }
        }
        return allFlash;
    }

    public static void main(String[] args) {
        map = readMap("day11/data/day11.txt");
        long totalFlashes = 0;
        for (int i = 0; i < 100; i++) {
            totalFlashes += step(true);
        }
        System.out.println("Amount of flashes after a 100 steps: " + totalFlashes);
        map = readMap("day11/data/day11.txt");
        boolean bigFlash = false;
        long counter = 0;
        while (!bigFlash) {
            bigFlash = checkAllFlashStep();
            counter++;
        }
        System.out.println("First big flash: " + counter);
    }
}
