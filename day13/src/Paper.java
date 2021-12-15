import java.util.ArrayList;
import java.util.List;

public class Paper {
    private List<int[]> map = new ArrayList<>();

    public void addDot(int[] cor) {
        this.map.add(cor);
    }

    public void removeDuplicates() {
        List<int[]> result = new ArrayList<>();
        for (int[] cor : this.map) {
            boolean contained = false;
            for (int[] corResult : result) {
                if (corResult[0] == cor[0] && corResult[1] == cor[1]) {
                    contained = true;
                    break;
                }
            }
            if (!contained) {
                result.add(cor);
            }
        }
        this.map = result;
    }

    public void yFold(int ycor) {
        List<int[]> result = new ArrayList<>();
        for (int[] cors : this.map) {
            if (cors[1] < ycor) {
                result.add(cors);
            } else {
                result.add(new int[]{cors[0], ycor - (cors[1] - ycor)});
            }
        }
        this.map = result;
        removeDuplicates();
    }

    public int getDotAmount() {
        return this.map.size();
    }

    public void xFold(int xcor) {
        List<int[]> result = new ArrayList<>();
        for (int[] cors : this.map) {
            if (cors[0] < xcor) {
                result.add(cors);
            } else {
                result.add(new int[]{xcor - (cors[0] - xcor), cors[1]});
            }
        }
        this.map = result;
        removeDuplicates();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int biggestX = 0;
        int biggestY = 0;
        for (int[] cor : this.map) {
            biggestX = Math.max(cor[0], biggestX);
            biggestY = Math.max(cor[1], biggestY);
        }
        for (int y = 0; y <= biggestY; y++) {
            for (int x = 0; x <= biggestX; x++) {
                boolean check = false;
                for (int[] cor : this.map) {
                    if(cor[0] == x && cor[1] == y) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    result.append("|");
                } else {
                    result.append(" ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
