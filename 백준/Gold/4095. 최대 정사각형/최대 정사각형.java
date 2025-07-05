import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4095
public class Main {
    private static int N, M; // 행렬의 크기
    private static int[][] map; // 1과 0으로 이루어진 NxM 크기의 행렬


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static int getMax() {
        int[][] dp = new int[N+1][M+1]; // (i, j)가 정사각형의 우측 하단일 때 정사각형의 크기
        int max = 0; // 가장 큰 정사각형의 너비 또는 높이

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(map[i][j] == 0) continue;

                // 좌상, 상, 좌 확인
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }//getMax


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0) break;

            // 1과 0으로 이루어진 NxM 크기의 행렬
            map = new int[N+1][M+1];

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가장 큰 정사각형의 너비 또는 높이를 출력
            int max = getMax();
            ans.append(max).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


}//class