import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1509
public class Main {
    static final int INF = 2522;
    static int N;
    static char[] str;
    static int[] dp;
    static boolean[][] palindrome;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        br.close();

        init();

        getPalindrome();

        int cnt = getMinCnt();
        System.out.print(cnt);
    }//main

    private static int getMinCnt() {
        
        for (int i = 0; i < N; i++) {
            if (palindrome[0][i]) {
                dp[i] = 1; 
            } else {
                for (int j = 0; j < i; j++) {
                    if (palindrome[j + 1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        return dp[N-1];
    }//getMinCnt

    private static void getPalindrome() {
        
        for (int length = 2; length <= N; length++) {
            for (int i = 0; i <= N - length; i++) {
                int j = i + length - 1;
                if (str[i] == str[j]) {
                    if (length == 2) {
                        palindrome[i][j] = true; 
                    } else {
                        palindrome[i][j] = palindrome[i + 1][j - 1]; 
                    }
                }
            }
        }
        
    }//getPalindrome

    private static void print() {
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(palindrome[i]));
        }
    }//print

    private static void init() {
        N = str.length;
        palindrome = new boolean[N][N];
        dp = new int[N];

        Arrays.fill(dp, INF);

        for(int i=0; i<N; i++) {
            palindrome[i][i] = true;
        }
    }//init

}//class