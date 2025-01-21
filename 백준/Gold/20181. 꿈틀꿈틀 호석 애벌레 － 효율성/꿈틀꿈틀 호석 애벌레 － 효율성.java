import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20181
public class Main {
    private static int N, K;
    private static long[] sum;


    public static void main(String[] args) throws IOException {
        init();

        long max = getMax();
        System.out.print(max);
    }//main

    private static long getMax() {
        long[] max = new long[N+1];
        int s = 1, e = 1;
        long score, prev = 0;

        while(e <= N) {
            score = sum[e] - sum[s-1];

            if(score >= K) {
                prev = Math.max(prev, max[s - 1]);
                max[e] = Math.max(max[e], prev + score - K);
                s++;
            }
            else {
                e++;
            }
        }

        long result = 0;
        for(int i=1; i<=N; i++) {
            result = Math.max(result, max[i]);
        }

        return result;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sum = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class