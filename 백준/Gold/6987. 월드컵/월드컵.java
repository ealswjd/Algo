import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6987
public class Main {
    private static final int N = 6, M = 3;
    private static int result; // 가능한 결과는 1, 불가능한 결과는 0
    private static int[][] score; // 각 팀의 승,무승부,패 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        for(int t=0; t<4; t++) {
            int total = 0;
            result = 0;
            score = new int[N][M];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                score[i][0] = Integer.parseInt(st.nextToken()); // 승
                score[i][1] = Integer.parseInt(st.nextToken()); // 무승부
                score[i][2] = Integer.parseInt(st.nextToken()); // 패
                total += score[i][0] + score[i][1] + score[i][2];
            }

            if (total == 30) {
                dfs(0, 1);
            }

            // 가능한 결과는 1, 불가능한 결과는 0을 빈칸을 하나 사이에 두고 출력
            ans.append(result).append(' ');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static void dfs(int cur, int next) {
        if (result == 1) return;
        if (cur == N-1) {
            result = 1;
            return;
        }

        // 승
        if (score[cur][0] > 0 && score[next][2] > 0) {
            score[cur][0]--; score[next][2]--;

            if (next == N-1) dfs(cur + 1, cur + 2);
            else dfs(cur, next + 1);

            score[cur][0]++; score[next][2]++;
        }
        // 무승부
        if (score[cur][1] > 0 && score[next][1] > 0) {
            score[cur][1]--; score[next][1]--;

            if (next == N-1) dfs(cur + 1, cur + 2);
            else dfs(cur, next + 1);

            score[cur][1]++; score[next][1]++;
        }
        // 패
        if (score[cur][2] > 0 && score[next][0] > 0) {
            score[cur][2]--; score[next][0]--;

            if (next == N-1) dfs(cur + 1, cur + 2);
            else dfs(cur, next + 1);

            score[cur][2]++; score[next][0]++;
        }
    }//dfs

}//class