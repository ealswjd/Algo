import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20208
public class Main {
    private static final int MAX = 10; // 민트초코우유의 총합은 10개를 넘지 않는다.
    
    private static int M, H; // 초기 체력, 증가 체력
    private static int milkCnt; // 민트초코우유 개수
    private static int max; // 마실 수 있는 민트초코우유의 최대 개수
    private static Position start; // 집
    private static Position[] positions; // 민초 위치
    private static boolean[] visited; // 방문체크

    
    public static void main(String[] args) throws IOException {
        init();
        dfs(0, M, 0);

        System.out.print(max);
    }//main
    

    private static void dfs(int prev, int hp, int total) {
        // 현재 위치에서 집으로 돌아갈 수 있는 체력이 있음
        if (0 <= hp - getDist(prev, 0)) {
            max = Math.max(max, total);
        }

        // 체력이 없거나 우유 다 마심
        if (hp == 0 || total == milkCnt) {
            return;
        }

        for(int i=1; i<=milkCnt; i++) {
            if (visited[i]) continue;

            int dist = getDist(prev, i); // 다음 우유 거리
            int nextHp = hp - dist; // 이동 시 남는 체력

            // 이동 가능하면 이동해서 우유 마심
            if (0 <= nextHp) {
                visited[i] = true;
                dfs(i, nextHp + H, total + 1);
                visited[i] = false;
            }
        }
    }//dfs
    

    private static int getDist(int prev, int cur) {
        int r1 = positions[prev].r, c1 = positions[prev].c;
        int r2 = positions[cur].r, c2 = positions[cur].c;

        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }//getDist
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 민초마을의 크기 N과 초기체력 M, 증가하는 체력의 양 H가 공백을 두고 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        positions = new Position[MAX + 1]; // 민트초코우유 위치
        visited = new boolean[MAX + 1]; // 방문체크

        // N칸에 걸쳐서 민초마을의 지도가 주어진다
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 2) { // 민트초코우유
                    positions[++milkCnt] = new Position(r, c);
                } else if (num == 1) { // 진우의 집
                    start = new Position(r, c);
                }
            }
        }
        positions[0] = start; // 0번은 진우의 집으로

        br.close();
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