import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class primsAlgo {

    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static class primsPair {
        int vtx, par, w;

        primsPair(int vtx, int par, int w) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
        }

        primsPair(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }

    public static void prims_1(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;

        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            ngraph[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[N];

        PriorityQueue<primsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });

        int[] dis = new int[N];
        int[] par = new int[N];

        pq.add(new primsPair(src, -1, 0));

        while (pq.size() != 0) {
            primsPair p = pq.remove();

            if (vis[p.vtx]) {
                continue;
            }

            if (p.par != -1) {
                addEdge(ngraph, p.vtx, p.par, p.w);
            }

            vis[p.vtx] = true;

            dis[p.vtx] = p.w;
            par[p.vtx] = p.par;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    pq.add(new primsPair(e.v, p.vtx, e.w));
                }
            }
        }
        // display(ngraph);
        for (int a : dis) {
            System.out.print(a + " ");
        }
        System.out.println();
        // for (int a : par) {
        // System.out.print(a + " ");
        // }
        System.out.println();
    }

    public static void prims_2(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<primsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });

        boolean[] vis = new boolean[N];

        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);

        dis[src] = 0;
        pq.add(new primsPair(src, 0));
        while (pq.size() != 0) {
            primsPair p = pq.remove();

            if (vis[p.vtx])
                continue;

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v] && e.w < dis[e.v]) {
                    dis[e.v] = e.w;
                    pq.add(new primsPair(e.v, e.w));
                }
            }
        }

        for (int a : dis) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void constructGraph() {
        int N = 9;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 4);
        addEdge(graph, 1, 2, 8);
        addEdge(graph, 2, 3, 7);
        addEdge(graph, 3, 4, 9);
        addEdge(graph, 4, 5, 10);
        addEdge(graph, 5, 6, 2);
        addEdge(graph, 6, 7, 1);
        addEdge(graph, 0, 7, 8);
        addEdge(graph, 6, 8, 6);
        addEdge(graph, 2, 8, 2);
        addEdge(graph, 2, 5, 4);
        addEdge(graph, 3, 5, 14);
        addEdge(graph, 1, 7, 11);
        addEdge(graph, 7, 8, 7);

        prims_1(graph, 0);
        prims_2(graph, 0);
    }

    public static void main(String[] args) {
        constructGraph();
    }
}
