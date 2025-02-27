import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22953
public class Main {
    private static final long MAX = 1_000_000_000_001L;
    private static int N; // 요리사의 수
    private static int K; // 만들어야 할 음식의 개수
    private static int C; // 격려해줄 수 있는 횟수
    private static int[] times; // 요리사의 음식 조리 시간
    private static long minTime; // 요리에 걸리는 최소 시간


    public static void main(String[] args) throws IOException {
        init();

        // 요리에 걸리는 최소 시간을 출력
        back(0, 0);
        System.out.print(minTime);
    }//main


    private static void back(int cur, int depth) {
        minTime = Math.min(minTime, binarySearch());

        if(depth == C) return;

        for(int i=cur; i<N; i++) {
            if(times[i] == 1) continue;

            times[i]--;
            back(i, depth + 1);
            times[i]++;
        }
    }//back


    private static long binarySearch() {
        long start = 0;
        long end = MAX;
        long mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return end;
    }//binarySearch


    private static boolean isPossible(long time) {
        long cnt = 0;

        for(int i=0; i<N; i++) {
            cnt += time / times[i];
            if(cnt >= K) return true;
        }

        return false;
    }//isPossible


    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 요리사의 수
        K = Integer.parseInt(st.nextToken()); // 만들어야 할 음식의 개수
        C = Integer.parseInt(st.nextToken()); // 격려해줄 수 있는 횟수

        times = new int[N]; // 요리사의 음식 조리 시간

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        minTime = MAX;
        br.close();
    }//init


}//class