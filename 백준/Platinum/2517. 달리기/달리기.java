import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2517
public class Main {
    private static int N; // 선수의 수
    private static int[] scores; // 선수들의 평소 점수
    private static int[] tree; // 앞지를 수 있는 사람의 수

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<=N; i++) {
            int score = scores[i]; // i번 사람의 평소 점수

            // 최선의 등수 : 현재 등수 - 앞지를 수 있는 사람 수
            int rank = i - getSum(1, 1, N, 1, score - 1);
            // 현재 선수 방문 표시
            update(1, 1, N, score);

            ans.append(rank).append('\n');
        }

        System.out.print(ans);
    }//sol

    private static int getSum(int cur, int start, int end, int from, int to) {
        // 범위 벗어남
        if (to < start || end < from) return 0;
        // 해당 범위임
        if (from <= start && end <= to) {
            return tree[cur];
        }

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, from, to)
                + getSum(cur * 2 + 1, mid + 1, end, from, to);
    }//getSum

    private static void update(int cur, int start, int end, int target) {
        // 범위 벗어남
        if (target < start || end < target) return;

        tree[cur]++;

        if (start != end) {
            int mid = (start + end) / 2;

            update(cur * 2, start, mid, target);
            update(cur * 2 + 1, mid + 1, end, target);
        }
    }//update

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 선수의 수

        long[] players = new long[N];
        scores = new int[N + 1]; // 선수들의 평소 점수
        tree = new int[N * 4]; // 앞지를 수 있는 사람 수

        for(int i=0; i<N; i++) {
            long score = Long.parseLong(br.readLine()); // 평소 점수
            // 점수 기준 정렬 예정
            players[i] = (score << 32) | (i + 1);
        }
        br.close();

        // 점수 1부터 새로 부여
        Arrays.sort(players);
        for(int i=0; i<N; i++) {
            int idx = (int) players[i];
            scores[idx] = i + 1;
        }

    }//init

}//class