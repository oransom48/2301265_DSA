import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static String getPath(int startNode, int finishNode, int[] predecessor) {
        if (predecessor[finishNode] == -1) {
            return "No path";
        }

        ArrayList<Integer> path = new ArrayList<>();
        int i = finishNode;
        path.add(i + 1); // convert to 1-based indexing

        while (i != startNode) {
            i = predecessor[i];
            path.addFirst(i + 1); // insert at the beginning
        }

        StringBuilder pathStr = new StringBuilder();
        for (int node : path) {
            pathStr.append(node).append(" ");
        }

        return pathStr.toString().trim();
    }

    public static String printPath(int startNode, int finishNode, int[] predecessor) {
        int i = finishNode;
        StringBuilder answer = new StringBuilder(Integer.toString(i + 1));

        while (i != startNode) {
            i = predecessor[i];
            answer.insert(0, (i + 1) + " ");
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // input
        int n = input.nextInt();
        int m = input.nextInt();
        int c = input.nextInt();

        int[] maxLeader = new int[n];
        for (int i = 0; i < n; i++) {
            maxLeader[i] = input.nextInt();
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

        // print the traveling expense and path from country 1 -> country n
        System.out.println("\ntraveling expense, total expense, and path from country 1 -> country n");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + dist[i] + "\t" + (dist[i] + c) + "\t" + printPath(0, i, predecessor));
        }

        // answer the main task
        PriorityQueue<int[]> costs = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < maxLeader[i]; j++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    costs.add(new int[]{dist[i] + c, i}); // Total cost + destination country index
                }
            }
        }

        // Output the total costs and paths for each of the K people
        System.out.println();
        for (int i = 0; i < k; i++) {
            if (costs.isEmpty()) {
                System.out.println("-1"); // Not enough assignments available
            } else {
                int[] assignment = costs.poll();
                int totalCost = assignment[0];
                int destination = assignment[1];

                String path = getPath(0, destination, predecessor); // Path from country 1 to the destination
                System.out.println("Cost: " + totalCost + ",\tPath: " + path);
            }
        }

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

10 14 10000
1 2 2 1 2 2 1 2 2 1
1 2 1
1 7 19
1 9 13
1 10 10
2 3 4
3 4 14
3 6 4
4 5 16
4 7 6
6 5 14
7 3 1
8 4 15
9 8 8
10 9 8
20
*/
