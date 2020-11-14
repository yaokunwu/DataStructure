import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class WordPuzzle {

    private MyHashTable<String, Boolean> dictionary;
    private boolean useEnhancement;
    int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    WordPuzzle(String path, boolean enhancement) {
        dictionary = new MyHashTable<>();
        this.useEnhancement = enhancement;
        readFromFile(path);
    }

    public static char[][] generateGrid(int m, int n) {
        if (m < 1 || n < 1) {
            return new char[0][0];
        }

        char[][] grid = new char[m][n];
        Random rand = new Random();
        int size = m * n;
        System.out.println("Random generated grids of size " + m + " * " + n + " is displayed below: ");
        for (int i = 0; i < size; i++) {
            int num = rand.nextInt(26);
            grid[i / n][i % n] = (char) ('a' + num);
            System.out.print((char) ('a' + num));
            System.out.print(' ');
            if ((i + 1) % n == 0) {
                System.out.println();
            }
        }
        return grid;
    }

    public List<String> solve(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new ArrayList<>();
        }
        Set<String> set = new HashSet<>();
        int m = grid.length;
        int n = grid[0].length;
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                for (int k = 0; k < 8; k++) {
                    StringBuilder word = new StringBuilder();
                    int row = i, col = j;
                    for (int len = 0; len < Math.max(m, n); len++) {
                        word.append(grid[row][col]);
                        String currWord = word.toString();
                        if (useEnhancement && !dictionary.contains(currWord)) {
                            break;
                        }
                        if (dictionary.contains(currWord) && dictionary.get(currWord)) {
                            set.add(currWord);
                        }
                        row = row + directions[k][0];
                        col = col + directions[k][1];
                        if (row < 0 || col < 0 || row >= m || col >= n) {
                            break;
                        }
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    private void insert(String str) {
        if (!useEnhancement) {
            dictionary.insert(str, true);
            return;
        }

        StringBuilder sb = new StringBuilder();
        char[] s = str.toCharArray();
        int n = s.length;
        int i;
        for (i = 0; i < n; i++) {
            sb.append(s[i]);
            if (i == n - 1) {
                dictionary.insert(sb.toString(), true);
            } else {
                dictionary.insert(sb.toString(), false);
            }
        }
    }

    private void readFromFile(String path) {
        File f = new File(path);
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
        )
        {
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                insert(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please specify the path of a dictionary: ");
        String path = scanner.nextLine();

        while (true) {

            System.out.println("Please enter the number of row: ");
            int m = scanner.nextInt();
            if (m <= 0) {
                System.out.println("Invalid, restart again: ");
                continue;
            }
            System.out.println("Please enter the number of col: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Invalid, restart again: ");
                continue;
            }
            if ((long) m * (long)n > 400) {
                System.out.println("Grid size too large, restart again: ");
                continue;
            }

            char[][] board = generateGrid(m, n);

            long startTimeLoading1 = System.currentTimeMillis();
            WordPuzzle solver1 = new WordPuzzle(path, false);
            long endTimeLoading1 = System.currentTimeMillis();

            long startTimeLoading2 = System.currentTimeMillis();
            WordPuzzle solver2 = new WordPuzzle(path, true);
            long endTimeLoading2 = System.currentTimeMillis();

            System.out.println();

            long startTimeRunning1 = System.currentTimeMillis();
            List<String> res1 = solver1.solve(board);
            long endTimeRunning1 = System.currentTimeMillis( );

            long startTimeRunning2 = System.currentTimeMillis();
            List<String> res2 = solver2.solve(board);
            long endTimeRunning2 = System.currentTimeMillis( );

            System.out.println( "Elapsed time (not including time for loading dictionary) for basic algorithm: " + (endTimeRunning1 - startTimeRunning1) );
            System.out.println( "Elapsed time (not including time for loading dictionary) for enhanced algorithm: " + (endTimeRunning2 - startTimeRunning2) );

            System.out.println();
            System.out.println( "Elapsed time (including time for loading dictionary) for basic algorithm: " + ((endTimeRunning1 - startTimeRunning1) + (endTimeLoading1 - startTimeLoading1)) );
            System.out.println( "Elapsed time (including time for loading dictionary) for enhanced algorithm: " + ((endTimeRunning2 - startTimeRunning2) + (endTimeLoading2 - startTimeLoading2)) );

            System.out.println();
            System.out.println("Words captured by basic algorithm: ");
            System.out.println(res1);
            System.out.println();
            System.out.println("Words captured by enhanced algorithm: ");
            System.out.println(res2);
            System.out.println();

            System.out.println();
            System.out.println("One more time ? (y/n) ");

            char toContinue = scanner.next().charAt(0);
            if (toContinue != 'y') {
                break;
            }
        }
    }
}
