import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3079
public class Main {
    private static int N, M; // 입국 심사대 개수, 친구들 수
    private static int[] times; // 각 심사대에서 심사를 하는데 걸리는 시간

    
    public static void main(String[] args) throws IOException {
        init();

        long time = getTime();
        System.out.print(time);
    }//main

    
    private static long getTime() {
        long time = 0;
        long min = 1;
        long max = (long) times[N - 1] * M;
        long mid;

        while(min <= max) {
            mid = (min + max) / 2;

            if(getSum(mid) < M) min = mid + 1;
            else {
                max = mid - 1;
                time = mid;
            }
        }

        return time;
    }//getTime

    
    private static long getSum(long mid) {
        long sum = 0;

        for(int i=0; i<N; i++) {
            sum += mid / times[i];
            if(sum >= M) break;
        }

        return sum;
    }//getSum

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 입국 심사대 개수
        M = Integer.parseInt(st.nextToken()); // 친구들 수

        times = new int[N]; // 각 심사대에서 심사를 하는데 걸리는 시간

        for(int i=0; i<N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(times);

        br.close();
    }//init

    
}//class