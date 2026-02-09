import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1079
public class Main {
    private static int N, M; // 참가자의 수, 마피아 참가 번호
    private static int max;
    private static int[] score; // 유죄 점수
    private static int[][] R; // 참가자 탈락 시 변하는 유죄 점수

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // 짝:밤 홀:낮
        dfs(N, 0, new boolean[N]);

        System.out.print(max);
    }//sol

    private static void dfs(int cnt, int night, boolean[] isDead) {
        if (cnt == 1) {
            max = Math.max(max, night);
            return;
        }
        if (isDead[M]) return;

        if (cnt % 2 == 0) { // 밤
            // 밤에는 마피아가 죽일 사람을 한 명 고른다. 이 경우 각 사람의 유죄 지수가 바뀐다
            for(int i=0; i<N; i++) {
                if (isDead[i] || i == M) continue;

                isDead[i] = true;
                changeScore(i, 1);
                dfs(cnt-1, night+1, isDead);

                changeScore(i, -1);
                isDead[i] = false;
            }
        } else { // 낮
            // 낮에는 현재 게임에 남아있는 사람 중에 유죄 지수가 가장 높은 사람을 죽인다.
            int idx = kill(isDead);
            isDead[idx] = true;
            if (idx == M) {
                max = Math.max(max, night);
                isDead[idx] = false;
                return;
            }
            dfs(cnt-1, night, isDead);
            isDead[idx] = false;
        }
    }//dfs

    private static void changeScore(int i, int s) {
        for(int j=0; j<N; j++) {
            score[j] += R[i][j] * s;
        }
    }//changeScore

    private static int kill(boolean[] isDead) {
        int idx = 0;
        int maxScore = 0;

        for(int i=0; i<N; i++) {
            if (!isDead[i] && maxScore < score[i]) {
                idx = i;
                maxScore = score[i];
            }
        }

        return idx;
    }//kill

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 참가자의 수 N이 주어진다.
        N = Integer.parseInt(br.readLine());

        score = new int[N]; // 유죄 점수
        R = new int[N][N]; // 참가자 탈락 시 변하는 유죄 점수

        // 각 참가자의 유죄 지수가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        // N개의 줄에는 배열 R이 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                // 참가자 i가 죽었다면, 다른 참가자 j의 유죄 지수는 R[i][j]만큼 변한다.
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 마피아의 참가자 번호가 주어진다.
        M = Integer.parseInt(br.readLine());

        br.close();
    }//init

}//class