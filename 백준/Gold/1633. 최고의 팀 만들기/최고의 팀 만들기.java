import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1633
public class Main {
    private static final int MAX = 1000; // 1000줄은 넘지 않는다.
    private static final int P = 15; // 각 팀의 플레이어 수
    private static int N; // 플레이어의 수
    private static int[] W, B; // 백으로 플레이를 할 때 능력치, 흑으로 플레이를 할 때의 능력치
    private static int[][][] dp; // 협회가 만들 수 있는 팀 중 가장 큰 능력치


    public static void main(String[] args) throws IOException {
        init();
        int max = getMax(0, 0, 0);

        System.out.print(max);
    }//main


    private static int getMax(int cur, int w, int b) {
        if(cur == N) return 0;
        if(w == P && b == P) return 0;
        if(dp[cur][w][b] != 0) return dp[cur][w][b];

        // 해당 선수 선택 x
        int power = getMax(cur+1, w, b);

        // 해당 선수 백팀
        if(w < P) power = Math.max(power, getMax(cur+1, w+1, b) + W[cur]);
        // 해당 선수 흑팀
        if(b < P) power = Math.max(power, getMax(cur+1, w, b+1) + B[cur]);

        return dp[cur][w][b] = power;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = new int[MAX + 1]; // 백으로 플레이를 할 때 능력치
        B = new int[MAX + 1]; // 흑으로 플레이를 할 때의 능력치

        StringTokenizer st;
        String player;
        while(true) {
            player = br.readLine();
            if(player == null || player.isEmpty()) break;

            st = new StringTokenizer(player);
            // 첫 번째 숫자는 해당 플레이어가 백으로 플레이를 할 때 능력치고
            W[N] = Integer.parseInt(st.nextToken());
            // 두 번째 숫자는 흑으로 플레이를 할 때의 능력치
            B[N] = Integer.parseInt(st.nextToken());

            N++;
        }

        br.close();

        dp = new int[N][P+1][P+1]; // 협회가 만들 수 있는 팀 중 가장 큰 능력치
    }//init


}//class