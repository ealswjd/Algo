import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27210
public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 돌상의 개수
        int max = 0;
        int[][] dp = new int[N+1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1; // 돌상 방향
            int reverse = dir ^ 1; // 반대 방향

            dp[i][dir] = dp[i-1][dir] + 1;
            if(dp[i-1][reverse] > 0) {
                dp[i][reverse] = dp[i-1][reverse] - 1;
            }

            max = Math.max(max, dp[i][dir]);
        }

        br.close();
        System.out.print(max);
    }//main



}//class