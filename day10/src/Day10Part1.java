import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10Part1 {

    public static ArrayList<String> getLines(boolean corrupt) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("day10/data/day10.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if((getIndexOfFirstWrongCharacter(stringToList(line)) != -1) == corrupt) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Character> stringToList(String input) {
        List<Character> chars = new ArrayList<>();
        for (char ch: input.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }

    public static ArrayList<Integer> getCorrectCharacters(List<Character> input) {
        ArrayList<Integer> alreadyChecked = new ArrayList<>();
        int nextAvailable = -1;
        for (int i = 0; i < input.size(); i++) {
            if (i < (input.size() - 1) && !alreadyChecked.contains(i)) {
                char current = input.get(i);
                for (int j = i + 1; j < input.size(); j++) {
                    if (!alreadyChecked.contains(j)) {
                        nextAvailable = j;
                        break;
                    }
                }
                char next = input.get(nextAvailable);
                if ((current == '[' && next == ']') || (current == '(' && next == ')') || (current == '{' && next == '}') || (current == '<' && next == '>')) {
                    alreadyChecked.add(i);
                    alreadyChecked.add(nextAvailable);
                    Collections.sort(alreadyChecked);
                    i = -1;
                }
            }
        }
        return alreadyChecked;
    }

    public static int getIndexOfFirstWrongCharacter(List<Character> input) {
        List<Integer> list = getCorrectCharacters(input);
        for (int i = 0; i < input.size(); i++) {
            if (!list.contains(i) && (input.get(i) == '}' || input.get(i) == ']' || input.get(i) == ')' || input.get(i) == '>')) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<String> corruptedLines = getLines(true);
        int result = 0;
        for (String line : corruptedLines) {
            int ans = getIndexOfFirstWrongCharacter(stringToList(line));
            switch (stringToList(line).get(ans)) {
                case ')':
                    result += 3;
                    break;
                case ']':
                    result += 57;
                    break;
                case '}':
                    result += 1197;
                    break;
                case '>':
                    result += 25137;
                    break;
            }
        }
        System.out.println(result);
    }

}
