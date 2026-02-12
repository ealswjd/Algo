import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9983
public class Main {
    private static final String RESULT = "Minimum Number of Pieces to be removed: ";
    private static final int MAX = 15, EMPTY = -1;

    // 0~3: 상하좌우(Rook), 4~7: 대각선(Bishop), 8~15: 나이트(Knight)
    private static final int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1, -2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] dc = {0, 0, -1, 1, -1, 1, -1, 1, -1, 1, -2, 2, -2, 2, -1, 1};

    private static int H, W; // 보드 크기
    private static int pCnt, minCnt; // 기물 개수, 제거할 최소 개수
    private static int[][] map; // 보드
    private static boolean isSafe; // 서로를 공격하지 않는 상태 확인
    private static Piece[] pieces; // 기물 정보
    private static boolean[] isRemoved; // 제거 여부


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        String line;

        while((line = br.readLine()) != null) {
            init(br);
            sol();

            ans.append(RESULT).append(minCnt).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static void sol() {
        for(int limit=0; limit<pCnt; limit++) {
            comb(0, 0, limit);

            if (isSafe) {
                minCnt = limit;
                return;
            }
        }
    }//sol

    private static void comb(int depth, int start, int limit) {
        if (depth == limit) {
            if (isPossible()) {
                isSafe = true;
            }
            return;
        }

        for(int i=start; i<pCnt; i++) {
            isRemoved[i] = true;
            comb(depth+1, i+1, limit);
            isRemoved[i] = false;

            if (isSafe) return;
        }
    }//comb

    private static boolean isPossible() {
        int r, c, sd, ed;

        for(int i=0; i<pCnt; i++) {
            if (isRemoved[i]) continue;

            Piece piece = pieces[i];
            r = piece.r; c = piece.c;
            sd = 0; ed = 16;
            boolean isK = piece.type == 'K' || piece.type == 'N';

            switch (piece.type) {
                case 'K': ed = 8; break;
                case 'Q': ed = 8; break;
                case 'R': ed = 4; break;
                case 'B': sd = 4; ed = 8; break;
                case 'N': sd = 8; break;
            }

            for(int d=sd; d<ed; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                while(inRange(nr, nc)) {
                    int target = map[nr][nc];
                    if (target != EMPTY) {
                        // 제거되지 않은 기물을 공격한다면 실패
                        if (!isRemoved[target]) {
                            return false;
                        }
                    }
                    // 킹이나 나이트는 한 칸만 이동하고 종료
                    if (isK) break;

                    nr += dr[d];
                    nc += dc[d];
                }
            }
        }

        return true;
    }//isPossible

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }//inRange

    private static void init(BufferedReader br) throws IOException {
        // 보드 크기
        W = Integer.parseInt(br.readLine());
        H = Integer.parseInt(br.readLine());

        map = new int[H][W]; // 보드
        pieces = new Piece[MAX]; // 기물 정보
        pCnt = 0; // 기물 개수
        isSafe = false; // 서로를 공격하는 기물이 없는지 확인

        StringTokenizer st;
        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                char type = st.nextToken().charAt(0);
                if (type == 'E') {
                    map[i][j] = EMPTY;
                } else {
                    pieces[pCnt] = new Piece(type, i, j);
                    map[i][j] = pCnt++;
                }
            }
        }
        br.readLine(); // END 처리

        isRemoved = new boolean[pCnt]; // 제거 여부
        minCnt = pCnt; // 제거할 개수
    }//init

    private static class Piece {
        char type;
        int r;
        int c;

        Piece(char type, int r, int c) {
            this.type = type;
            this.r = r;
            this.c = c;
        }
    }//Piece


}//class