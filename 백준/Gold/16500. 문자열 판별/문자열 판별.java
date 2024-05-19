import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/16500
public class Main {
    static int N;
    static String S; // 알파벳 소문자로 이루어진 문자열 S
    static String[] A; // 단어 목록 A

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        N = Integer.parseInt(br.readLine());
        A = new String[N];
        for(int i=0; i<N; i++) {
            A[i] = br.readLine();
        }
        br.close();

        int result = getResult();
        System.out.print(result);
    }//main

    private static int getResult() {
        int sLen = S.length(), len;
        int[] dp = new int[sLen];

        for(int i=0; i<sLen; i++) {
            for(int a=0; a<N; a++) {
                len = A[a].length();
                
                if((i==0 || dp[i-1]==1) && i+len <= sLen && S.indexOf(A[a], i) == i) {
                    dp[i+len-1] = 1;
                }
            }
        }

        return dp[sLen-1];
    }//getResult

}//class

/*
aaaaaaaaaa
2
aaaa
aaa
 */