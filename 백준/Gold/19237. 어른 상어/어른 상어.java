import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19237
public class Main {
    static final int INF = 1000, X=0, T=1, Z=1001;
    static int N, M, K;
    static Shark[] sharks;
    static int[][][] dirArr;
    static int[][][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        init();

        // 격자의 모습
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 0) continue;
                map[i][j][X] = x;
                map[i][j][T] = K;
                sharks[x] = new Shark(x, i, j);
            }
        }

        // 각 상어의 방향
        st = new StringTokenizer(br.readLine());
        for(int x=1; x<=M; x++) {
            sharks[x].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        // 각 상어의 방향 우선순위
        for(int x=1; x<=M; x++) {
            for(int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<4; j++) {
                    dirArr[x][i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        br.close();

        int time = move();
        System.out.print(time);
    }//main

    private static int move() {
        int cnt = M;
        int time = 0;
        int r, c, d, nr, nc, sr, sc, sdir;
        boolean isEmpty;

        while(time <= INF) {
            if(cnt == 1) return time;
            time++;

            // 상어 이동
            // 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동
            for(int x=1; x<=M; x++) {
                if(sharks[x].r == -1) continue;
                r = sharks[x].r;
                c = sharks[x].c;
                d = sharks[x].dir;
                sr = -1;
                sc = -1;
                sdir = d;
                isEmpty = false;

                // 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
                // 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다.
                // 이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다.
                for(int i=0; i<4; i++) {
                    nr = r + dr[dirArr[x][d][i]];
                    nc = c + dc[dirArr[x][d][i]];
                    if(rangeCheck(nr, nc)) continue;

                    // 빈칸
                    if(map[nr][nc][X] == 0) {
                        isEmpty = true;
                        sharks[x].r = nr;
                        sharks[x].c = nc;
                        sharks[x].dir = dirArr[x][d][i];
                        break;
                    }else if(map[nr][nc][X] == x && sr == -1) {
                        sr = nr;
                        sc = nc;
                        sdir = dirArr[x][d][i];
                    }
                }

                if(!isEmpty) {
                    sharks[x].r = sr;
                    sharks[x].c = sc;
                    sharks[x].dir = sdir;
                }

            }

            // 냄새 감소
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j][X] == 0) continue;
                    if(--map[i][j][T] == 0) map[i][j][X] = 0;
                }
            }

            // 냄새 뿌리기
            for(int x=1; x<=M; x++) {
                r = sharks[x].r;
                c = sharks[x].c;
                if(r == -1) continue;

                if(map[r][c][X] != 0 && map[r][c][X] < x){
                    cnt--;
                    sharks[x].r = -1;
                }else{
                    map[r][c][X] = x;
                    map[r][c][T] = K;
                }
            }

        }

        return -1;
    }//move

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >=N;
    }//rangeCheck

    private static void init() {
        map = new int[N][N][2];
        sharks = new Shark[M+1];
        dirArr = new int[M+1][4][4];
    }//init

    static class Shark {
        int num;
        int r;
        int c;
        int dir;
        public Shark(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }
    }//Shark

}//class