public class Position {
    private int xcor;
    private int ycor;
    private int value;

    public Position(int xcor, int ycor, int value) {
        this.xcor = xcor;
        this.ycor = ycor;
        this.value = value;
    }

    public int getYcor() {
        return this.ycor;
    }

    public int getXcor(int xcor) {
        return this.xcor;
    }

    public int getValue() {
        return this.value;
    }
}
