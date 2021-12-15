import java.util.ArrayList;

public class Position {
    private boolean startNode;
    private int riskBestPath = 0;
    private int riskLevel;
    private ArrayList<Position> neighbours;

    public Position(int riskLevel, boolean startNode, ArrayList<Position> neighbours) {
        this.riskLevel = riskLevel;
        this.startNode = startNode;
        this.neighbours = neighbours;
        getLowestRiskPath();
    }

    public int getLowestRiskPath() {
        if (startNode) {
            return 0;
        }
        if (this.riskBestPath != 0) {
            return riskBestPath;
        }
        int lowestRisk = Integer.MAX_VALUE;
        for (Position neighbour : neighbours) {
            lowestRisk = Math.min(lowestRisk, neighbour.getLowestRiskPath());
        }
        this.riskBestPath = lowestRisk + this.riskLevel;
        return this.riskBestPath;
    }

}
