import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/23031
public class Main {
    private static final String SUCCESS = "Phew...";
    private static final String FAIL = "Aaaaaah!";
    private static final char EMPTY = 'O', ZOMBI = 'Z', SWITCH = 'S';
    private static final int[] dr = {1, 0, -1, 0, -1, -1, 1, 1}; // 하 좌 상 우
    private static final int[] dc = {0, -1, 0, 1, -1, 1, 1, -1};
    private static final int[] right = {1, 2, 3, 0};
    private static final int[] left = {3, 0, 1, 2};
    private static int N; // 다솔관 크기
    private static char[][] map; // 다솔관
    private static char[] orders; // 이동할 순서
    private static List<User> zombiList; // 좀비 위치 정보
    private static boolean[][] isLightOn; // 불 켜져있는지 확인
    private static boolean isFail;
    private static User user;


    public static void main(String[] args) throws IOException {
        init();
        move();
    }//main


    private static void move() {

        for(char order : orders) {
            if(order == 'F') moveForward(user);
            else if(order == 'R') turnRight(user);
            else turnLeft(user);

            moveZombi();

            if(isFail) break;
        }

        if(isFail) System.out.print(FAIL);
        else System.out.print(SUCCESS);
    }//move


    private static void moveForward(User user) {
        int dir = user.dir;
        int r = user.r;
        int c = user.c;
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        // 벽에 부딪히면 이동 x
        if(rangeCheck(nr, nc)) {
            // 좀비면 뒤로 회전
            if(user.isZombi) {
                turnRight(user);
                turnRight(user);
            }
            return;
        }

        // 이동
        user.setUser(nr, nc, dir);

        // 스위치가 있는 칸에 도착
        if(map[nr][nc] == SWITCH) {
            switchOn(nr, nc);
        }

        if(user.isZombi) {
            map[r][c] = EMPTY;
            map[nr][nc] = ZOMBI;
        }

        // 좀비 마주치는지 확인
        if(check()) {
            isFail = true;
        }
    }//moveForward


    private static void switchOn(int r, int c) {
        isLightOn[r][c] = true;

        for(int i=0; i<8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(rangeCheck(nr, nc)) continue;

            isLightOn[nr][nc] = true;
        }
    }//switchOn


    private static void turnRight(User user) {
        int dir = right[user.dir];

        user.setUser(user.r, user.c, dir);
    }//turnRight


    private static void turnLeft(User user) {
        int dir = left[user.dir];

        user.setUser(user.r, user.c, dir);
    }//turnLeft


    private static void moveZombi() {
        int r, c;

        for(User zombi : zombiList) {
            r = zombi.r;
            c = zombi.c;

            moveForward(zombi);

            map[r][c] = EMPTY;
            map[zombi.r][zombi.c] = ZOMBI;

            if(check()) {
                isFail = true;
                return;
            }
        }

    }//moveZombi


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }//rangeCheck


    private static boolean check() {
        return !isLightOn[user.r][user.c] && map[user.r][user.c] == ZOMBI;
    }//check


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 다솔관 크기

        map = new char[N][N]; // 다솔관
        isLightOn = new boolean[N][N]; // 불 켜져있는지 확인
        orders = br.readLine().toCharArray(); // 이동할 순서
        zombiList = new ArrayList<>(); // 좀비 위치 정보
        user = new User(0, 0, 0); // 아리

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if(map[i][j] == ZOMBI) {
                    zombiList.add(new User(i, j, 0, true));
                }
            }
        }

        br.close();
    }//init


    private static class User {
        int r; // 행
        int c; // 열
        int dir; // 방향
        boolean isZombi;

        public User(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public User(int r, int c, int dir, boolean isZombi) {
            this(r, c, dir);
            this.isZombi = isZombi;
        }

        public void setUser(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }//User


}//class