import java.util.ArrayList;

public class Location {
    private ArrayList<int[]> basinCors = new ArrayList<>();
    private int[][] map;

    public Location(int[] startCor, int[][] map) {
        this.basinCors.add(startCor);
        this.map = map;
        createBasin(startCor);
    }

    public ArrayList<int[]> getBasinCors() {
        return this.basinCors;
    }

    public void createBasin(int[] startCor) {
        ArrayList<int[]> cors = increaseBasin(startCor);
        if (cors.size() != 0) {
            for (int[] cor : cors) {
                createBasin(cor);
            }
        }
    }

    public ArrayList<int[]> increaseBasin(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];
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
        ArrayList<int[]> result = new ArrayList<>();
        if (up != 9) {
            result.add(new int[] {x, y - 1});
        }
        if (right != 9) {
            result.add(new int[] {x + 1, y});
        }
        if (down != 9) {
            result.add(new int[] {x, y + 1});
        }
        if (left != 9) {
            result.add(new int[] {x - 1, y});
        }
        ArrayList<int[]> newResult = new ArrayList<>();
        for (int[] cor : result) {
            if (!firstContainsSecond(this.basinCors, cor)) {
                newResult.add(cor);
                this.basinCors.add(cor);
            }
        }
        return newResult;
    }

    public boolean firstContainsSecond(ArrayList<int[]> list, int[] cor) {
        for (int[] elem : list) {
            if (elem[0] == cor[0] && elem[1] == cor[1]) {
                return true;
            }
        }
        return false;
    }
}
