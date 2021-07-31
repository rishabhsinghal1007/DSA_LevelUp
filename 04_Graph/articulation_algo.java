import java.util.ArrayList;

public class articulation_algo {

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

    static int[] low, disc;
    static boolean[] articulation, vis;
    static int time = 0, rootcalls = 0;

    public static void dfs(ArrayList<Edge>[] graph, int src, int par) {
        low[src] = disc[src] = time++;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                if (par == -1) {
                    rootcalls++;
                }

                dfs(graph, e.v, src);
                if (disc[src] <= low[e.v]) {
                    articulation[src] = true;
                }
                if (disc[src] < low[e.v]) {
                    System.out.println("Articulation Edge: " + src + "->" + e.v + ". ");
                }

                low[src] = Math.min(low[src], low[e.v]);

            } else if (e.v != par) {
                low[src] = Math.min(low[src], disc[e.v]);
            }
        }
    }

    public static void articulationPointAndBridges(ArrayList<Edge>[] graph) {
        int n = graph.length;
        graph = new ArrayList[n];
        low = new int[n];
        disc = new int[n];
        vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1);
            }
        }
    }
}