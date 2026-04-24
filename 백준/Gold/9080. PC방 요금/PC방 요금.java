import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9080
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static final int BASE = 1000; // 기본 요금
    private static final int FLAT = 5000; // 야간 정액 요금

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            String[] time = st.nextToken().split(":");
            int hour = Integer.parseInt(time[0]); // 시작 시간
            int minute = Integer.parseInt(time[1]); // 시작 분
            int total = Integer.parseInt(st.nextToken()); // 총 게임 시간

            int result = getCost(hour, minute, total);
            ans.append(result).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getCost(int hour, int minute, int total) {
        int[] dp = new int[total + 1]; // i분 동안 게임했을 때 최소 요금

        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int i=0; i<total; i++) {
            if (dp[i] == MAX) continue;

            // 기본 요금
            for(int step=1; step<=60 && i+step<=total; step++) {
                dp[i + step] = Math.min(dp[i + step], dp[i] + BASE);
            }

            // 야간 정액 요금
            int curTime = (hour * 60 + minute + i) % (24 * 60);
            int h = curTime / 60;
            int m = curTime % 60;

            // 현재 시간이 밤 10시 이상이거나 아침 8시 미만인 경우 야간 정액권 사용 가능
            if (h >= 22 || h < 8) {
                int rem = 0; // 오전 8시까지 남은 시간

                if (h >= 22) {
                    rem = (23 - h) * 60 + (60 - m) + (8 * 60);
                } else {
                    rem = (7 - h) * 60 + (60 - m);
                }

                for(int step=1; step<=rem && i+step<=total; step++) {
                    dp[i + step] = Math.min(dp[i + step], dp[i] + FLAT);
                }
            }

        }

        return dp[total];
    }//getCost

}//class

/*

4
14:30 180
19:28 242
23:25 580
21:10 765
-------------
3000
5000
7000
8000

 */