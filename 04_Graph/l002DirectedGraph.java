import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class l002DirectedGraph {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
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

    public static void dfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                dfs_topo(graph, e.v, vis, ans);
            }
        }

        ans.add(src);
    }

    public static void topo(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();
        int N = graph.length;
        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs_topo(graph, i, vis, ans);
            }
        }
        System.out.println(ans + " ");
    }

    public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] indegree = new int[N];
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (Edge e : graph[i]) {
                if (indegree[e.v] == 0) {
                    indegree[e.v]++;
                }
            }
        }

        for (int a : indegree) {
            System.out.print(a + " ");
        }

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }

        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans.add(rvtx);
            for (Edge e : graph[rvtx]) {
                if (--indegree[e.v] == 0) {
                    que.addLast(e.v);
                }
            }
        }

        if (ans.size() != N)
            ans.clear();

        return ans;
    }

    public static boolean dfs_topo_isCycle(ArrayList<Edge>[] graph, int src, int[] vis, ArrayList<Integer> ans) {
        vis[src] = 0;
        boolean res = false;

        for (Edge e : graph[src]) {
            if (vis[e.v] == -1) {
                res = res || dfs_topo_isCycle(graph, e.v, vis, ans);
            } else if (vis[e.v] == 0) {
                return true;
            }
        }

        vis[src] = 1;
        ans.add(src);
        return res;
    }

    public static ArrayList<Integer> dfs_topo_isCycle(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        ArrayList<Integer> ans = new ArrayList<>();

        boolean cycle = false;

        for (int i = 0; i < N; i++) {
            if (vis[i] == -1) {
                cycle = cycle || dfs_topo_isCycle(graph, i, vis, ans);
            }
        }

        if (cycle)
            ans.clear();

        return ans;
    }

    public static void constructGraph() {
        int N = 10;
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
        addEdge(graph, 7, 0, 10);
        addEdge(graph, 8, 0, 10);
        addEdge(graph, 1, 9, 10);

        // display(graph);
        // topo(graph);
        System.out.println(kahnsAlgo(graph));
        System.out.println(dfs_topo_isCycle(graph));

    }

    public static void main(String[] args) {
        constructGraph();
    }
}