import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16401
public class Main {
    private static int N; // 과자의 수
    private static int M; // 조카의 수
    private static int[] length; // 과자의 길이
    private static int sum;
    private static int maxLen;


    public static void main(String[] args) throws IOException {
        init();

        int maxLength = getMaxLength();
        System.out.print(maxLength);
    }//main


    private static int getMaxLength() {
        if(sum < M) return 0;

        int max = 0;
        int start = 1;
        int end = maxLen;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                start = mid + 1;
                max = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return max;
    }//getMaxLength


    private static boolean isPossible(int mid) {
        int cnt = 0;

        for(int len : length) {
            if(len < mid) continue;
            cnt += len / mid;
        }

        return cnt >= M;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 조카의 수
        N = Integer.parseInt(st.nextToken()); // 과자의 수

        length = new int[N]; // 과자의 길이

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            length[i] = Integer.parseInt(st.nextToken());
            sum += length[i];
            maxLen = Math.max(maxLen, length[i]);
        }

        br.close();
    }//init


}//class