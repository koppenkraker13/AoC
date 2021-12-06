import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {

    public static ArrayList<Double> birthCycle(ArrayList<Double> fish, int days) {
        for (int i = 0; i < days; i++) {
            double day0 = fish.get(0);
            fish.remove(0);
            fish.add(day0);
            fish.set(6, fish.get(6) + day0);
        }
        return fish;
    }

    public static double getFish(int dayAmount) {
        try (BufferedReader br = new BufferedReader(new FileReader("day6/data/day6"))) {
            ArrayList<Double> fish = new ArrayList<>();
            String line;
            for (int i = 0; i < 9; i++) {
                fish.add((double) 0);
            }
            while ((line = br.readLine()) != null) {
                String[] ages = line.split(",");
                for (String stringAge : ages) {
                    int age = Integer.parseInt(stringAge);
                    fish.set(age, fish.get(age) + 1);
                }
            }
            double total = 0;
            for (double amount : birthCycle(fish, dayAmount)) {
                total += amount;
            }
            return total;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.print("Amount after 80 days: ");
        System.out.printf("%.1f", getFish(80));
        System.out.print("\nAmount after 256 days: ");
        System.out.printf("%.1f", getFish(256));
    }
}
