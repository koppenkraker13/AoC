import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day8Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("day8/data/day8.txt"))) {
            String line;
            int result = 0;
            while ((line = br.readLine()) != null) {
                String[] string = line.split("\\| ")[1].split(" ");
                for (String str : string) {
                    if (str.length() <= 4 || str.length() == 7) {
                        result++;
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
