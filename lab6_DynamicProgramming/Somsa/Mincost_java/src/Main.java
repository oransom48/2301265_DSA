import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double perimeter(ArrayList<Point> vertices, int i, int j, int k) {
        Point p1 = vertices.get(i);
        Point p2 = vertices.get(j);
        Point p3 = vertices.get(k);
        return dist(p1, p2) + dist(p2, p3) + dist(p1, p3);
    }

    public static double minCost(ArrayList<Point> vertices, int n) {
        if (n < 3) return 0;

        // min cost table
        double[][] table = new double[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (j < i + 2) {
                    table[i][j] = 0;
                } else {
                    table[i][j] = Double.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        double temp = table[i][k] + table[k][j] + perimeter(vertices, i, j, k);
                        if (temp < table[i][j]) {
                            table[i][j] = temp;
                        }
                    }
                }
            }
        }

        return table[0][n-1];
    }

    public static void printVertice(int[][] index, int i, int j) {
        if (j < i + 2) {
            return;
        } else {
            System.out.printf("[%d %d %d] ", i, index[i][j], j);
            printVertice(index, i, index[i][j]);
            printVertice(index, index[i][j], j);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // input
        int n = input.nextInt();
        ArrayList<Point> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            vertices.add(new Point(x, y));
        }

        if (n < 3) {
            System.out.print("can't do a triangulation");
        } else {
            // min cost table
            double[][] table = new double[n][n];
            // tracking table
            int[][] track = new int[n][n];

            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = gap; j < n; i++, j++) {
                    if (j < i + 2) {
                        table[i][j] = 0;
                    } else {
                        table[i][j] = Double.MAX_VALUE;
                        for (int k = i + 1; k < j; k++) {
                            double temp = table[i][k] + table[k][j] + perimeter(vertices, i, j, k);
                            if (temp < table[i][j]) {
                                table[i][j] = temp;
                                track[i][j] = k;
                            }
                        }
                    }
                }
            }

            // print minimum cost
            System.out.printf("minimum cost = %.2f\n", table[0][n-1]);

            // print list of point to form triangle
            printVertice(track, 0, n-1);
        }

    }
}

/*
5
0 0
1 0
2 1
1 2
0 2
 */
