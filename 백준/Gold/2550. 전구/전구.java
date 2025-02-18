import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2550
public class Main {
    private static int N; // 스위치의 수(전구의 수)
    private static int[] button; // 스위치 번호들
    private static int[] bulb; // 전구 순서
    private static int[] count; // 개수 저장


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main


    private static void solution() {
        int max = getMax(); // 가장 많은 전구가 켜지게 하는 스위치의 수
        int cnt = max;
        StringBuilder ans = new StringBuilder();
        List<Integer> numbers = new ArrayList<>(max);

        for(int i=N-1; i>=0 && cnt > 0; i--) {
            if(count[i] == cnt) {
                numbers.add(button[i]);
                cnt--;
            }
        }

        // 가장 많은 전구가 켜지게 하는 스위치의 수를 출력
        ans.append(max).append('\n');

        // 눌러야 하는 스위치의 번호를 오름차순으로 빈칸을 사이에 두고 하나의 줄에 출력
        Collections.sort(numbers);
        for(int num : numbers) {
            ans.append(num).append(' ');
        }

        System.out.print(ans);
    }//solution


    private static int getMax() {
        int[] dp = new int[N+1];
        int cnt = 0;
        int idx;

        for(int i=0; i<N; i++) {
            int num = button[i]; // 스위치 번호

            if(bulb[num] > dp[cnt]) {
                dp[++cnt] = bulb[num];
                count[i] = cnt;
                continue;
            }

            idx = getIndex(bulb[num], cnt, dp);
            dp[idx] = bulb[num];
            count[i] = idx;
        }

        return cnt;
    }//getMax


    private static int getIndex(int target, int end, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] > target) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 스위치의 수(전구의 수)

        button = new int[N]; // 스위치 번호들
        bulb = new int[N+1]; // 전구 순서
        count = new int[N]; // 개수 저장

        // N개의 스위치 번호들이 위에서부터 순서대로 빈칸을 사이에 두고 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            button[i] = Integer.parseInt(st.nextToken());
        }

        // N개의 전구 번호들이 위에서부터 순서대로 빈칸을 사이에 두고 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int num = Integer.parseInt(st.nextToken());
            bulb[num] = i;
        }

        br.close();
    }//init


}//class