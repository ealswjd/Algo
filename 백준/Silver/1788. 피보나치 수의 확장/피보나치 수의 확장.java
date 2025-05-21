import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1788
public class Main {
    private static final int MOD = 1_000_000_000;


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        StringBuilder ans = new StringBuilder();
        int result = 1; // 양수이면 1, 0이면 0, 음수이면 -1

        if(N < 0) {
            N = N * (-1);
            if(N % 2 == 0) result = -1;
        }
        else if(N == 0){
            result = 0;
        }

        int[] f = new int[N+1];
        if(N > 0) f[1] = 1;

        for(int n=2; n<=N; n++) {
            f[n] = (f[n-1] + f[n-2]) % MOD;
        }

        ans.append(result).append('\n');
        ans.append(f[N]);
        System.out.print(ans);
    }//sol
    

}//class