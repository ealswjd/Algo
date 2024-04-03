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
                sum[i][j] = sum[i-1][j] + map[i][j];
            }
        }
        br.close();

        System.out.print(getMax());
    }//main

    private static int getMax() {
        int max = MIN;
        int score;

        for(int i=1; i<=N; i++) {
            for(int j=i; j<=N; j++) {
                score = 0;
                for(int k=1; k<=M; k++) {
                    if(score < 0) score = sum[j][k] - sum[i-1][k];
                    else score += sum[j][k] - sum[i-1][k];

                    max = Math.max(max, score);
                }
            }
        }

        return max;
    }//getMax

}//class