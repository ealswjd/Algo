import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1561
public class Main {
    private static int N, M; // 아이들 수, 놀이기구 개수
    private static int max; // 최대 운행 시간
    private static int[] times; // 놀이기구 운행 시간

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        long time = getNumber(); // 최소 N명 태울 수 있는 시간
        long cnt = getCnt(time - 1); // time - 1까지 태운 아이 수
        long result = time; // 마지막 아이가 타게 되는 놀이기구의 번호

        for(int i=0; i<M; i++) {
            if(time % times[i] == 0) cnt++;
            if(cnt == N) {
                result = i + 1;
                break;
            }
        }

        System.out.print(result);
    }//sol


    private static long getNumber() {
        if(N <= M) return N;

        long start = 0;
        long end = (long) N * 30;
        long mid, cnt;

        while(start <= end) {
            mid = (start + end) / 2;
            cnt = getCnt(mid); // 탑승할 수 있는 아이 수

            if(cnt < N) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return start;
    }//getNumber


    private static long getCnt(long mid) {
        long cnt = M; // M명 먼저 탑승

        for(int i=0; i<M; i++) {
            cnt += mid / times[i];
        }

        return cnt;
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이들 수
        M = Integer.parseInt(st.nextToken()); // 놀이기구 개수

        times = new int[M]; // 놀이기구 운행 시간
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, times[i]);
        }

        br.close();
    }//init


}//class