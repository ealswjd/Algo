import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4781
public class Main {
    private static int N, M; // 사탕 종류의 수 n, 가지고 있는 돈의 양 m
    private static int[] C, P; // 칼로리 c와 가격 p
    private static boolean isEnd;


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int max;

        while(true) {
            init(br);
            if(isEnd) break;

            max = getMax();
            ans.append(max).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static int getMax() {
        int[] dp = new int[M+1];

        for(int n=0; n<N; n++) {
            for(int m=P[n]; m<=M; m++) {
                dp[m] = Math.max(dp[m], dp[m - P[n]] + C[n]);
            }
        }

        return dp[M];
    }//getMex


    private static void init(BufferedReader br) throws IOException {
        double m, p;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        m = Double.parseDouble(st.nextToken());
        M = (int) (m * 100 + 0.5);

        if(N == 0 && M == 0) {
            isEnd = true;
            return;
        }

        C = new int[N]; // 각 사탕의 칼로리
        P = new int[N+1]; // 각 사탕의 가격

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            C[i] = Integer.parseInt(st.nextToken());
            p = Double.parseDouble(st.nextToken());
            P[i] = (int) (p * 100 + 0.5);
        }

    }//init


}//class