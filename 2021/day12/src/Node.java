import java.util.ArrayList;

public class Node {
    private final ArrayList<Node> connectedTo = new ArrayList<>();
    private final String name;

    public Node(String name) {
        this.name = name;
    }

    public void addConnection(Node node) {
        this.connectedTo.add(node);
    }

    public ArrayList<Node> getConnections() {
        return this.connectedTo;
    }

    public String getName() {
        return this.name;
    }
}
