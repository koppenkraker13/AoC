import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {

    public static ArrayList<Long> birthCycle(ArrayList<Long> fish, int days) {
        for (int i = 0; i < days; i++) {
            long day0 = fish.get(0);
            fish.remove(0);
            fish.add(day0);
            fish.set(6, fish.get(6) + day0);
        }
        return fish;
    }

    public static long getFish(int dayAmount) {
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day6/data/day6.txt"))) {
            ArrayList<Long> fish = new ArrayList<>();
            String line;
            for (int i = 0; i < 9; i++) {
                fish.add((long) 0);
            }
            while ((line = br.readLine()) != null) {
                String[] ages = line.split(",");
                for (String stringAge : ages) {
                    int age = Integer.parseInt(stringAge);
                    fish.set(age, fish.get(age) + 1);
                }
            }
            long total = 0;
            for (long amount : birthCycle(fish, dayAmount)) {
                total += amount;
            }
            return total;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.print("Amount after 80 days: " + getFish(80));
        System.out.print("\nAmount after 256 days: " + getFish(256));
    }
}
