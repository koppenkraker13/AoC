import java.io.*;

public class Day1Part1 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("day1/data/day1.txt"))) {
            String line;
            int counter = -1;
            int previous = -1;
            while ((line = br.readLine()) != null) {
                if (Integer.parseInt(line) > previous) {
                    counter++;
                }
                previous = Integer.parseInt(line);
            }
            System.out.println(counter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
