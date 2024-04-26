import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 로봇 시뮬레이션 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/2174
 * */
public class Main {
    static final int N=0, E=1, S=2, W=3;
    static String result;
    static int R, C, K;
    static boolean isEnd;
    static Robot[] robots;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 로봇 개수
        int M = Integer.parseInt(st.nextToken()); // 명령 개수
        robots = new Robot[K];

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int r = R - Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            robots[i] = new Robot(r, c, d);
            map[r][c] = i+1;
        }

        result = "OK"; // 시뮬레이션 결과
        while(M-->0) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken()) - 1; // 명령을 내리는 로봇
            char m = st.nextToken().charAt(0); // 명령의 종류
            int cnt = Integer.parseInt(st.nextToken()); // 명령의 반복 횟수

            move(num, m, cnt);
            if(isEnd) break;
        }

        System.out.print(result);
    }//main

    private static void move(int num, char m, int cnt) {
        switch (m) {
            case 'L' :
                while(cnt-->0) {
                    robots[num].turnLeft();
                }
                break;
            case 'R' :
                while(cnt-->0) {
                    robots[num].turnRight();
                }
                break;
            default:
                moveForward(num, cnt);
                break;
        }
    }//isCrash

    private static void moveForward(int num, int cnt) {
        int r = robots[num].r;
        int c = robots[num].c;
        int d = robots[num].d;
        int nr = r;
        int nc = c;
        int Y = 0;
        int X = num+1;

        map[r][c] = 0;

        if(d == E) {
            nc += cnt;
            for(int i=c; i<=nc && i<C; i++) {
                if(map[r][i] != 0) {
                    Y = map[r][i];
                    break;
                }
            }
        }
        else if(d == W) {
            nc -= cnt;
            for(int i=c; i>=nc && i>=0; i--) {
                if(map[r][i] != 0) {
                    Y = map[r][i];
                    break;
                }
            }
        }
        else if(d == S) {
            nr += cnt;
            for(int i=r; i<=nr && i<R; i++) {
                if(map[i][c] != 0) {
                    Y = map[i][c];
                    break;
                }
            }
        }
        else {
            nr -= cnt;
            for(int i=r; i>=nr && i>=0; i--) {
                if(map[i][c] != 0) {
                    Y = map[i][c];
                    break;
                }
            }
        }

        // X번 로봇이 움직이다가 Y번 로봇에 충돌하는 경우
        if(Y != 0) {
            isEnd = true;
            result = "Robot " + X + " crashes into robot " + Y;
            return;
        }

        // X번 로봇이 벽에 충돌하는 경우
        if(rangeCheck(nr, nc)) {
            isEnd = true;
            result = "Robot " + X + " crashes into the wall";
            return;
        }

        robots[num].move(nr, nc);
        map[nr][nc] = X;
    }//moveForward

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }//rangeCheck

    static class Robot {
        int r;
        int c;
        int d;

        public Robot(int r, int c, char d) {
            this.r = r;
            this.c = c;
            this.d = getDir(d);
        }

        public void move(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void turnLeft() {
            this.d = this.d - 1;
            if(this.d < 0) this.d = W;
        }

        public void turnRight() {
            this.d = (this.d + 1) % 4;
        }

        private int getDir(char d) {
            if(d == 'E') return E;
            else if(d == 'W') return W;
            else if(d == 'S') return S;
            else return N;
        }
    }//Robot

}//class
