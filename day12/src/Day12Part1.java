import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12Part1 {

    public static ArrayList<ArrayList<Node>> getPaths(ArrayList<Node> path) {
        Node current = path.get(path.size() - 1);
        ArrayList<ArrayList<Node>> result = new ArrayList<>();
        for (Node next : current.getConnections()) {
            if (Character.isLowerCase(next.getName().toCharArray()[0]) && path.contains(next)) {
                continue;
            }
            ArrayList<Node> nextPath = (ArrayList<Node>) path.clone();
            nextPath.add(next);
            if (nextPath.get(nextPath.size()-1).getName().equals("end")) {
                result.add(nextPath);
                continue;
            }
            result.addAll(getPaths(nextPath));
        }
        return result;
    }

    public static boolean listContainsNode(ArrayList<Node> list, Node node) {
        for (Node current : list) {
            if (current.getName().equals(node.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        Node startNode = new Node("start");
        nodes.add(startNode);
        try (BufferedReader br = new BufferedReader(new FileReader("day12/data/day12.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split("-");
                Node node1 = new Node(lines[0]);
                Node node2 = new Node(lines[1]);
                for (Node node : nodes) {
                    if (node.getName().equals(lines[0])) {
                        node1 = node;
                    }
                    if (node.getName().equals(lines[1])) {
                        node2 = node;
                    }
                }
                node1.addConnection(node2);
                node2.addConnection(node1);
                if (!listContainsNode(nodes, node1)) {
                    nodes.add(node1);
                }
                if (!listContainsNode(nodes, node2)) {
                    nodes.add(node2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Node> paths = new ArrayList<>();
        paths.add(startNode);
        ArrayList<ArrayList<Node>> allPaths = getPaths(paths);
        System.out.println(allPaths.size());
    }
}
