import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24429
public class Main {
    static final int R = 0, C = 1;
    static int N, pCnt;
    static int[][] map;
    static List<int[]> pList;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pCnt = Integer.parseInt(br.readLine());
        for(int i=1; i<=pCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pList.add(new int[] {r, c});
        }

        int maxScore = getMaxScore();
        System.out.print(maxScore);
    }//main


    private static int getMaxScore() {
        Collections.sort(pList, (o1, o2) -> {
            if(o1[R] == o2[R]) return o1[C] - o2[C];
            return o1[R] - o2[R];
        });

        int[][] dp = new int[N+1][N+1];
        int[][] fromP = new int[N+1][N+1];

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]) + map[r][c];
            }
        }

        int prevR = pList.get(0)[R];
        int prevC = pList.get(0)[C];
        fromP[prevR][prevC] = dp[prevR][prevC];

        for(int p=1; p<pCnt; p++) {
            int r = pList.get(p)[R];
            int c = pList.get(p)[C];

            if(prevR < r && prevC > c) return -1;
            if(dp[prevR][prevC] == 0) return -1;

            calculate(prevR, prevC, r, c, fromP);
            prevR = r;
            prevC = c;
        }

        calculate(prevR, prevC, N, N, fromP);

        return fromP[N][N] > 0 ? fromP[N][N] : -1;
    }//getMaxScore

    
    private static void calculate(int prevR, int prevC, int r, int c, int[][] fromP) {

        for(int i=prevR; i<=r; i++) {
            for(int j=prevC; j<=c; j++) {
                if(i == prevR && j == prevC) continue;
                if(i == prevR) fromP[i][j] = fromP[i][j-1] + map[i][j];
                else if(j == prevC) fromP[i][j] = fromP[i-1][j] + map[i][j];
                else {
                    fromP[i][j] = Math.max(fromP[i-1][j], fromP[i][j-1]) + map[i][j];
                }
            }
        }

    }//calculate


    private static void init() {
        map = new int[N+1][N+1];
        pList = new ArrayList<>();
    }//init


}//class