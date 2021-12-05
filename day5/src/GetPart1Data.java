import java.io.*;
import java.util.Objects;

public class GetPart1Data {
    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("day5/data/day5"))) {
            String line;
            FileWriter fw = new FileWriter("day5/data/day5Part1");
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.split(" -> |,");
                if (Objects.equals(coordinates[0], coordinates[2]) || Objects.equals(coordinates[1], coordinates[3])) {
                    fw.write(line + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
