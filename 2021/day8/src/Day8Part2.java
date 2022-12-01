import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8Part2 {
    public static String sort(String inputString) {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static Boolean isContained(String big, String small) {
        boolean isContained = true;
        for (char smallChar : small.toCharArray()) {
            if (big.indexOf(smallChar) == -1) {
                isContained = false;
                break;
            }
        }
        return isContained;
    }

    public static String subtractString(String first, String second) {
        String result = first;
        for (char secondChar : second.toCharArray()) {
            if (result.indexOf(secondChar) != -1) {
                result = result.replace(Character.toString(secondChar), "");
            }
        }
        return result;
    }

    public static List<String> decryptNumbers(String[] exampleData) {
        String[] numbers = new String[10];
        ArrayList<String> length5 = new ArrayList<>();
        ArrayList<String> length6 = new ArrayList<>();
        for (String str : exampleData) {
            switch (str.length()) {
                case 2:
                    numbers[1] = sort(str);
                    break;
                case 3:
                    numbers[7] = sort(str);
                    break;
                case 4:
                    numbers[4] = sort(str);
                    break;
                case 5:
                    length5.add(sort(str));
                    break;
                case 6:
                    length6.add(sort(str));
                    break;
                case 7:
                    numbers[8] = sort(str);
                    break;
            }
        }
        for (String num : length5) {
            if (isContained(num, numbers[1])) {
                numbers[3] = sort(num);
            } else if (isContained(num, subtractString(numbers[4], numbers[1]))) {
                numbers[5] = sort(num);
            } else {
                numbers[2] = sort(num);
            }
        }
        for (String num : length6) {
            if (isContained(num, numbers[3])) {
                numbers[9] = sort(num);
            } else if (isContained(num, subtractString(numbers[8], subtractString(numbers[1], numbers[5])))) {
                numbers[6] = sort(num);
            } else {
                numbers[0] = sort(num);
            }
        }
        return Arrays.asList(numbers);
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day8/data/day8.txt"))) {
            String line;
            int total = 0;
            while ((line = br.readLine()) != null) {
                String[] exampleData = line.split("\\| ")[0].split(" ");
                String[] questionData = line.split("\\| ")[1].split(" ");
                List<String> numbersString = decryptNumbers(exampleData);
                StringBuilder number = new StringBuilder();
                for (String num : questionData) {
                    number.append(numbersString.indexOf(sort(num)));
                }
                total += Integer.parseInt(number.toString());
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
