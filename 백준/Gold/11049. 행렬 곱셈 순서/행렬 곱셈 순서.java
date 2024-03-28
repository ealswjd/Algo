import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 행렬 곱셈 순서 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/11049
 * */
public class Main {
    static final int R=0, C=1, INF=Integer.MAX_VALUE;
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 행렬의 개수
        arr = new int[N+1][2];

        StringTokenizer st;
        for(int i=1, idx=1; i<=N; i++, idx++) {
            st = new StringTokenizer(br.readLine());
            arr[i][R] = Integer.parseInt(st.nextToken());
            arr[i][C] = Integer.parseInt(st.nextToken());
        }//for
        br.close();

        int min = getMin();
        System.out.print(min);
    }//main

    private static int getMin() {
        int M = N+1;
        int[][] dp = new int[M][M];

        for(int i=1; i<N; i++){
            for(int s=1; s+i<=N; s++) {
                int e = s + i;
                dp[s][e] = INF;
                for(int m=s; m<e; m++) {
                    dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m+1][e] 
                                        + arr[s][R] * arr[m][C] * arr[e][C]);
                }
            }
        }

        return dp[1][N];
    }//getMin

}//class