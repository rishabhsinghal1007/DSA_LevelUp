import java.util.ArrayList;
import java.util.Arrays;

public class kruskalAlgorithm {

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

    static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public static void union(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p2] = p1;
            size[p1] += p2;
        } else {
            par[p1] = p2;
            size[p2] += p1;
        }
    }

    public static void unionFind(ArrayList<Edge>[] graph, int[][] edges, int n) {
        par = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            par[i] = i;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    public static void kruskalAlgo(int[][] edges, int N) {
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        unionFind(graph, edges, N);
    }

}