import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19645
public class Main {
    static int N; // 햄버거의 수
    static int[] V; // 햄버거의 효용

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = 0; // 효용 총합
        for(int i=1; i<=N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
            total += V[i];
        }

        int max = getMax(total); // 막내 길원이가 얻을 수 있는 효용의 합의 최댓값
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int getMax(int total) {
        int[][] dp = new int[total+1][total+1];
        dp[0][0] = 1;

        for(int i=1; i<=N; i++) {
            for(int a=total; a>=0; a--) {
                for(int b=total; b>=0; b--) {
                    if(a - V[i] >= 0) {
                        dp[a][b] |= dp[a- V[i]][b];
                    }
                    if(b - V[i] >= 0) {
                        dp[a][b] |= dp[a][b- V[i]];
                    }
                }
            }
        }

        int max = 0;
        for(int a=0; a<=total; a++) {
            for(int b=0; b<=a; b++) {
                if(dp[a][b] == 1 && b >= (total - a - b)) {
                    max = Math.max(max, total - a - b);
                }
            }
        }

        return max;
    }//getMax

    private static void init() {
        V = new int[N+1]; // 햄버거의 효용
    }//init

}//class