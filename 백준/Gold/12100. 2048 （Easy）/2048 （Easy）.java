import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12100
public class Main {
    private static final int U = 0, D = 1, L = 2, R = 3;
    private static int N; // 보드의 크기
    private static int[][] board; // 보드
    private static int result; // 가장 큰 블록
    private static int total; // 블록 총 개수
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    
    public static void main(String[] args) throws IOException {
        init();
        getMax();

        System.out.print(result);
    }//main

    
    private static void getMax() {
        if(total <= 1) return; // 블록 한개

        Queue<Integer> q = new LinkedList<>();

        // 상 하 좌 우 움직이기
        for(int i=0; i<4; i++) {
            move(i, 0, 0, copyArr(board), q);
        }

    }//getMax

    
    private static void move(int dir, int cnt, int max, 
                             int[][] original, Queue<Integer> q) {
        if(cnt == 5) {
            result = Math.max(result, max);
            return;
        }

        int[][] tmp = copyArr(original);
        int sum = 0;

        if(dir == U) sum = moveUp(tmp, q); // 상
        else if(dir == D) sum = moveDown(tmp, q); // 하
        else if(dir == L) sum = moveLeft(tmp, q); // 좌
        else sum = moveRight(tmp, q); // 우

        max = Math.max(max, sum);

        for(int i=0; i<4; i++) {
            move(i, cnt+1, max, tmp, q);
        }

    }//move

    
    private static int moveRight(int[][] map, Queue<Integer> q) {
        int max = 0;

        for(int r=0; r<N; r++) {
            for(int c=N-1; c>=0; c--) {
                if(map[r][c] == 0) continue;

                int cnt = 1;
                while(c - cnt >= 0 && map[r][c-cnt] == 0) {
                    cnt++;
                }

                if(c-cnt >= 0 && map[r][c] == map[r][c-cnt]) {
                    q.offer(map[r][c] * 2);
                    max = Math.max(max, map[r][c] * 2);
                    c -= cnt;
                }
                else {
                    q.offer(map[r][c]);
                    max = Math.max(max, map[r][c]);
                }
            }

            if(!q.isEmpty()) {
                Arrays.fill(map[r], 0);

                int c = N-1;
                while(!q.isEmpty()) {
                    map[r][c--] = q.poll();
                }
            }
        }

        return max;
    }//moveRight

    
    private static int moveLeft(int[][] map, Queue<Integer> q) {
        int max = 0;

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(map[r][c] == 0) continue;

                int cnt = 1;
                while(c + cnt < N && map[r][c+cnt] == 0) {
                    cnt++;
                }

                if(c+cnt < N && map[r][c] == map[r][c+cnt]) {
                    q.offer(map[r][c] * 2);
                    max = Math.max(max, map[r][c] * 2);
                    c += cnt;
                }
                else {
                    q.offer(map[r][c]);
                    max = Math.max(max, map[r][c]);
                }
            }

            if(!q.isEmpty()) {
                Arrays.fill(map[r], 0);

                int c = 0;
                while(!q.isEmpty()) {
                    map[r][c++] = q.poll();
                }
            }
        }

        return max;
    }//moveLeft

    
    private static int moveDown(int[][] map, Queue<Integer> q) {
        int max = 0;

        for(int c=0; c<N; c++) {
            for(int r=N-1; r>=0; r--) {
                if(map[r][c] == 0) continue;

                int cnt = 1;
                while(r - cnt >= 0 && map[r-cnt][c] == 0) {
                    cnt++;
                }

                if(r-cnt >= 0 && map[r][c] == map[r-cnt][c]) {
                    q.offer(map[r][c] * 2);
                    max = Math.max(max, map[r][c] * 2);
                    r -= cnt;
                }
                else {
                    q.offer(map[r][c]);
                    max = Math.max(max, map[r][c]);
                }
            }

            if(!q.isEmpty()) {
                for(int r=0; r<N; r++) {
                    map[r][c] = 0;
                }

                int r = N-1;
                while(!q.isEmpty()) {
                    map[r--][c] = q.poll();
                }
            }
        }

        return max;
    }//moveDown

    
    private static int moveUp(int[][] map, Queue<Integer> q) {
        int max = 0;

        for(int c=0; c<N; c++) {
            for(int r=0; r<N; r++) {
                if(map[r][c] == 0) continue;

                int cnt = 1;
                while(r + cnt < N && map[r+cnt][c] == 0) {
                    cnt++;
                }

                if(r+cnt < N && map[r][c] == map[r+cnt][c]) {
                    q.offer(map[r][c] * 2);
                    max = Math.max(max, map[r][c] * 2);
                    r += cnt;
                }
                else {
                    q.offer(map[r][c]);
                    max = Math.max(max, map[r][c]);
                }
            }

            if(!q.isEmpty()) {
                for(int r=0; r<N; r++) {
                    map[r][c] = 0;
                }

                int r = 0;
                while(!q.isEmpty()) {
                    map[r++][c] = q.poll();
                }
            }
        }

        return max;
    }//moveUp

    
    private static int[][] copyArr(int[][] original) {
        int[][] copy = new int[N][N];

        for(int i=0; i<N; i++) {
            copy[i] = Arrays.copyOf(original[i], N);
        }

        return copy;
    }//copyArr

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기

        board = new int[N][N]; // 보드
        StringTokenizer st;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] != 0) {
                    total++;
                    result = Math.max(result, board[i][j]);
                }
            }
        }

    }//init

    
}//class