import java.util.ArrayList;

public class unionFind {
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
        if (par[u] == u) {
            return u;
        }
        return par[u] = findPar(par[u]); // path compression

        // return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public static void union(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }

    }

    public static void unionFindAlgo(int[][] edges) {
        int N = edges.length;

        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        par = new int[N];
        size = new int[N];

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

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 2, 1, 10);
        addEdge(graph, 3, 2, 10);
        addEdge(graph, 3, 4, 10);
        addEdge(graph, 4, 5, 10);
        addEdge(graph, 4, 6, 10);
        addEdge(graph, 5, 6, 10);

        // display(graph);
    }

    public static void main(String[] args) {
        constructGraph();
    }
}
