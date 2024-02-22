import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21317
public class Main {
    static int N, K;
    static int min = Integer.MAX_VALUE;
    static int[][] jump;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        jump = new int[N+1][2];

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            jump[i][0] = s; // 작은 점프
            jump[i][1] = b; // 큰 점프
        }//for

        K = Integer.parseInt(br.readLine());
        br.close();

        dfs(1, 0, false);
        System.out.print(min);
    }//main

    private static void dfs(int cur, int sum, boolean flag) {
        if(cur > N) return;
        if(cur == N) {
            min = Math.min(min, sum);
            return;
        }//if

        dfs(cur+1, sum + jump[cur][0], flag);
        dfs(cur+2, sum + jump[cur][1], flag);
        if(!flag) dfs(cur+3, sum+K, true);
    }//dfs

}//class