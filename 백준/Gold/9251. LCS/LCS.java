import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9251
public class Main {
    static char[] A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        int len = getLength();
        System.out.println(len);
    }//main

    private static int getLength() {
        int aLen = A.length;
        int bLen = B.length;
        int[][] dp = new int[aLen+1][bLen+1];

        for(int a=1; a<=aLen; a++) {
            for(int b=1; b<=bLen; b++) {
                if(A[a-1] == B[b-1]) dp[a][b] = dp[a-1][b-1] + 1;
                else dp[a][b] = Math.max(dp[a-1][b], dp[a][b-1]);
            }
        }

        return dp[aLen][bLen];
    }//getLength

}//class