import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public static void main(String[] args) {
        Paper paper = new Paper();
        List<String[]> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("day13/data/day13.txt"))) {
            String line;
            while ((line = br.readLine()) != null && !line.equals("")) {
                String[] cors = line.split(",");
                paper.addDot(new int[] {Integer.parseInt(cors[0]), Integer.parseInt(cors[1])});
            }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                parts[0] = parts[0].substring(parts[0].length() - 1);
                instructions.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String[] instruction : instructions) {
            if (instruction[0].equals("x")) {
                paper.xFold(Integer.parseInt(instruction[1]));
            } else {
                paper.yFold(Integer.parseInt(instruction[1]));
            }
            System.out.println(paper.getDotAmount() + " Dots on the paper");
        }
        System.out.println("\n end result:\n" + paper);
    }

}
