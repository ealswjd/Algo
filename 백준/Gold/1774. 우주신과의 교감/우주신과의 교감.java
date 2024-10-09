import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1774
public class Main {
    static final int X = 0, Y = 1;
    private static int N, M;
    private static int[][] xy;
    private static int[] parent;

    
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double dist;
        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.dist, e.dist);
        }
    }

    
    public static void main(String[] args) throws IOException {
        init();
        getMin("%.2f");
    }//main

    
    private static void getMin(String format) {
        List<Edge> list = new ArrayList<>();

        for(int i=1; i<=N; i++) {
            for(int j=i+1; j<=N; j++) {
                if(find(i) == find(j)) continue;

                list.add(new Edge(i, j, getDist(i, j)));
            }
        }

        Collections.sort(list);

        double total = 0.0;
        for(Edge edge : list) {
            if(find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                total += edge.dist;
            }
        }

        System.out.printf(format, total);
    }//getMin

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        xy = new int[N+1][2];
        parent = new int[N+1];

        for(int i=0; i<=N; i++) {
            parent[i] = i;
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            xy[i][X] = Integer.parseInt(st.nextToken());
            xy[i][Y] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to);
        }

    }//init

    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }//union

    
    private static int find(int n) {
        if(parent[n] == n) return n;

        return parent[n] = find(parent[n]);
    }//find

    
    private static double getDist(int from, int to) {
        double x = Math.pow(xy[from][X] - xy[to][X], 2);
        double y = Math.pow(xy[from][Y] - xy[to][Y], 2);

        return Math.sqrt(x + y);
    }//getDist

    
}//class