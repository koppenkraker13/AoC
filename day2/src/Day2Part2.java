import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day2Part2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("day2/data/day2.txt"))) {
            String line;
            int forward = 0;
            int depth = 0;
            int aim = 0;
            while ((line = br.readLine()) != null) {
                String command[] = line.split(" ");
                int amount = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward":
                        forward += amount;
                        depth += amount * aim;
                        break;
                    case "up":
                        aim -= amount;
                        break;
                    case "down":
                        aim += amount;
                        break;
                }
            }
            System.out.println("forward: " + forward + "\ndept: " + depth + "\nFinal answer: " + forward*depth);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
