import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day14Part1 {
    public static final int AMOUNT_OF_STEPS = 10;

    public static void main(String[] args) {
        String polymer = "";
        Map<String, String> polymerTemplate = new HashMap<>();
        Map<String, Integer> amount = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("2021/day14/data/day14.txt"))) {
            String line = br.readLine();
            polymer = line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] mapping = line.split(" -> ");
                polymerTemplate.put(mapping[0], mapping[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] chars = polymer.toCharArray();
        for (char chari : chars) {
            amount.put("" + chari, amount.containsKey("" + chari) ? amount.get("" + chari) + 1 : 1);
        }
        for (int i = 0; i < AMOUNT_OF_STEPS; i++) {
            for (int j = 0; j < polymer.length() - 1; j++) {
                String letter = polymerTemplate.get("" + polymer.charAt(j) + polymer.charAt(j+1));
                amount.put(letter, amount.containsKey(letter) ? amount.get(letter) + 1 : 1);
                polymer = polymer.substring(0, j+1) + letter + polymer.substring(j+1);
                j++;
            }
        }
        System.out.println(Collections.max(amount.values()) - Collections.min(amount.values()));
    }
}
