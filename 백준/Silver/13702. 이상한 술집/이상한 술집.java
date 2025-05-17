import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13702
public class Main {
    private static int N, K; // 주전자의 개수 N, 친구들의 수 K
    private static int max; // 주전자에 들어있는 가장 큰 용량
    private static int[] volume; // 주전자의 용량


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        long result = 0; // K명에게 나눠줄 수 있는 최대의 막걸리 용량
        long start = 1;
        long end = max;
        long mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isAvailable(mid)) {
                start = mid + 1;
                result = mid;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.print(result);
    }//sol


    private static boolean isAvailable(long mid) {
        long cnt = 0;

        for(int v : volume) {
            cnt += v / mid;
            if(cnt >= K) return true;
        }

        return false;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 주전자의 개수 N
        K = Integer.parseInt(st.nextToken()); // 친구들의 수 K

        volume = new int[N];

        for(int i=0; i<N; i++) {
            volume[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, volume[i]);
        }

        br.close();
    }//init


}//class