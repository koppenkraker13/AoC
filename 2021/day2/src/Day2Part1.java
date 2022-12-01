import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day2Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day2/data/day2.txt"))) {
            String line;
            int forward = 0;
            int dept = 0;
            while ((line = br.readLine()) != null) {
                String command[] = line.split(" ");
                int amount = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward":
                        forward += amount;
                        break;
                    case "up":
                        dept -= amount;
                        break;
                    case "down":
                        dept += amount;
                        break;
                }
            }
            System.out.println("forward: " + forward + "\ndept: " + dept + "\nFinal answer: " + forward*dept);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
