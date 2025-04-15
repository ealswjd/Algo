import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17953
public class Main {
    private static int N, M; // 한 주기의 날짜 수, 디저트 종류의 수
    private static int[][] satisfaction; // 디저트의 만족감


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[][] dp = new int[N][M]; // 각 날의 만족감 최댓값 저장
        int result = 0; // 최종 만족감의 최댓값
        int max, val;

        // 각 주기의 첫 날의 만족감은 이전 주기의 마지막 날에 영향을 받지 않음
        System.arraycopy(satisfaction[0], 0, dp[0], 0, M);

        for(int day=1; day<N; day++) {
            for(int num=0; num<M; num++) {
                max = 0;
                for(int prev=0; prev<M; prev++) {
                    // 전날에 먹었던 것과 같은 것을 먹으면 만족감이 반으로 감소
                    if(num == prev) val = satisfaction[day][num] / 2;
                    // 전날에 먹었던 것과 다른 디저트
                    else val = satisfaction[day][num];

                    max = Math.max(max, dp[day-1][prev] + val);
                }

                dp[day][num] = max; // day일에 num번 디저트 먹었을 때 만족도
            }
        }

        for(int i=0; i<M; i++) {
            result = Math.max(result, dp[N-1][i]);
        }

        return result;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 한 주기의 날짜 수
        M = Integer.parseInt(st.nextToken()); // 디저트 종류의 수

        satisfaction = new int[N][M]; // 디저트의 만족감

        for(int d=0; d<M; d++) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                satisfaction[i][d] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class