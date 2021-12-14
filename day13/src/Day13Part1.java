import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13Part1 {

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
        if (instructions.get(0)[0].equals("x")) {
            paper.xFold(Integer.parseInt(instructions.get(0)[1]));
        } else {
            paper.yFold(Integer.parseInt(instructions.get(0)[1]));
        }
        System.out.println(paper.getDots().size());
    }

}
