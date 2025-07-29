import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2343
public class Main {
    private static final int MAX = 1_000_000_000;
    private static int N, M; // 강의의 수 N, 블루레이 개수 M
    private static int maxLen;
    private static int[] length; // 기타 강의의 길이


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        init();

        int result = 0;
        int min = maxLen;
        int max = MAX;
        int mid;

        while(min <= max) {
            mid = (min + max) / 2;

            if(isAvailable(mid)) {
                max = mid - 1;
                result = mid;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.print(result);
    }//sol


    private static boolean isAvailable(long mid) {
        int sum = 0; // 블루레이의 크기
        int cnt = 1; // 블루레이 개수

        for(int len : length) {
            if(sum + len > mid) {
                sum = len;
                cnt++;
            }
            else {
                sum += len;
            }
        }

        return M >= cnt;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 강의의 수
        M = Integer.parseInt(st.nextToken()); // 블루레이 개수

        length = new int[N]; // 기타 강의의 길이

        // 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            length[i] = Integer.parseInt(st.nextToken());
            maxLen = Math.max(maxLen, length[i]);
        }

        br.close();
    }//init


}//class