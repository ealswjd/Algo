import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2320
public class Main {
    private static int N, M; // 단어 개수, 비트마스크
    private static int[] len; // 단어 길이
    private static char[] first, last; // 단어 첫 글자, 마지막 글자


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[N][M];

        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int max = 0; // 최대 점수
        int score;

        for(int i=0; i<N; i++) {
            score = dfs(i, 1 << i, dp); // i번째 단어로 시작했을 때 점수
            max = Math.max(max, score);
        }

        System.out.print(max);
    }//sol


    private static int dfs(int cur, int status, int[][] dp) {
        if(dp[cur][status] != -1) {
            return dp[cur][status];
        }

        int max = 0; // 최대 점수
        int score;

        for(int next=0; next<N; next++) {
            // 사용여부 체크
            if((status & (1 << next)) != 0) continue;

            // 앞단어의 마지막 글자가 뒷단어의 처음 글자와 같도록
            if(last[cur] == first[next]) {
                score = dfs(next, status | (1 << next), dp);
                max = Math.max(max, score);
            }
        }

        // 게임에 사용된 단어의 길이의 합이 그 팀의 점수
        return dp[cur][status] = max + len[cur];
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 단어 개수
        M = 1 << N; // 비트마스크

        first = new char[N]; // 단어 첫 글자
        last = new char[N];  // 단어 마지막 글자
        len = new int[N];    // 단어 길이

        for(int i=0; i<N; i++) {
            String word = br.readLine();
            len[i] = word.length();
            first[i] = word.charAt(0);
            last[i] = word.charAt(len[i] - 1);
        }

        br.close();
    }//init


}//class