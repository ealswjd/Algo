import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2666
public class Main {
    static int N, M;
    static int[] order; // 사용할 벽장들의 순서
    static int[][][] dp; // 이동 횟수

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 벽장의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] open = new int[2]; // 열려있는 두 개의 벽장
        open[0] = Integer.parseInt(st.nextToken());
        open[1] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine()); // 사용할 벽장들의 순서의 길이
        order = new int[M]; // 사용할 벽장들의 순서
        for(int i=0; i<M; i++) {
            order[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N+1][N+1][M];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int cnt = getCnt(open[0], open[1], 0);
        System.out.print(cnt);
    }//main

    
    private static int getCnt(int left, int right, int idx) {
        if(idx == M) return 0;
        if(dp[left][right][idx] != -1) {
            return dp[left][right][idx];
        }

        int target = order[idx]; // 사용할 벽장

        int moveLeft = Math.abs(left - target) + getCnt(target, right, idx+1);
        int moveRight = Math.abs(right - target) + getCnt(left, target, idx+1);

        return dp[left][right][idx] = Math.min(moveLeft, moveRight);
    }//getCnt

    
}//class