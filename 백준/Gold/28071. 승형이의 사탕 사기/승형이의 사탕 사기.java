import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28071
public class Main {
    private static int N, M, K;
    private static int[] box; // 사탕 상자

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int[][] dp = new int[M+1][K];

        for(int i=0; i<=M; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 0;

        for(int candy : box) {
            for(int i=0; i<M; i++) {
                for(int j=0; j<K; j++) {
                    // 사탕 가져갈 수 있다면
                    if(dp[i][j] != -1) {
                        int cnt = dp[i][j] + candy; // 사탕 개수
                        int mod = cnt % K;          // 나머지
                        dp[i+1][mod] = Math.max(dp[i+1][mod], cnt);
                    }
                }
            }
        }

        // K명이 나누어 가질 수 있는 최대 사탕 개수 구하기
        int max = 0;
        for(int m=1; m<=M; m++) {
            max = Math.max(max, dp[m][0]);
        }

        return max;
    }//getMax

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사탕 상자 종류
        M = Integer.parseInt(st.nextToken()); // 가져갈 수 있는 사탕 상자 개수
        K = Integer.parseInt(st.nextToken()); // 동생 수

        box = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            // 사탕 상자에 담겨있는 사탕 개수
            box[i] = Integer.parseInt(st.nextToken());
        }
    }//init

    
}//class