import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1535
public class Main {
    static int N;
    static int[] hp, score, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hp = new int[N];
        score = new int[N];
        dp = new int[100];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            for(int j=99; j>=hp[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - hp[i]] + score[i]);
            }//for
        }//for


        System.out.print(dp[99]);
    }//main

}//class