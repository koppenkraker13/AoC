import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4Part2 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("day4/data/day4"))) {
            String line;

            line = br.readLine();
            String[] rawNumbers = line.split(",");
            for (String num : rawNumbers) {
                numbers.add(Integer.parseInt(num));
            }
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> values = new ArrayList<>();
                if (!line.equals("")) {
                    for (int i = 0; i < 5; i++) {
                        String[] rawValues = line.split(" ");
                        for (String value : rawValues) {
                            if (!value.equals("")) {
                                values.add(Integer.parseInt(value));
                            }
                        }
                        line = br.readLine();
                    }
                    cards.add(new Card(values));
                }
            }
            ArrayList<Card> bingoCards = new ArrayList<>();
            int lastNumber = 0;
            Card lastCard = cards.get(0);
            for (int number : numbers) {
                bingoCards = new ArrayList<>();
                boolean bingo = false;
                for (Card card : cards) {
                    card.markValue(number);
                    if (card.bingo()) {
                        bingoCards.add(card);
                        if (cards.size() == 1) {
                            lastCard = card;
                            lastNumber = number;
                        }
                    }
                }
                for (Card card : bingoCards) {
                    cards.remove(card);
                }
                if (cards.size() == 0) {
                    break;
                }
            }
            System.out.println(lastCard.sumRemainingValues() * lastNumber);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
