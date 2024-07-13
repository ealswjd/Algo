import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19645
public class Main {
    static int N; // 햄버거의 수
    static int[] V; // 햄버거의 효용
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = 0; // 효용 총합
        for(int i=0; i<N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
            total += V[i];
        }

        dp = new int[total+1][total+1];
        for(int i=0; i<=total; i++) {
            Arrays.fill(dp[i], -1);
        }

        int max = getMax(0, 0, 0, total); // 막내 길원이가 얻을 수 있는 효용의 합의 최댓값
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int getMax(int n, int a, int b, int total) {
        if(n == N) {
            int k =  total - a - b;
            if(a >= k && b >= k) return k;
            return 0;
        }
        if(dp[a][b] != -1) return dp[a][b];

        int max = 0;
        if(a + V[n] <= total) max = Math.max(max, getMax(n+1, a+V[n], b, total));
        if(b + V[n] <= total) max = Math.max(max, getMax(n+1, a, b+V[n], total));
        max = Math.max(max, getMax(n+1, a, b, total));

        return dp[a][b] = max;
    }//getMax

    private static void init() {
        V = new int[N];
    }//init

}//class