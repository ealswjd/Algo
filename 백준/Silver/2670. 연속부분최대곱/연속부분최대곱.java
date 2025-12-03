import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2670
public class Main {
    private static int N; // 실수의 개수
    private static double[] numbers; // N개의 실수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        double[] dp = new double[N+1]; // i까지 연속된 최댓값
        double max = 0; // 최댓값
        
        for(int i=1; i<=N; i++) {
            dp[i] = Math.max(dp[i-1] * numbers[i], numbers[i]);
            max = Math.max(max, dp[i]);
        }

        // 최댓값을 소수점 이하 넷째 자리에서 반올림하여 소수점 이하 셋째 자리까지 출력
        System.out.printf("%.3f", max);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 실수의 개수
        numbers = new double[N+1]; // N개의 실수

        for(int i=1; i<=N; i++) {
            numbers[i] = Double.parseDouble(br.readLine());
        }

        br.close();
    }//init


}//class