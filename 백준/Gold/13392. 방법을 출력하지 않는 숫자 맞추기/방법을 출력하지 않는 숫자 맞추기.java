import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/13392
public class Main {
    private static final int MOD = 10;
    private static int N; // 숫자나사의 개수
    private static int[] origin, target; // 현재의 상태, 원하는 상태
    private static int[][] dp; // [i][j] i번째 자리에서 0~(i-1)번째까지 왼쪽으로 돌아간 최소 횟수


    public static void main(String[] args) throws IOException {
        init();

        int min = getMin(0, 0);
        System.out.print(min);
    }//main


    private static int getMin(int n, int turn) {
        if(dp[n][turn] != -1) return dp[n][turn];
        if(n == N) return 0;

        int turnLeft = (target[n] - origin[n] - turn + 20) % MOD;
        int turnRight = MOD - turnLeft;

        int leftCnt = getMin(n+1, (turn + turnLeft) % MOD) + turnLeft;
        int rightCnt = getMin(n+1, turn) + turnRight;

        return dp[n][turn] = Math.min(leftCnt, rightCnt);
    }//getMin


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 숫자나사의 개수
        char[] originArr = br.readLine().toCharArray(); // 현재의 상태 입력값
        char[] targetArr = br.readLine().toCharArray(); // 원하는 상태 입력값

        origin = new int[N]; // 현재의 상태
        target = new int[N]; // 원하는 상태
        dp = new int[N+1][MOD]; // [i][j] i번째 자리에서 0~(i-1)번째까지 왼쪽으로 돌아간 최소 횟수

        for(int i=0; i<N; i++) {
            origin[i] = originArr[i] - '0';
            target[i] = targetArr[i] - '0';
        }

        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }

        br.close();
    }//init


}//class