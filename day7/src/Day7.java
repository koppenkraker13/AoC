import java.io.*;
import java.util.ArrayList;

public class Day7 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("day7/data/day7.txt"))) {
            ArrayList<Integer> positions = new ArrayList<>();
            int maxDistance = 0;
            double totalDistance = 0;
            int totalFuelCostPart1 = Integer.MAX_VALUE;
            int totalFuelCostPart2 = Integer.MAX_VALUE;
            String[] stringPos = br.readLine().split(",");
            for (String pos : stringPos) {
                int intPos = Integer.parseInt(pos);
                positions.add(intPos);
                totalDistance += intPos;
                maxDistance = Math.max(maxDistance, intPos);
            }
            for (int i = maxDistance; i >= 0; i--) {
                int tempFuelCostPart1 = 0;
                int tempFuelCostPart2 = 0;
                for (int pos : positions) {
                    tempFuelCostPart1 += Math.abs(pos - i);
                    int n = Math.abs(pos - i);
                    tempFuelCostPart2 += n*(n+1)/2;
                }
                totalFuelCostPart1 = Math.min(tempFuelCostPart1, totalFuelCostPart1);
                totalFuelCostPart2 = Math.min(tempFuelCostPart2, totalFuelCostPart2);
            }
            System.out.println("Minimized total fuel cost part1: " + totalFuelCostPart1);
            System.out.println("Minimized total fuel cost part2: " + totalFuelCostPart2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
