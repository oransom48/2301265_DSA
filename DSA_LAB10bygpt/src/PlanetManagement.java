import java.util.*;

public class PlanetManagement {
    static class Edge {
        int destination, weight;
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // อ่านข้อมูลจำนวนประเทศ, เส้นทาง และค่าดำรงชีพ
        int N = sc.nextInt();
        int M = sc.nextInt();
        int C = sc.nextInt();

        // อ่านจำนวนคนที่ต้องการในแต่ละประเทศ
        int[] countryRequirements = new int[N];
        for (int i = 0; i < N; i++) {
            countryRequirements[i] = sc.nextInt();
        }

        // สร้างกราฟ
        List<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        // อ่านเส้นทางและค่าการเดินทาง
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int w = sc.nextInt();
            graph[a].add(new Edge(b, w));
        }

        // จำนวนคนทั้งหมดที่ต้องการส่งไปบริหาร
        int K = sc.nextInt();
        sc.close();

        // ใช้ Dijkstra หาเส้นทางเดินที่สั้นที่สุดจากประเทศ 1
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, 0});  // {distance, country}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int u = current[1];

            if (currentDist > dist[u]) continue;

            for (Edge edge : graph[u]) {
                int v = edge.destination;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{dist[v], v});
                }
            }
        }

        // คำนวณค่าใช้จ่ายทั้งหมดสำหรับแต่ละประเทศตามจำนวนคนที่ต้องการ
        List<Integer> costs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (dist[i] < Integer.MAX_VALUE) {
                int totalCostPerAdmin = dist[i] + C;
                for (int j = 0; j < countryRequirements[i]; j++) {
                    costs.add(totalCostPerAdmin);
                }
            }
        }

        // เรียงลำดับค่าใช้จ่ายจากน้อยไปมากเพื่อให้ผลลัพธ์ออกมาตามลำดับที่ถูกต้อง
        Collections.sort(costs);

        // ถ้าจำนวนคนไม่พอ ก็เพิ่ม -1 ในค่าใช้จ่าย
        while (costs.size() < K) {
            costs.add(-1);
        }

        // แสดงผลค่าใช้จ่ายของแต่ละคน
        for (int i = 0; i < K; i++) {
            System.out.println(costs.get(i));
        }
    }
}

//10 14 10000
//1 2 2 1 2 2 1 2 2 1
//1 2 1
//1 7 19
//1 9 13
//1 10 10
//2 3 4
//3 4 14
//3 6 4
//4 5 16
//4 7 6
//6 5 14
//7 3 1
//8 4 15
//9 8 8
//10 9 8
//20

//5 5 200000
//1 1 2 2 1
//1 2 20000
//1 3 10000
//2 4 10000
//3 4 30000
//3 5 10000
//6
