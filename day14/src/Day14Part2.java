import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day14Part2 {
    public static final int AMOUNT_OF_STEPS = 40;
    private static char beginChar;
    public static char endChar;

    public static long MostMinLeast(Map<String, Long> polymer) {
        long leastFrequent = Long.MAX_VALUE;
        long mostFrequent = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            long frequency = 0;
            for (Map.Entry<String, Long> entry : polymer.entrySet()) {
                if (entry.getKey().charAt(0) == ch && entry.getKey().charAt(1) == ch) {
                    frequency += 2L * entry.getValue();
                } else if (entry.getKey().charAt(0) == ch || entry.getKey().charAt(1) == ch) {
                    frequency += entry.getValue();
                }
            }
            if (ch == beginChar || ch == endChar) {
                frequency++;
            }
            frequency = frequency/2;
            if (frequency != 0) {
                leastFrequent = Math.min(leastFrequent, frequency);
            }
            mostFrequent = Math.max(mostFrequent, frequency);
        }
        return mostFrequent - leastFrequent;
    }

    public static void main(String[] args) {
        String startString = "";
        Map<String, Long> polymer = new HashMap<>();
        Map<String, String> polymerTemplate = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("day14/data/day14.txt"))) {
            String line = br.readLine();
            startString = line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] mapping = line.split(" -> ");
                polymerTemplate.put(mapping[0], mapping[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        beginChar = startString.charAt(0);
        endChar = startString.charAt(startString.length()-1);
        for (int i = 0; i < startString.length() - 1; i++) {
            String sub = startString.substring(i, i+2);
            polymer.put(sub, polymer.containsKey(sub) ? polymer.get(sub) + 1 : 1);
        }
        for (int i = 0; i < AMOUNT_OF_STEPS; i++) {
            Map<String, Long> temp = new HashMap<>();
            for (var entry : polymer.entrySet()) {
                String string = entry.getKey().charAt(0) + polymerTemplate.get(entry.getKey()) + entry.getKey().substring(1);
                String part1 = string.substring(0, 2);
                String part2 = string.substring(1);
                temp.put(part1, (temp.containsKey(part1) ? temp.get(part1) : 0) + entry.getValue());
                temp.put(part2, (temp.containsKey(part2) ? temp.get(part2) : 0) + entry.getValue());
            }
            polymer = temp;
        }
        System.out.println(MostMinLeast(polymer));
    }
}
