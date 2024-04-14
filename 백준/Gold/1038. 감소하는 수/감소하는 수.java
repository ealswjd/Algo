import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1038
public class Main {
    static final long INF = 9876543210L;
    static int N, max, total;
    static long[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();

        if(N <= 10) System.out.print(N);
        else {
            num = new long[N+1];
            max = -1;

            for(int i=1; i<=10; i++) {
                if(total > N || max != -1) break;
                comb(0, 0, i);
            }

            if(max != -1 && N >= max) System.out.print(-1);
            else System.out.print(num[N]);
        }


    }//main

    private static void comb(int cnt, long sum, int C) {
        if(total > N || max != -1) return;
        if(cnt == C) {
            num[total++] = sum;
            if (sum == INF) max = total;
            return;
        }

        for(int i=0; i<10; i++) {
            if(cnt != 0 && i >= sum%10) break;

            comb(cnt+1, sum * 10 + i, C);
        }

    }//comb


}//class