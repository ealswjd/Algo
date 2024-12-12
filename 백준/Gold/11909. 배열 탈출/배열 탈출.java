import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11909
public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int[][] map;


    public static void main(String[] args) throws IOException {
        init();

        int min = getMinCost();
        System.out.print(min);
    }//main


    private static int getMinCost() {
        int[][] cost = new int[N+1][N+1];

        for(int i=0; i<=N; i++) {
            Arrays.fill(cost[i], INF);
            map[i][0] = 2222;
            map[0][i] = 2222;
        }

        cost[0][1] = 0;
        cost[1][0] = 0;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                int fromTop = cost[r-1][c];  // 위
                int fromLeft = cost[r][c-1]; // 왼쪽

                if(map[r-1][c] <= map[r][c]) {
                    fromTop += map[r][c] - map[r-1][c] + 1;
                }
                if(map[r][c-1] <= map[r][c]) {
                    fromLeft += map[r][c] - map[r][c-1] + 1;
                }

                cost[r][c] = Math.min(fromLeft, fromTop);
            }
        }

        return cost[N][N];
    }//getMinCost


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class