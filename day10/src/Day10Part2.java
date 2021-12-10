import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Day10Part2 {

    public static void main(String[] args) {
        ArrayList<Long> allScores = new ArrayList<>();
        List<String> lines = Day10Part1.getLines(false);
        for (String line : lines) {
            List<Character> list = Day10Part1.stringToList(line);
            ArrayList<Long> scores = new ArrayList<>();
            ArrayList<Integer> correct = Day10Part1.getCorrectCharacters(list);
            for (int i = 0; i < list.size(); i++) {
                if (!correct.contains(i)) {
                    switch (list.get(i)) {
                        case '(':
                            scores.add((long) 1);
                            break;
                        case '[':
                            scores.add((long) 2);
                            break;
                        case '{':
                            scores.add((long) 3);
                            break;
                        case '<':
                            scores.add((long) 4);
                    }
                }
            }
            long total = 0;
            for (int i = scores.size() - 1; i >= 0; i--) {
                total *= 5;
                total += scores.get(i);
            }
            allScores.add(total);
        }
        Collections.sort(allScores);
        System.out.println(allScores.get((allScores.size() / 2)));
    }
}
