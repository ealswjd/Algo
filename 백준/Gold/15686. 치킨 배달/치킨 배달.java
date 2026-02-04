import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15686
public class Main {
    private static final int MAX = 25252525;
    private static int M; // 영업할 치킨집 개수
    private static int H, C; // 집 개수, 치킨집 개수
    private static int result; // 도시의 치킨 거리의 최솟값
    private static int[] pick; // 선택한 치킨집
    private static int[][] dist; // i번 집과 j번 치킨집 거리


    public static void main(String[] args) throws IOException {
        init();
        comb(0, 0);

        System.out.print(result);
    }//main


    private static void comb(int cur, int cnt) {
        if (cnt == M) {
            getMinDist();
            return;
        }

        for(int i = cur; i< C; i++) {
            pick[cnt] = i; // i번째 치킨집 선택
            comb(i + 1, cnt + 1);
        }
    }//comb

    private static void getMinDist() {
        int sum = 0;
        int minDist;

        for(int h = 0; h< H; h++) {
            minDist = MAX;
            for(int c : pick) {
                minDist = Math.min(minDist, dist[h][c]);
            }

            sum += minDist;
        }

        result = Math.min(result, sum);
    }//getTotalDist


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Position> homeList = new ArrayList<>(); // 집
        List<Position> chickenList = new ArrayList<>(); // 치킨집
        pick = new int[M]; // 선택한 치킨집

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) { // 1은 집
                    homeList.add(new Position(i, j));
                } else if (num == 2) { // 2는 치킨집
                    chickenList.add(new Position(i, j));
                }
            }
        }
        br.close();

        result = MAX; // 도시의 치킨 거리의 최솟값
        H = homeList.size(); // 집 개수
        C = chickenList.size(); // 치킨집 개수

        // 치킨 거리 미리 구하기
        dist = new int[H][C]; // h번째 집과 c번째 치킨집 거리
        for(int h = 0; h< H; h++) {
            Position home = homeList.get(h);

            for(int c = 0; c< C; c++) {
                Position chicken = chickenList.get(c);

                dist[h][c] = Math.abs(home.r - chicken.r)
                        + Math.abs(home.c - chicken.c);
            }
        }

    }//init

    private static class Position {
        int r;
        int c;
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }//Position


}//class