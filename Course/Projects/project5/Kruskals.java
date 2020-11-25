import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Kruskals {

    private class Edge {
        String from;
        String to;
        int distance;

        Edge(String from, String to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return from + ", " + to + " -> " + distance;
        }
    }

    public void solve(String inputData) {
        String[] inputs = inputData.split("\\n");
        Map<String, Integer> id = new HashMap<>();
        int idx = 0;
        int num = inputs.length;

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        for (String input : inputs) {
            String[] data = input.split(",");
            int n = data.length;
            String key = data[0];
            id.put(key, idx);
            for (int i = 1; i < n - 1; i += 2) {
                Edge currNode = new Edge(key, data[i], Integer.parseInt(data[i + 1]));
                minHeap.offer(currNode);
            }
            idx++;
        }

        DisjSets uf = new DisjSets(num);
        List<Edge> res = new ArrayList<>();
        int DistSum = 0;
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int from = id.get(edge.from);
            int to = id.get(edge.to);
            if (uf.find(from) != uf.find(to)) {
                DistSum += edge.distance;
                res.add(edge);
                uf.union(uf.find(from), uf.find(to));
            }
        }

        System.out.println();
        System.out.println("Edges in the minimum spanning tree: ");
        System.out.println("-----------------------------------");
        for (Edge edge : res) {
            System.out.println(edge);
        }
        System.out.println("-----------------------------------");
        System.out.print("The sum of all of the distances in the tree = ");
        System.out.println(DistSum);
    }

    public static void main(String[] args) throws Exception {
        String inputData = "";
        String thisLine = null;
        BufferedReader br = new BufferedReader(new FileReader("assn9_data.csv"));
        while ((thisLine = br.readLine()) != null) {
            inputData += thisLine + "\n";
        }
        Kruskals solver = new Kruskals();
        solver.solve(inputData);
    }

}
