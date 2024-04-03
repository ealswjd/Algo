import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1749
public class Main {
    static final int MIN = -10_000;
    static int N, M;
    static int[][] map, sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        sum = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + map[i][j];
                map[i][j] = sum[i][j];
            }
        }
        br.close();

        System.out.print(getMax());
    }//main

    private static int getMax() {
        int max = MIN;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                for(int r2=r; r2<=N; r2++) {
                    for(int c2=c; c2<=M; c2++) {
                        max = Math.max(max, sum[r2][c2] - sum[r2][c-1] - sum[r-1][c2] + sum[r-1][c-1]);
                    }
                }
            }
        }

        return max;
    }//getMax

}//class