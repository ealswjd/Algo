import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1833
public class Main {
    private static int N; // 도시 개수
    private static int[][] cost; // 고속철도 설치 비용
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int totalCost = 0; // 총 비용
        int cnt = 0; // 연결된 철도 개수
        List<Edge> edges = new ArrayList<>();

        // 이미 고속철도가 설치되어 있는 도시 연결
        for(int i=1; i<N; i++) {
            for(int j=i+1; j<=N; j++) {
                if (cost[i][j] < 0) {
                    totalCost += cost[i][j];
                    if (union(i, j)) cnt++;
                } else {
                    edges.add(new Edge(i, j, cost[i][j]));
                }
            }
        }
        totalCost *= -1;
        Collections.sort(edges);

        int newCnt = 0; // 새로 설치한 고속철도의 개수
        StringBuilder newCity = new StringBuilder();
        for(Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                newCnt++;
                newCity.append(edge.from).append(' ')
                        .append(edge.to).append('\n');

                if (++cnt == N-1) break;
            }
        }

        StringBuilder ans = new StringBuilder();
        // 총 비용, 새로 설치한 고속철도의 개수
        ans.append(totalCost).append(' ');
        ans.append(newCnt).append('\n');
        ans.append(newCity);
        
        System.out.print(ans);
    }//sol

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        parents[b] = a;
        return true;
    }//union

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }//find

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N+1][N+1];
        parents = new int[N+1];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            parents[i] = i;
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }//init

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }//Edge

}//class