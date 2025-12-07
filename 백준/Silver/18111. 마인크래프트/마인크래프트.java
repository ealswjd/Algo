import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18111
public class Main {
    private static final int MAX_H = 256, MAX_T = Integer.MAX_VALUE;
    private static int N, M, B;
    private static int time, blockCnt;
    private static int[][] map;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int resultTime = MAX_T;
        int resultHeight = 0;

        for(int h=MAX_H; h>=0; h--) {
            blockCnt = B;
            time = 0;

            for(int r=0; r<N; r++) {
                for(int c=0; c<M; c++) {
                    // h로 땅 고르기 작업
                    levelGround(map[r][c], h);
                }
            }

            if(blockCnt >=0 && resultTime > time) {
                resultHeight = h;
                resultTime = time;
            }
        }

        // 땅을 고르는 데 걸리는 시간과 땅의 높이를 출력
        System.out.printf("%d %d", resultTime, resultHeight);
    }//sol


    private static void levelGround(int curHeight, int targetHeight) {
        if(curHeight > targetHeight) { // (2초) 블록을 제거하여 인벤토리에 넣는다.
            int cnt = curHeight - targetHeight;
            blockCnt += cnt;
            time += cnt * 2;
        } else if(curHeight < targetHeight){ // (1초) 인벤토리에서 블록 꺼내서 채우기
            int cnt = targetHeight - curHeight;
            blockCnt -= cnt;
            time += cnt;
        }
    }//levelGround


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, M, B가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵의 세로
        M = Integer.parseInt(st.nextToken()); // 맵의 가로
        B = Integer.parseInt(st.nextToken()); // 인벤토리에 있는 블록 개수

        map = new int[N][M];

        // 땅의 높이가 주어진다.
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class