import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26093
public class Main {
    private static int N, K; // 고양이의 수, 리본 종류의 수
    private static int[][] score; // i번 고양이가 j번 리본을 달았을 때의 만족도


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[][] dp = new int[N+1][K];
        int first, second;

        for(int i=1; i<=N; i++) {
            first = 0; // 가장 큰 만족도
            second = 0; // 두번째로 큰 만족도

            for(int k=0; k<K; k++) {
                if(dp[i-1][k] > first) {
                    second = first;
                    first = dp[i-1][k];
                    continue;
                }

                if(dp[i-1][k] > second) second = dp[i-1][k];
            }

            for(int k=0; k<K; k++) {
                if(dp[i-1][k] == first) {
                    dp[i][k] = score[i][k] + second;
                    continue;
                }

                dp[i][k] = score[i][k] + first;
            }
        }

        int max = 0;
        for(int k=0; k<K; k++) {
            max = Math.max(max, dp[N][k]);
        }

        return max;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 고양이의 수
        K = Integer.parseInt(st.nextToken()); // 리본 종류의 수

        score = new int[N+1][K]; // i번 고양이가 j번 리본을 달았을 때의 만족도

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<K; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class