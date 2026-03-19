import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13905
public class Main {
    private static int s, e; // 출발, 도착 위치
    private static int[] parent; // 연결
    private static Edge[] edges; // 다리 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int maxCnt = 0; // 금빼빼로의 최대 개수

        for(Edge cur : edges) {
            // 현재 다리 연결
            union(cur.from, cur.to);

            // 출발지, 도착지 연결 완료
            if (find(s) == find(e)) {
                maxCnt = cur.cnt;
                break;
            }
        }

        // 숭이가 들고 갈 수 있는 금빼빼로의 최대 개수를 출력
        System.out.print(maxCnt);
    }//sol

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a <= b) parent[b] = a;
        else parent[a] = b;
    }//union

    private static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }//find

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 집의 수 N(2≤N≤100,000)와 다리의 수 M(1≤M≤300,000)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edges = new Edge[M]; // 다리 연결 정보

        // 출발 위치(s)와 혜빈이의 위치(e)가 주어진다
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // M개의 줄에는 다리의 정보가 주어진다.
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, cnt);
        }
        br.close();

        // 정렬
        Arrays.sort(edges);

        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
    }//init

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cnt;
        Edge(int from, int to, int cnt) {
            this.from = from;
            this.to = to;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Edge e) {
            return e.cnt - this.cnt;
        }
    }//Edge

}//class