import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17208
public class Main {
    static final int CHEESE=0, POTATO=1;
    static int N, C, P;
    static int[][] orders;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 주문의 수
        C = Integer.parseInt(st.nextToken()); // 치즈버거 개수
        P = Integer.parseInt(st.nextToken()); // 감자튀김 개수

        orders = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            orders[i][CHEESE] = Integer.parseInt(st.nextToken()); // 치즈버거 요구 개수
            orders[i][POTATO] = Integer.parseInt(st.nextToken()); // 감자튀김 요구 개수
        }
        br.close();

        int maxCnt = getMaxCnt();
        System.out.println(maxCnt);
    }//main

    private static int getMaxCnt() {
        int[][] dp = new int[C+1][P+1];

        for(int[] order : orders) {
            for(int c=C; c>=order[CHEESE]; c--) {
                for(int p=P; p>=order[POTATO]; p--) {
                    dp[c][p] = Math.max(dp[c][p], dp[c-order[CHEESE]][p-order[POTATO]] + 1);
                }
            }
        }

        return dp[C][P];
    }//getMaxCnt

}//class