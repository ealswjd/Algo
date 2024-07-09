import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/4384
public class Main {
    static final int MAX = 450; // 몸무게 최댓값
    static int N; // 총 인원의 수
    static int[] weights; // 몸무게

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 총 인원의 수
        weights = new int[N]; // 몸무게

        int total = 0;
        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
            total += weights[i];
        }
        br.close();

        splitTeam(total);
    }//main

    private static void splitTeam(int total) {
        int half = N/2;
        int max = half * MAX;
        boolean[][] dp = new boolean[half+1][max+1];
        dp[0][0] = true;

        for(int weight : weights) {
            for(int j=half; j>0; j--) {
                for(int k=max; k>=weight; k--) {
                    dp[j][k] |= dp[j-1][k-weight];
                }
            }
        }


        int teamA = 0;
        int teamB = 0;
        int min = Integer.MAX_VALUE;

        for(int w=0; w<max; w++) {
            if(dp[half][w]) {
                int diff = Math.abs(w - (total-w));
                if(min > diff) {
                    min = diff;
                    teamA = w;
                }
            }
        }

        teamB = total - teamA;
        if(teamA > teamB) {
            int tmp = teamA;
            teamA = teamB;
            teamB = tmp;
        }

        System.out.printf("%d %d", teamA, teamB);
    }//splitTeam

}//class