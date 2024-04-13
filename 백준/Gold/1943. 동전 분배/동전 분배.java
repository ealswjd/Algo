import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1943
public class Main {
    static final int MONEY=0, CNT=1;
    static int N, total;
    static boolean[] dp;
    static int[][] moneys;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int T = 3;

        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            init();

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                moneys[i][MONEY] = n;
                moneys[i][CNT] = cnt;

                total += n * cnt;
            }

            ans.append(getResult()).append('\n');
        }

        System.out.print(ans);
    }//main

    private static int getResult() {
        if(total % 2 != 0) return 0;

        int half = total / 2;
        dp = new boolean[half+1];
        dp[0] = true;
        int cnt, n;

        for(int i=0; i<N; i++) {
            n = moneys[i][MONEY];
            cnt = moneys[i][CNT];
            
            for(int j=half; j>=n; j--) {
                if(dp[j - n]) {
                    for(int c=0; c<cnt; c++) {
                        if(j + n * c > half) break;
                        dp[j + n * c] = true;
                    }
                }
            }
        }

        return dp[half] ? 1 : 0;
    }

    private static void init() {
        moneys = new int[N][2];
        total = 0;
    }

}//class