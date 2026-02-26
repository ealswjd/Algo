import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2281
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N, W; // 이름의 개수, 노트의 가로칸
    private static int[] names; // 각 이름의 길이

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int[] dp = new int[N+1]; // i번 이름까지 적었을 때 제곱의 합 최솟값

        Arrays.fill(dp, MAX);
        dp[0] = 0;

        int len, total, space, cost;
        for(int i=1; i<=N; i++) {
            len = 0; // 이름 길이 합

            // i번 이름 앞에 j번 이름을 적어보며 확인
            for(int j=i; j>=1; j--) {
                len += names[j]; // j번 이름 추가
                total = len + (i - j); // 이름 길이 합 + 공백
                if (total > W) break;

                if (i == N) { // 마지막 이름은 마지막 줄
                    dp[i] = Math.min(dp[i], dp[j-1]);
                } else {
                    space = W - total; // 남은 공백
                    cost = space * space; // 공백 제곱합

                    dp[i] = Math.min(dp[i], dp[j-1] + cost);
                }
            }
        }

        // 남게 되는 칸 수의 제곱의 합의 최솟값을 출력
        System.out.print(dp[N]);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 이름 개수
        W = Integer.parseInt(st.nextToken()); // 노트 너비

        names = new int[N+1]; // 각 사람의 이름 길이

        for(int i=1; i<=N; i++) {
            names[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init

}//class