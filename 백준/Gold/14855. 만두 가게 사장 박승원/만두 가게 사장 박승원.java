import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 만두 가게 사장 박승원 (골드 4)
 * 링크 : https://www.acmicpc.net/problem/14855
 * */
public class Main {
    static final int A=0, B=1, C=2, D=3;
    static int N, M;
    static int[][] dumplings;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 밀가루
        M = Integer.parseInt(st.nextToken()); // 만두 종류
        int SC = Integer.parseInt(st.nextToken()); // 스페셜 메뉴 밀가루
        int SD = Integer.parseInt(st.nextToken()); // 스페셜 메뉴 판매 금액

        dumplings = new int[M+1][4];
        dumplings[0][A] = N/SC;
        dumplings[0][B] = 1;
        dumplings[0][C] = SC;
        dumplings[0][D] = SD;

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            dumplings[i][A] = Integer.parseInt(st.nextToken()); // 남아 있는 만두 속
            dumplings[i][B] = Integer.parseInt(st.nextToken()); // 만두 하나를 만들기 위한 만두 속
            dumplings[i][C] = Integer.parseInt(st.nextToken()); // 만두 하나를 만들기 위한 밀가루
            dumplings[i][D] = Integer.parseInt(st.nextToken()); // 판매 금액
        }
        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int[] dp = new int[N+1];

        for(int[] dumpling : dumplings) {
            for(int i=N; i>=dumpling[C]; i--) {
                for(int j=1, max=dumpling[A]/dumpling[B]; j<=max; j++) {
                    if(i-dumpling[C]*j < 0) continue;
                    dp[i] = Math.max(dp[i], dp[i-dumpling[C]*j] + dumpling[D]*j);
                }
            }
        }

        return dp[N];
    }//getMax

}//class