import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2228
public class Main {
    private static final int MIN = -3276800;
    private static int N, M;
    private static int[] sum;


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[][] max = new int[N+1][M+1];

        Arrays.fill(max[0], MIN);
        max[0][0] = 0;

        for(int n=1; n<=N; n++) {
            for(int m=1; m<=M; m++) {
                max[n][m] = max[n-1][m];
                for(int i=1; i<=n; i++) {
                    if(i >= 2) {
                        max[n][m] = Math.max(max[n][m], max[i-2][m-1] + sum[n] - sum[i - 1]);
                    }
                    else if(i == 1 && m == 1) {
                        max[n][m] = Math.max(max[n][m], sum[n]);
                    }
                }
            }
        }

        return max[N][M];
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 숫자 개수
        M = Integer.parseInt(st.nextToken()); // 구간 개수

        sum = new int[N+1];

        for(int i=1; i<=N; i++) {
            int num = Integer.parseInt(br.readLine());
            sum[i] = sum[i-1] + num;
        }

        br.close();
    }//init

    
}//class