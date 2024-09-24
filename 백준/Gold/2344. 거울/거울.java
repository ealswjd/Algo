import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2344
public class Main {
    static final int R = 0, U = 1, L = 2, D = 3; // 우 상 좌 하
    static int N, M; // 행, 열
    static int[][] box; // 박스
    // 우 상 좌 하
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        box = new int[N][M]; // 박스

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findExitHole();
    }//main


    private static void findExitHole() {
        int cnt = N * 2 + M * 2; // 구멍 개수
        int[][][] boxDir = makeBoxNumbers(); // 박스 구멍 번호
        int[] exitNumbers = new int[cnt]; // 빠져나가는 구멍 번호
        int[] dir = {L, D, R, U};
        int n;

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                for(int i=0; i<4; i++) {
                    n = boxDir[r][c][i];

                    if(n != 0) {
                        int curDir = dir[i]; // 빛을 쏘는 방향
                        exitNumbers[n-1] = getNumber(r, c, curDir, boxDir);
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int number : exitNumbers) {
            ans.append(number).append(' ');
        }

        System.out.print(ans);
    }//findExitHole


    private static int getNumber(int r, int c, int dir, int[][][] boxDir) {
        int nr, nc;

        while(true) {
            // 거울이면 방향 변경
            if(box[r][c] == 1) {
                if(dir == R || dir == L) dir = (dir + 1) % 4;
                else dir--;
            }

            nr = r + dr[dir];
            nc = c + dc[dir];

            if(rangeCheck(nr, nc)) break;

            r = nr;
            c = nc;
        }

        return boxDir[r][c][dir];
    }//getNumber


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck


    private static int[][][] makeBoxNumbers() {
        int[][][] boxDir = new int[N][M][4];
        int num = 1;
        int dir = L;
        
        // 왼쪽
        for(int r=0, c=0; r<N; r++) {
            boxDir[r][c][dir] = num++;
        }

        // 아래
        dir = D;
        for(int c=0, r=N-1; c<M; c++) {
            boxDir[r][c][dir] = num++;
        }

        // 오른쪽
        dir = R;
        for(int r=N-1, c=M-1; r>=0; r--) {
            boxDir[r][c][dir] = num++;
        }

        // 위
        dir = U;
        for(int c=M-1, r=0; c>=0; c--) {
            boxDir[r][c][dir] = num++;
        }

        return boxDir;
    }//makeBoxNumbers

    
}//class