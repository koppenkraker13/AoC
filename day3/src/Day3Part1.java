import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("day3/data/day3"))) {
            String line;
            double counter = 0;
            ArrayList<Integer> zeroCounter = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                if (counter == 0) {
                    for (int i = 0; i < line.length(); i++) {
                        zeroCounter.add(0);
                    }
                }
                for (int i = 0; i < line.length(); i++) {
                    int bit = Integer.parseInt(String.valueOf(line.charAt(i)));
                    if (bit == 0) {
                        int plusOne = zeroCounter.get(i) + 1;
                        zeroCounter.set(i, plusOne);
                    }
                }
                counter++;
            }
            String gamma = "";
            for (double zeroCount : zeroCounter) {
                if ((zeroCount / counter) >= 0.500) {
                    gamma += "0";
                } else {
                    gamma += "1";
                }
            }
            String epsilon = gamma;
            System.out.println(epsilon);
            epsilon = epsilon.replaceAll("0", "x");
            epsilon = epsilon.replaceAll("1", "0");
            epsilon = epsilon.replaceAll("x", "1");
            System.out.println(epsilon);
            int gammaRate = Integer.parseInt(gamma, 2);
            int epsilonRate = Integer.parseInt(epsilon, 2);

            System.out.println("gammaRate: " + gammaRate);
            System.out.println("epsilonRate: " + epsilonRate);
            System.out.println("FINAL: " + gammaRate * epsilonRate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
