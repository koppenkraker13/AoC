import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day1Part2 {
    public static final int WINDOW_SIZE = 3;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("day1/data/day1.txt"))) {
            String line;
            ArrayList<Integer> window = new ArrayList();
            int prevTotal = 0;
            int counter = -1;
            while ((line = br.readLine()) != null) {
                int current = Integer.parseInt(line);
                if (window.size() == WINDOW_SIZE) {
                    window.remove(0);
                    window.add(current);
                    int curTotal = window.get(0) + window.get(1) + window.get(2);
                    if (curTotal > prevTotal) {
                        counter++;
                    }
                    prevTotal = curTotal;
                } else {
                    window.add(current);
                }
            }
            System.out.println(counter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
