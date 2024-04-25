import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10836
public class Main {
    static int N, M;
    static int[][][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for(int day=1; day<=M; day++){
            st = new StringTokenizer(br.readLine());

            // 제일 왼쪽 열과, 제일 위쪽 행의 애벌레들은 자신이 자라는 정도를 스스로 결정
            first(st, N-1, 0, day);

            // 나머지 애벌레들은 자신의 왼쪽(L), 왼쪽 위(D), 위쪽(U)의 애벌레들이 다 자란 다음,
            // 그 날 가장 많이 자란 애벌레가 자란 만큼 자신도 자란다.
            second(day);
        }

        getResult();
    }//main


    private static void first(StringTokenizer st, int r, int c, int day) {
        int cnt;

        for(int i=0; i<3; i++) {
            cnt = Integer.parseInt(st.nextToken());
            while(cnt-->0) {
                map[r][c][day] = i;

                if(r-1 < 0) c++;
                else r--;
            }
        }
    }//first

    private static void second(int day) {
        for(int r=1; r<N; r++) {
            for(int c=1; c<N; c++) {
                map[r][c][day] = getMax(r, c, day);
            }
        }
    }//sol

    private static int getMax(int r, int c, int day) {
        return Math.max(map[r][c-1][day], Math.max(map[r-1][c-1][day], map[r-1][c][day]));
    }//getMax


    private static void getResult() {
        StringBuilder ans = new StringBuilder();

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                for(int d=1; d<=M; d++) {
                    map[r][c][d] += map[r][c][d-1];
                }
                ans.append(map[r][c][M]).append(' ');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//getResult

    private static void init() {
        map = new int[N][N][M+1];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j][0] = 1;
            }
        }
    }//init

}//class