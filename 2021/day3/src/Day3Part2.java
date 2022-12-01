import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3Part2 {

    private static ArrayList<String> bitReader(ArrayList<String> list, int position, boolean more) {
        ArrayList<String> zeroList = new ArrayList<>();
        ArrayList<String> oneList = new ArrayList<>();
        for (String line : list) {
            int bit = Integer.parseInt(String.valueOf(line.charAt(position)));
            if (bit == 0) {
                zeroList.add(line);
            } else {
                oneList.add(line);
            }
        }
        if (more) {
            return zeroList.size() > oneList.size() ? zeroList : oneList;
        } else {
            return zeroList.size() > oneList.size() ? oneList : zeroList;
        }
    }

    private static String getNumber(boolean more) {
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day3/data/day3.txt"))) {
            String line;
            int bitNR = 0;
            ArrayList<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            while (list.size() > 1) {
                list = bitReader(list, bitNR, more);
                bitNR++;
            }
            return list.get(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        int oxigen = Integer.parseInt(getNumber(true), 2);
        int co2 = Integer.parseInt(getNumber(false), 2);
        System.out.println("oxigen: " + oxigen);
        System.out.println("CO2: " + co2);
        System.out.println("FINAL: " + oxigen * co2);
    }
}
