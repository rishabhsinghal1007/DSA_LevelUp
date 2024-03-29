import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class dijikstraAlgo {
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

    public static class pair {
        int vtx, par, w, wsf;

        // for dijikstra1
        pair(int vtx, int par, int w, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }

        // for dijikstra2
        pair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    public static void dijikstra_1(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;

        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int[] dis = new int[N];
        int[] par = new int[N];

        pq.add(new pair(src, -1, 0, 0));

        while (pq.size() != 0) {
            pair p = pq.remove();

            if (vis[p.vtx])
                continue;

            if (p.par != -1) {
                addEdge(ngraph, p.vtx, p.par, p.w);
            }

            vis[p.vtx] = true;

            dis[p.vtx] = p.wsf;
            par[p.vtx] = p.par;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    pq.add(new pair(e.v, p.vtx, e.w, p.wsf + e.w));
                }
            }
        }

        display(ngraph);
    }

    public static void dijikstra_2(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int[] dis = new int[N];
        int[] par = new int[N];

        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        pq.add(new pair(src, 0));
        dis[src] = 0;

        while (pq.size() != 0) {
            pair p = pq.remove();

            if (p.wsf > dis[p.vtx]) {
                continue;
            }

            for (Edge e : graph[p.vtx]) {
                if (p.wsf + e.w < dis[e.v]) {
                    dis[e.v] = p.wsf + e.w;
                    par[e.v] = p.vtx;

                    pq.add(new pair(e.v, p.wsf + e.w));
                }
            }
        }
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

        // dijikstra_1(graph, 0);
        dijikstra_2(graph, 0);
    }

    public static void main(String[] args) {
        constructGraph();
    }
}