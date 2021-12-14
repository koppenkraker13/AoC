import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<int[]> getDots() {
        return this.map;
    }
}
