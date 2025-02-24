import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2662
public class Main {
    private static int N, M; // 투자 금액 N과 투자 가능한 기업들의 개수 M
    private static int[][] profits; // 투자액수별 각 기업에서 얻을 수 있는 이익
    private static int[][] prev;
    private static int[] result; // 각 기업에 투자한 액수


    public static void main(String[] args) throws IOException{
        init();
        solution();
    }//main


    private static void solution() {
        StringBuilder ans = new StringBuilder();
        int max = getMax(); // 얻을 수 있는 최대 이익

        // 첫 줄에 얻을 수 있는 최대 이익을 출력
        ans.append(max).append('\n');

        // 둘째 줄에는 각 기업에 투자한 액수를 출력
        for(int i=1; i<=M; i++) {
            ans.append(result[i]).append(' ');
        }

        System.out.print(ans);
    }//solution


    private static int getMax() {
        int[] dp = new int[N+1];
        int money;

        for(int m=1; m<=M; m++) {
            for(int n=N; n>=0; n--) {
                for(int k=1; k<=n; k++) {
                    money = dp[n-k] + profits[m][k];

                    if(dp[n] < money) {
                        prev[m][n] = n - k;
                        dp[n] = money;
                    }
                    else if(prev[m][n] == 0 && m != 1){
                        prev[m][n] = n;
                    }
                }
            }
        }

        int n = N;
        for(int i=M; i>=1; i--) {
            result[i] = n - prev[i][n];
            n = prev[i][n];
        }

        return dp[N];
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 투자 금액 N과 투자 가능한 기업들의 개수 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 투자 금액
        M = Integer.parseInt(st.nextToken()); // 투자 가능한 기업들의 개수

        profits = new int[M+1][N+1]; // 투자액수별 각 기업에서 얻을 수 있는 이익
        prev = new int[M+1][N+1];
        result = new int[M+1]; // 각 기업에 투자한 액수

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int amount = Integer.parseInt(st.nextToken()); // 투자액수

            // 투자액수에 따라 1 ~ M번 기업이 투자가에게 주는 이익
            for(int j=1; j<=M; j++) {
                profits[j][amount] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class