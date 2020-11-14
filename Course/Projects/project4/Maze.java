import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Maze {
    int m, n, c;
    boolean[][] top;
    boolean[][] bottom;
    boolean[][] left;
    boolean[][] right;
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public Maze(int rows, int cols) {
        this.m = rows;
        this.n = cols;
        this.c = m * n;
        top = new boolean[m][n];
        bottom = new boolean[m][n];
        left = new boolean[m][n];
        right = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                top[i][j] = true;
                bottom[i][j] = true;
                left[i][j] = true;
                right[i][j] = true;
            }
        }
        // maze in to and out of
        left[0][0] = false;
        right[m - 1][n - 1] = false;
        top[0][0] = false;
        bottom[m - 1][n - 1] = false;
    }

    public void generatMaze() {
        DisjSets uf = new DisjSets(c);
        Set<Integer> unvisited = new HashSet<>();
        for (int i = 0; i < c; i++) {
            unvisited.add(i);
        }
        // till fully connected
        while (unvisited.size() != 0) {
            Iterator<Integer> it = unvisited.iterator();
            while (it.hasNext()) {
                int curr = it.next();
                if (uf.find(curr) == uf.find(0)) {
                    it.remove();
                }
            }
            Random rand1 = new Random();
            int node = rand1.nextInt(c);
            int directIdx = rand1.nextInt(4);
            int[] direction = directions[directIdx];
            int row = node / n;
            int col = node % n;
            int adjNodeRow = row + direction[0];
            int adjNodeCol = col + direction[1];
            if (adjNodeRow < 0 || adjNodeRow >= m || adjNodeCol < 0 || adjNodeCol >= n) {
                continue;
            }
            int adjNode = adjNodeRow * n + adjNodeCol;
            if (uf.find(node) != uf.find(adjNode)) {
                if (directIdx == 0) {
                    right[row][col] = false;
                    left[adjNodeRow][adjNodeCol] = false;
                } else if (directIdx == 1) {
                    bottom[row][col] = false;
                    top[adjNodeRow][adjNodeCol] = false;
                } else if (directIdx == 2) {
                    left[row][col] = false;
                    right[adjNodeRow][adjNodeCol] = false;
                } else {
                    top[row][col] = false;
                    bottom[adjNodeRow][adjNodeCol] = false;
                }
                uf.union(uf.find(node), uf.find(adjNode));
            }
        }
    }
}
