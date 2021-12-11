public class Octopuse {
    private boolean hasFlashed = false;
    private int charge;

    public Octopuse(int charge) {
        this.charge = charge;
    }

    public boolean increase() {
        if (!this.hasFlashed) {
            this.charge++;
        }
        if (this.charge > 9) {
            this.charge = 0;
            this.hasFlashed = true;
            return true;
        }
        return false;
    }

    public void setFlashFalse() {
        this.hasFlashed = false;
    }

    public boolean didFlash() {
        return this.hasFlashed;
    }
}
