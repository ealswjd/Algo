import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/29792
public class Main {
    static final int P=0, Q=1, T=900; // 체력, 메소, 하루에 한 캐릭터 당 최대 15분씩
    static int N, M, K;
    static long[] players;
    static long[][] bosses;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 보유한 캐릭터의 개수
        M = Integer.parseInt(st.nextToken()); // 하루에 사용할 캐릭터의 개수
        K = Integer.parseInt(st.nextToken()); // 보스의 가짓수

        init();

        for(int i=0; i<N; i++) {
            players[i] = Long.parseLong(br.readLine());
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            bosses[i][P] = Long.parseLong(st.nextToken()); // 보스의 체력
            bosses[i][Q] = Long.parseLong(st.nextToken()); // 보스를 처치했을 때 메소
        }
        br.close();

        long max = getMax();
        System.out.print(max);
    }//main

    private static long getMax() {
        long[][] dp = new long[N][T+1];
        long time;

        for(int i=0; i<N; i++) {
            for(long[] boss : bosses) {
                time = boss[P]/players[i];
                if(boss[P]%players[i] != 0) time++;
                for(int t=T; t>=1; t--) {
                    if(t-time < 0) break;
                    dp[i][t] = Math.max(dp[i][t], dp[i][(int) (t-time)] + boss[Q]);
                }
            }
        }

        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long max;
        for(int i=0; i<N; i++) {
            max = 0;
            for(int t=1; t<=T; t++) {
                max = Math.max(max, dp[i][t]);
            }
            pq.offer(max);
        }

        long ans = 0;
        while(M-->0) {
            ans += pq.poll();
        }
        return ans;
    }//getMax

    private static void init() {
        players = new long[N];
        bosses = new long[K][2];
    }

}//class