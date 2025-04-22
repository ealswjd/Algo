import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/30460
 스위치
 */
public class Main {
    private static final int MIN = -Integer.MAX_VALUE;
    private static int N; // 점수를 얻는 횟수
    private static int[] score; // i초에 얻는 점수
    private static int[] doublePrefix; // 2배 점수 누적합


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[] dp = new int[N]; // i초에 얻는 최고 점수

        Arrays.fill(dp, MIN);
        dp[0] = 0;

        int btnO, btnX;
        for(int i=1; i<N; i++) {
            // 버튼 누름
            btnO = i>=3 ? doublePrefix[i] - doublePrefix[i-3] + dp[i-3] : MIN;
            // 버튼 안 누름
            btnX = dp[i-1] + score[i];

            dp[i] = Math.max(btnO, btnX);
        }

        return dp[N-1];
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()) + 3; // 점수를 얻는 횟수
        score = new int[N]; // i초에 얻는 점수
        doublePrefix = new int[N]; // 2배 점수 누적합

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<N-2; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            int doubleScore = score[i] * 2;

            doublePrefix[i] = doublePrefix[i-1] + doubleScore;
        }

        for(int i=N-2; i<N; i++) {
            doublePrefix[i] = doublePrefix[i-1];
        }

        br.close();
    }//init


}//class