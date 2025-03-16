import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1660
public class Main {
    private static final int MAX = 987654321;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int min = getMin(N);
        System.out.print(min);
    }//main


    private static int getMin(int N) {
        int M = 125;
        int[] size = new int[M];
        int[] cnt = new int[N+1];

        Arrays.fill(cnt, MAX);
        size[1] = 1;
        cnt[0] = 0;
        cnt[1] = 1;

        for(int i=2; i<M; i++) {
            size[i] = size[i-1] + size[i-1] - size[i-2] + i;
        }

        for(int i=2; i<=N; i++) {
            for(int j=1; j<M; j++) {
                if(size[j] > i) break;
                cnt[i] = Math.min(cnt[i], cnt[i - size[j]] + 1);
            }
        }

        return cnt[N];
    }//getMin


}//class