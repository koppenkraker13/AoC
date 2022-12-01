import java.util.ArrayList;

public class Card {
    private final int CARD_SIZE = 5;
    private ArrayList<Integer> cardRepresentation = new ArrayList<>();
    private ArrayList<Integer> markedPositions = new ArrayList<>();

    public Card(ArrayList<Integer> cardRepresentation) {
        this.cardRepresentation = cardRepresentation;
    }

    public int markValue(int value) {
        if (cardRepresentation.contains(value)) {
            int pos = cardRepresentation.indexOf(value);
            markedPositions.add(pos);
            return pos;
        }
        return -1;
    }

    public boolean bingo() {
        if (bingoColumn() || bingoRow()) {
            return true;
        }
        return false;
    }

    public int sumRemainingValues() {
        ArrayList<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < cardRepresentation.size(); i++) {
            if(!markedPositions.contains(i)) {
                remaining.add(this.cardRepresentation.get(i));
            }
        }
        int sum = 0;
        for (int value : remaining) {
            sum += value;
        }
        return sum;
    }

    private boolean bingoRow() {
        for (int y = 0; y < CARD_SIZE; y++) {
            boolean possibleBingo = true;
            for (int x = 0; x < CARD_SIZE; x++) {
                if (!possibleBingo) {
                    break;
                } else {
                    possibleBingo = markedPositions.contains(corToIndex(x, y));
                }
            }
            if (possibleBingo) {
                return true;
            }
        }
        return false;
    }

    private boolean bingoColumn() {
        for (int x = 0; x < CARD_SIZE; x++) {
            boolean possibleBingo = true;
            for (int y = 0; y < CARD_SIZE; y++) {
                if (!possibleBingo) {
                    break;
                } else {
                    possibleBingo = markedPositions.contains(corToIndex(x, y));
                }
            }
            if (possibleBingo) {
                return true;
            }
        }
        return false;
    }

    public int corToIndex(int xcor, int ycor) {
        if (xcor >= CARD_SIZE || ycor >= CARD_SIZE) {
            return -1;
        }
        return ycor * 5 + xcor;
    }
}
