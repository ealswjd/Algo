import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1727
public class Main {
    private static final int MAX = 1_000_000_000;
    private static int L, S; // 인원이 더 많은 그룹, 적은 그룹의 수
    private static int[] larger, smaller; // 인원이 더 많은 그룹, 적은 그룹

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        // dp[i][j]: 큰 그룹의 i번째 사람까지, 작은 그룹의 j번째 사람까지 매칭했을 때 최솟값
        int[][] dp = new int[L+1][S+1];

        for(int i=0; i<=L; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][0] = 0;
        }

        for(int i=1; i<=L; i++) {
            for(int j=1; j<=Math.min(i, S); j++) {
                // 현재 두 사람의 성격 차이
                int diff = Math.abs(larger[i-1] - smaller[j-1]);

                int skip = dp[i-1][j]; // 매칭 x
                int match = dp[i-1][j-1] + diff; // 매칭

                // 최솟값 저장
                dp[i][j] = Math.min(skip, match);
            }
        }

        // 큰 그룹의 L명과 작은 그룹의 S명을 모두 고려했을 때의 최솟값
        System.out.print(dp[L][S]);
    }//solve

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 남자 수
        int m = Integer.parseInt(st.nextToken()); // 여자 수

        int[] men = new int[n]; // 남자들의 성격
        int[] women = new int[m]; // 여자들의 성격

        // 남자 성격이 주어진다
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            men[i] = Integer.parseInt(st.nextToken());
        }

        // 여자 성격이 주어진다
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            women[i] = Integer.parseInt(st.nextToken());
        }

        // 순서대로 짝을 짓기 위해 오름차순 정렬
        Arrays.sort(men);
        Arrays.sort(women);

        if (n > m) {
            larger = men;
            smaller = women;
        } else {
            larger = women;
            smaller = men;
        }

        L = larger.length;
        S = smaller.length;

        br.close();
    }//init

}//class
/*

2 1
10 20
15
---------
5

 */