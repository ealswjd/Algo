import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14499
public class Main {
    private static final int EAST=0, WEST=1, NORTH=2, SOUTH=3;
    private static int N, M;
    private static int[][] map;
    private static int[] dr = {0, 0, -1, 1}; // 동 서 북 남
    private static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        int X = Integer.parseInt(st.nextToken()); // 주사위 행
        int Y = Integer.parseInt(st.nextToken()); // 주사위 열
        int K = Integer.parseInt(st.nextToken()); // 명령 횟수

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();

        game(st, K, ans, X, Y);

        System.out.print(ans);
    }//main

    private static void game(StringTokenizer st, int K, StringBuilder ans, int r, int c) {
        Dice dice = new Dice();
        int nr, nc;
        int dir; // 방향

        while(K-->0) {
            dir = Integer.parseInt(st.nextToken()) - 1;
            nr = r + dr[dir];
            nc = c + dc[dir];

            // 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시
            if(rangeCheck(nr, nc)) continue;
            r = nr;
            c = nc;

            // 주사위 이동
            move(dir, nr, nc, dice);

            // 이동한 칸 0 -> 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
            // 0 아님 -> 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사, 칸 -> 0
            if(map[nr][nc] == 0) map[nr][nc] = dice.getBottom();
            else map[nr][nc] = 0;

            // 윗 면에 쓰여 있는 수 출력
            ans.append(dice.getTop()).append('\n');
        }//while

    }//game

    private static void move(int dir, int r, int c, Dice dice) {
        switch (dir) {
            case EAST :
                dice.moveEast(map[r][c]);
                break;
            case WEST :
                dice.moveWest(map[r][c]);
                break;
            case NORTH :
                dice.moveNorth(map[r][c]);
                break;
            case SOUTH :
                dice.moveSouth(map[r][c]);
                break;
        }
    }//move

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }//rangeCheck

    static class Dice {
        private int east;
        private int west;
        private int south;
        private int north;
        private int top;
        private int bottom;

        public int getEast() {
            return east;
        }

        public void setEast(int east) {
            this.east = east;
        }

        public int getWest() {
            return west;
        }

        public void setWest(int west) {
            this.west = west;
        }

        public int getSouth() {
            return south;
        }

        public void setSouth(int south) {
            this.south = south;
        }

        public int getNorth() {
            return north;
        }

        public void setNorth(int north) {
            this.north = north;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        // 동쪽 이동
        public void moveEast(int num) {
            int top = this.getTop();
            int bottom = this.getBottom();

            this.setBottom(this.getEast());
            this.setTop(this.getWest());
            this.setWest(bottom);
            this.setEast(top);

            if(num != 0) this.setBottom(num);
        }

        // 서쪽 이동
        public void moveWest(int num) {
            int top = this.getTop();
            int bottom = this.getBottom();

            this.setBottom(this.getWest());
            this.setTop(this.getEast());
            this.setWest(top);
            this.setEast(bottom);

            if(num != 0) this.setBottom(num);
        }

        // 북쪽 이동
        public void moveNorth(int num) {
            int top = this.getTop();

            this.setTop(this.getSouth());
            this.setSouth(this.getBottom());
            this.setBottom(this.getNorth());
            this.setNorth(top);

            if(num != 0) this.setBottom(num);
        }

        // 남쪽 이동
        public void moveSouth(int num) {
            int north = this.getNorth();

            this.setNorth(this.getBottom());
            this.setBottom(this.getSouth());
            this.setSouth(this.getTop());
            this.setTop(north);

            if(num != 0) this.setBottom(num);
        }

    }//Dice

}//class