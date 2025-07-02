import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/17218
public class Main {
    private static char[] A, B; // 키보드를 막 쳐서 나온 두 문자열
    private static int lenA, lenB; // 두 문자열 길이


    public static void main(String[] args) throws IOException{
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[lenA+1][lenB+1];

        for(int a=1; a<=lenA; a++) {
            for(int b=1; b<=lenB; b++) {
                if(A[a-1] == B[b-1]) {
                    dp[a][b] = dp[a-1][b-1] + 1;
                }
                else {
                    dp[a][b] = Math.max(dp[a-1][b], dp[a][b-1]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int a = lenA;
        int b = lenB;

        while(a > 0 && b > 0) {
            if(dp[a][b] == dp[a-1][b]) a--;
            else if(dp[a][b] == dp[a][b-1]) b--;
            else {
                a--;
                b--;
                stack.add(A[a]);
            }
        }

        while(!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 키보드를 막 쳐서 나온 두 문자열
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        // 두 문자열 길이
        lenA = A.length;
        lenB = B.length;

        br.close();
    }//init


}//class