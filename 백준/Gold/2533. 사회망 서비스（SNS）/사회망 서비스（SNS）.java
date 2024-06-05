import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2533
public class Main {
    static int N; // 친구 관계 트리의 정점 개수
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 친구 관계 트리의 정점 개수

        init();

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(u).add(v);
            list.get(v).add(u);
        }
        br.close();

        int n = 1;
        dfs(n);

        System.out.print(Math.min(dp[n][0], dp[n][1]));
    }//main

    private static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][0] = 1;

        for(int next : list.get(cur)) {
            if(visited[next]) continue;
            dfs(next);
            dp[cur][0] += Math.min(dp[next][0], dp[next][1]); // 얼리 아답터
            dp[cur][1] += dp[next][0]; // 일반
        }
    }//dfs

    private static void init() {
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class