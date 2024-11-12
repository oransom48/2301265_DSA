import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // input
        int n = input.nextInt();
        int m = input.nextInt();
        int c = input.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }

        // graph representation using adjacency matrix
        int[][] graph = new int[n][n];
        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            graph[x - 1][y - 1] = input.nextInt();
        }

        int k = input.nextInt();

        // initialize single source
        int[] dist = new int[n];
        int[] predecessor = new int[n];
        boolean[] sptSet = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
            sptSet[i] = false;
        }
        dist[0] = 0;

        // find the shortest path
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int u = -1;

            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && dist[v] < min) {
                    min = dist[v];
                    u = v;
                }
            }

            sptSet[u] = true;

            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]
                ) {
                    dist[v] = dist[u] + graph[u][v];
                    predecessor[v] = u;
                }
            }
        }

        /*
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + dist[i]);
        }
         */


    }
}

/*
5 5 200000
1 1 2 2 1
1 2 20000
1 3 10000
2 4 10000
3 4 30000
3 5 10000
6
*/
