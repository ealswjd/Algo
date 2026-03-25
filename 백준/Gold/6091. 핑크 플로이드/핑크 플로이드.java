import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6091
public class Main {
    private static int N; // 정점의 수
    private static int[] parent;
    private static List<Edge> edges; // 연결 정보
    private static List<List<Integer>> tree; // 원래 트리

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // 리스트 형태로 원래의 트리 연결
        for(Edge cur : edges) {
            int from = cur.from;
            int to = cur.to;

            if (union(from, to)) {
                tree.get(from).add(to);
                tree.get(to).add(from);
            }
        }

        // N개의 줄에 인접 리스트 형태로 트리를 출력
        StringBuilder ans = new StringBuilder();
        for(int i=1; i<=N; i++) {
            List<Integer> list = tree.get(i);
            Collections.sort(list); // 오름차순으로 출력

            // 해당 노드와 연결되어 있는 정점의 수를 출력
            ans.append(list.size()).append(' ');
            // 출력한 수만큼 연결된 정점의 번호를 출력하면 된다
            for(int node : list) {
                ans.append(node).append(' ');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//sol

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (a < b) parent[b] = a;
        else parent[a] = b;

        return true;
    }//union

    private static int find(int n) {
        if (n == parent[n]) return n;
        return parent[n] = find(parent[n]);
    }//find

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정점의 수

        parent = new int[N+1];
        edges = new ArrayList<>();
        tree = new ArrayList<>(N+1);

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=i+1; j<=N; j++) {
                int dist = Integer.parseInt(st.nextToken());
                edges.add(new Edge(i, j, dist));
            }
        }
        br.close();

        Collections.sort(edges);

        for(int i=0; i<=N; i++) {
            parent[i] = i;
            tree.add(new ArrayList<>());
        }
    }//init

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;
        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }//Edge

}//class