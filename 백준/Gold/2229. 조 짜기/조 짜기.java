import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2229
public class Main {
    private static int N; // 학생수
    private static int[] score; // 학생들 점수

    
    public static void main(String[] args) throws IOException {
        init();
        getMax();
    }//main

    
    private static void getMax() {
        int[] dp = new int[N+1];

        for(int i=1; i<=N; i++) {
            for(int j=0; j<i; j++) {
                int max = Integer.MIN_VALUE; // 조에 속해있는 가장 점수가 높은 학생
                int min = Integer.MAX_VALUE; // 가장 점수가 낮은 학생

                for(int k=j; k<i; k++) {
                    max = Math.max(max, score[k]);
                    min = Math.min(min, score[k]);
                }

                dp[i] = Math.max(dp[i], dp[j] + (max - min));
            }
        }

        System.out.print(dp[N]);
    }//getMax

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 학생수
        score = new int[N]; // 학생들 점수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

    }//init

    
}//class