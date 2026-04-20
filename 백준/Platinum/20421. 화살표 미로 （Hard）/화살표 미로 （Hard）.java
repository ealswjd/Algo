import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20421
public class Main {
    private static final int MAX = 1555; // K의 최댓값이 1500임
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    
    private static int R, C; // 미로의 행과 열
    private static int K; // 주문서 세트의 개수
    private static int[][] map; // 미로의 지도
    

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    // 준서의 질문에 대한 답을 "Yes" 또는 "No"로 출력한다.
    private static void solve() {
        PriorityQueue<Player> pq = new PriorityQueue<>();
        // [사용한 R주문서 개수][행][열] = 사용한 최소 L주문서 개수
        int[][][] visited = new int[K+1][R][C];

        for(int k=0; k<=K; k++) {
            for(int r=0; r<R; r++) {
                Arrays.fill(visited[k][r], MAX);
            }
        }

        // 고객들은 미로의 가장 왼쪽 위에서 출발
        int r = 0, c = 0, lCnt = 0, rCnt = 0;
        pq.offer(new Player(r, c, lCnt, rCnt));

        Player cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur.r;
            c = cur.c;
            lCnt = cur.lCnt;
            rCnt = cur.rCnt;

            // 목적지 : 가장 오른쪽 아래인 (R,C)방에 있는 출구
            if (r == R-1 && c == C-1) {
                System.out.print("Yes");
                return;
            }

            // 동일한 R주문서를 사용해서 이 위치에 왔었고,
            // 현재 L주문서보다 더 적게 사용했었다면 패스
            if (visited[rCnt][r][c] <= lCnt) continue;
            visited[rCnt][r][c] = lCnt;

            for(int d=0; d<4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];
                if (!inRange(nr, nc) || visited[rCnt][nr][nc] <= lCnt) {
                    continue;
                }

                // 현재 바닥의 방향과 이동하려는 방향(d)가 같음
                if (map[r][c] == d) {
                    pq.offer(new Player(nr, nc, lCnt, rCnt));
                }
                // 바닥의 방향과 이동하려는 방향이 1칸(90도) 차이남
                else if (map[r][c] == (d+1) % 4) {
                    // 왼쪽 1번 사용하거나
                    if (lCnt + 1 <= K) pq.offer(new Player(nr, nc, lCnt+1, rCnt));
                    // 오른쪽 3번 사용
                    if (rCnt + 3 <= K) pq.offer(new Player(nr, nc, lCnt, rCnt+3));
                }
                // 바닥의 방향과 이동하려는 방향이 2칸(180도) 차이남
                else if (map[r][c] == (d+2) % 4) {
                    // 왼쪽 2번 사용하거나
                    if (lCnt + 2 <= K) pq.offer(new Player(nr, nc, lCnt+2, rCnt));
                    // 오른쪽 2번 사용
                    if (rCnt + 2 <= K) pq.offer(new Player(nr, nc, lCnt, rCnt+2));
                }
                // 바닥의 방향과 이동하려는 방향이 3칸(270도) 차이남
                else if (map[r][c] == (d+3) % 4) {
                    // 왼쪽 3번 사용하거나
                    if (lCnt + 3 <= K) pq.offer(new Player(nr, nc, lCnt+3, rCnt));
                    // 오른쪽 1번 사용
                    if (rCnt + 1 <= K) pq.offer(new Player(nr, nc, lCnt, rCnt+1));
                }
            }
        }

        // 가지고 있는 주문서 세트로 출구까지 갈 수 없음
        System.out.print("No");
    }//solve

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }//inRange

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 미로의 행 R, 열 C, 준서가 가진 주문서 세트의 개수 K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        // R줄에 걸쳐 화살표 미로의 지도가 입력된다.
        for(int i=0; i<R; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if (line[j] == 'U') {
                    map[i][j] = 0;
                } else if (line[j] == 'R') {
                    map[i][j] = 1;
                } else if (line[j] == 'D') {
                    map[i][j] = 2;
                } else if (line[j] == 'L') {
                    map[i][j] = 3;
                }
            }
        }

        br.close();
    }//init

    private static class Player implements Comparable<Player> {
        int r; // 행
        int c; // 열
        int lCnt; // 사용한 L 주문서 개수
        int rCnt; // 사용한 R 주문서 개수
        Player(int r, int c, int lCnt, int rCnt) {
            this.r = r;
            this.c = c;
            this.lCnt = lCnt;
            this.rCnt = rCnt;
        }
        @Override
        public int compareTo(Player p) {
            if (this.lCnt == p.lCnt) {
                return this.rCnt - p.rCnt;
            }
            return this.lCnt - p.lCnt;
        }
    }//Player

}//class

/*

3 3 1
RDR
URU
UDR
------
Yes

10 10 5
RDDRULURRD
DRLLLDURUD
URLDRRLURR
DRLRLUDRUR
UUDLRRUURR
LLDLRRULRR
DUURUDUULU
ULLLRLDRUD
DLRDDLDRDL
DLLUDLUULD
-------------
Yes

 */