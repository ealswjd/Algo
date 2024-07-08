import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// https://www.acmicpc.net/problem/9423
public class Main {
    static final int MAX_W = 450;
    static int N;
    static int[] weights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        weights = new int[N];
        int total = 0;
        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
            total += weights[i];
        }

        bw.write(sol(total));
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static String sol(int total) {
        int half = N/2;
        int maxW = half * MAX_W;
        boolean[][] dp = new boolean[half+1][maxW+1];
        dp[0][0] = true;

        for(int i=0; i<N; i++) {
            for(int j=half; j>0; j--) {
                for(int k=maxW; k>=weights[i]; k--) {
                    dp[j][k] |= dp[j-1][k - weights[i]];
                }
            }
        }

        int teamA = 0;
        int teamB = 0;
        int min = Integer.MAX_VALUE;
        for(int k=0; k<maxW; k++) {
            if(dp[half][k]) {
                if(min > Math.abs(total - 2 * k)) {
                    teamA = k;
                    min = Math.abs(total - 2 * k);
                }
            }
        }

        teamB = total - teamA;
        if(teamA > teamB) {
            int tmp = teamA;
            teamA = teamB;
            teamB = tmp;
        }

        return teamA + " " + teamB;
    }//sol

}//class