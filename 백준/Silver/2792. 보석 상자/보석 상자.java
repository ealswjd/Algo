import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2792
public class Main {
    private static int N, M, maxCnt; // 학생 수, 보석 색상 수
    private static int[] count; // 각 색상 보석의 개수


    public static void main(String[] args) throws IOException {
        init();
        int result = getMin();

        System.out.print(result);
    }//main


    private static int getMin() {
        int minCnt = 0;
        int start = 1;
        int end = maxCnt;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isAvailable(mid)) {
                end = mid - 1;
                minCnt = mid;
            } else {
                start = mid + 1;
            }
        }

        return minCnt;
    }//getMin


    private static boolean isAvailable(int mid) {
        long cnt = 0;

        for(int color : count) {
            cnt += (color / mid);
            if(color % mid != 0) cnt++;

            if(cnt > N) break;
        }

        return cnt <= N;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 보석 색상 수

        count = new int[M]; // 각 색상 보석의 개수

        for(int i=0; i<M; i++) {
            count[i] = Integer.parseInt(br.readLine());
            maxCnt = Math.max(maxCnt, count[i]);
        }

        br.close();
    }//init


}//class