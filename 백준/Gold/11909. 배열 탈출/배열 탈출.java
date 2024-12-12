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
        }

        cost[1][1] = 0;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(r == 1 && c == 1) continue;

                int fromTop = cost[r-1][c];
                int fromLeft = cost[r][c-1];

                if(r > 1 && map[r-1][c] <= map[r][c]) {
                    fromTop += map[r][c] - map[r-1][c] + 1;
                }
                if(c > 1 && map[r][c-1] <= map[r][c]) {
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