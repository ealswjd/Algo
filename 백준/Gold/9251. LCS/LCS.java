import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int lenA, lenB;
    static char[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        br.close();

        init();

        int length = getLength();
        System.out.print(length);
    }//main

    private static int getLength() {

        for(int a=1; a<=lenA; a++) {
            for(int b=1; b<=lenB; b++) {
                if(A[a-1] == B[b-1]) dp[a][b] = dp[a-1][b-1] + 1;
                else {
                    dp[a][b] = Math.max(dp[a-1][b], dp[a][b-1]);
                }
            }
        }

        return dp[lenA][lenB];
    }//getMinCnt

    private static void init() {
        lenA = A.length;
        lenB = B.length;
        dp = new int[lenA+1][lenB+1];
    }//init

}//class