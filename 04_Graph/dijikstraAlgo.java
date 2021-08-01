import java.util.ArrayList;
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

    // O(2E)
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

        pair(int vtx, int par, int w, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }
    }

    public static void dijikstra_01(ArrayList<Edge>[] graph, int src) {
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

            if (p.par != -1)
                addEdge(ngraph, p.vtx, p.par, p.w);

            vis[p.vtx] = true;

            dis[p.vtx] = p.wsf;
            par[p.vtx] = p.par;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v])
                    pq.add(new pair(e.v, p.vtx, e.w, p.wsf + e.w));
            }
        }
    }
}
