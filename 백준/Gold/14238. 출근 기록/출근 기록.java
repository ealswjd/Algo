import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14238
public class Main {
    static final int A=0, B=1, C=2;
    static int N;
    static int[] cnt;
    static char[] S, result;
    static boolean[][][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        br.close();

        init();

        if(!getResult(0, 0, 0, 0, 0)) System.out.print(-1);
        else printResult();
    }//main

    private static boolean getResult(int a, int b, int c, int one, int two) {
        if(a == cnt[A] && b == cnt[B] && c == cnt[C]) return true;
        if(dp[a][b][c][one][two]) return false;

        dp[a][b][c][one][two] = true;

        if(a < cnt[A]) {
            result[a+b+c+1] = 'A';
            if(getResult(a+1, b, c, A, one)) return true;
        }

        if(b < cnt[B] && one != B) {
            result[a+b+c+1] = 'B';
            if(getResult(a, b+1, c, B, one)) return true;
        }

        if(c < cnt[C] && one!=C && two!=C) {
            result[a+b+c+1] = 'C';
            if(getResult(a, b, c+1, C, one)) return true;
        }

        return false;
    }//getResult

    private static void printResult() {
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<=N; i++) {
            ans.append(result[i]);
        }

        System.out.print(ans);
    }//getResult

    private static void init() {
        N = S.length;
        cnt = new int[3];

        for(int i=0; i<N; i++) {
            cnt[S[i] - 'A']++;
        }

        dp = new boolean[cnt[A]+1][cnt[B]+1][cnt[C]+1][3][3];
        result = new char[N+1];
    }//init

}//class