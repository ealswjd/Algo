import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15483
public class Main {
    static int lenA, lenB;
    static char[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // A에 연산(삽입, 삭제, 교체)을 최소 횟수로 수행해 B로 만드는 문제
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        br.close();

        init();

        int minCnt = getMin();
        System.out.print(minCnt);
    }//main

    
    private static int getMin() {

        for(int a=1; a<=lenA; a++) {
            for(int b=1; b<=lenB; b++) {
                if(A[a-1] == B[b-1]) dp[a][b] = dp[a-1][b-1];
                else {
                    dp[a][b] = Math.min(dp[a-1][b], Math.min(dp[a][b-1], dp[a-1][b-1])) + 1;
                }
            }
        }

        return dp[lenA][lenB];
    }//getMin


    private static void init() {
        lenA = A.length;
        lenB = B.length;
        dp = new int[lenA+1][lenB+1];

        for(int i=0; i<=lenA; i++) {
            dp[i][0] = i;
        }

        for(int i=0; i<=lenB; i++) {
            dp[0][i] = i;
        }
    }//init

}//class