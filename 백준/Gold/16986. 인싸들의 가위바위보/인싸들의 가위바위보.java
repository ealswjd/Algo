import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16986
public class Main {
    private static final int A = 0, B = 1, C = 2; // 지우, 경희, 민호
    private static final int WIN = 2, LOSE = 0, CNT = 20; // 20경기
    private static int N; // 인싸 가위바위보의 손동작 수
    private static int K; // 우승을 위해 필요한 승수
    private static int[][] rules; // 상성표
    private static int[][] pattern; // 각 선수가 낼 손동작
    private static boolean[] used; // 지우의 손동작 사용여부
    private static boolean isPossible; // 지우 우승 가능성

    public static void main(String[] args) throws IOException {
        init();

        if (K <= N) {
            dfs(0);
        }

        // 지우가 모든 손동작을 다르게 내어 우승할 수 있으면 1을, 그렇지 않으면 0을 출력
        System.out.print(isPossible ? 1 : 0);
    }//main

    private static void dfs(int cur) {
        if (isPossible) return;
        if (cur == N) {
            play();
            return;
        }

        // 지우 손동작 순서 정하기
        for(int i=1; i<=N; i++) {
            if (used[i]) continue;

            used[i] = true;
            pattern[A][cur] = i;
            dfs(cur + 1);
            used[i] = false;
        }
    }//dfs

    private static void play() {
        int[] winCnt = new int[3]; // 세사람의 각각 이긴 횟수
        int[] idx = new int[3]; // 세사람의 현재 낼 차례
        int p1 = A; // 지우
        int p2 = B; // 경희
        int winner; // 승자

        while(true) {
            // 지우 참여 게임인데 지우가 이미 N가지 동작을 다 사용함.
            if ((p1 == A || p2 == A) && idx[A] == N) {
                return; // 지우 패배
            }

            int hand1 = pattern[p1][idx[p1]++]; // p1 손동작
            int hand2 = pattern[p2][idx[p2]++]; // p2 손동작

            if (rules[hand1][hand2] == WIN) {
                winner = p1;
            } else if (rules[hand1][hand2] == LOSE) {
                winner = p2;
            } else {
                winner = Math.max(p1, p2);
            }

            // 누군가 승리한다면 종료
            if (++winCnt[winner] == K) {
                break;
            }

            // 선수 교체
            int nextPlayer = 3 - p1 - p2;
            p1 = winner;
            p2 = nextPlayer;
        }

        // 지우가 이기는가
        isPossible = winner == A;
    }//play

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 인싸 가위바위보의 손동작 수
        K = Integer.parseInt(st.nextToken()); // 우승을 위해 필요한 승수

        rules = new int[N+1][N+1]; // 상성표
        pattern = new int[3][CNT]; // 3명이 20경기에서 낼 손동작의 순서
        used = new boolean[N+1];

        // 상성에 대한 정보가 주어진다
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                rules[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 경희 손동작의 순서
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<CNT; i++) {
            pattern[B][i] = Integer.parseInt(st.nextToken());
        }

        // 민호 손동작의 순서
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<CNT; i++) {
            pattern[C][i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

}//class