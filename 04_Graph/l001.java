import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class l001 {

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

    // TC: O(2E)
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

    // TC: O(E)
    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for (int i = 0; i < list.size(); i++) {
            Edge e = list.get(i);
            if (e.v == v)
                return i;
        }
        return -1;
    }

    // TC: O(E)
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx = findEdge(graph, u, v);
        graph[u].remove(idx);

        idx = findEdge(graph, v, u);
        graph[v].remove(idx);
    }

    // hasPath
    // TC: O(E), where E is total no of edges in that particular component.
    public static boolean dfs_findPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;

        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                res = res || dfs_findPath(graph, e.v, dest, vis);
        }

        return res;
    }

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, String psf, int wsf, boolean[] vis) {
        if (src == dest) {
            System.out.println(psf + dest + " @ " + wsf);
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                count += printAllPath(graph, e.v, dest, psf + src, wsf + e.w, vis);
        }
        vis[src] = false;

        return count;
    }

    public static void dfs_GCC(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_GCC(graph, e.v, vis);
        }
    }

    // GCC
    // TC: O(E + V)
    public static void getConnectedComponent(ArrayList<Edge>[] graph) {
        int components = 0, N = graph.length;
        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                components++;
                dfs_GCC(graph, i, vis);
            }
        }

        System.out.println(components);
    }

    public static void BFS_WithCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        int level = 0, cycleCount = 0;
        boolean iscycle = false;

        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);

        while (que.size() != 0) {
            int size = que.size();
            System.out.print(level + " -> ");

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                if (vis[rvtx]) {
                    cycleCount++;
                    iscycle = true;
                    continue;
                }

                System.out.print(rvtx + " ");
                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
        System.out.println("CycleCount : " + cycleCount);
        System.out.println("Is Cycle Present : " + iscycle);
    }

    public static void BFS_WithoutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        int level = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        vis[src] = true;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(level + ": ");

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                System.out.print(rvtx + " ");
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        que.addLast(e.v);
                        vis[e.v] = true;
                    }
                }
            }

            System.out.println();
            level++;
        }
    }

    public static boolean is_Bipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int color = 0; // 0 : red , 1 : green
        boolean isBipartite = true;
        boolean isCycle = false;

        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                int rvtx = que.removeFirst();

                if (vis[rvtx] != -1) {
                    isCycle = true;
                    if (vis[rvtx] != color) {
                        isBipartite = false;
                        break;
                    }
                    continue;
                }

                vis[rvtx] = color;

                for (Edge e : graph[rvtx]) {
                    if (vis[e.v] == -1) {
                        que.addLast(e.v);
                    }
                }

            }
            color = (color + 1) % 2;

            if (!isBipartite)
                break;
        }
        if (isCycle) {
            if (isBipartite)
                System.out.println("Graph is Bi-Partite it means it has even length cycle");
            else
                System.out.println("Graph is Non Bi-Partite it means it has odd length cycle");

        } else {
            System.out.println("Graph is Bi-Partite");
        }

        return isBipartite;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);

        boolean res = true;
        for (int i = 0; i < N; i++) {
            if (vis[i] == -1) {
                res = res && is_Bipartite(graph, i, vis);
            }
        }
        return res;

    }

    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);
        // addEdge(graph, 2, 7, 10);
        // addEdge(graph, 2, 8, 10);
        // addEdge(graph, 7, 8, 10);

        // display(graph);
        boolean[] vis = new boolean[N];
        // System.out.println(dfs_findPath(graph, 0, 6, vis));
        // System.out.println(printAllPath(graph, 0, 6, "", 0, vis));
        // getConnectedComponent(graph);
        // BFS_WithCycle(graph, 0, vis);
        // BFS_WithoutCycle(graph, 0, vis);
        System.out.println(isBipartite(graph));
    }

    public static void main(String[] args) {
        constructGraph();
    }
}