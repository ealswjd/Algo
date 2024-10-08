import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2031
public class Main {
    static int T, N, D, K;
    static int[] times; // 음식 먹는 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken()); // T분 동안
        N = Integer.parseInt(st.nextToken()); // 먹을 N 종류의 음식
        D = Integer.parseInt(st.nextToken()); // 다이어트 효과 유지시간
        K = Integer.parseInt(st.nextToken()); // 김네마 실베스터 수

        times = new int[N+1]; // 음식 먹는 시간
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        Arrays.sort(times);

        int[] counts = new int[N+1];
        int[][] dp = new int[N+1][K+1];

        // counts[i] = i번째 음식에서 D만큼의 효과가 끝났을 때 포함되는 음식의 개수
        for(int i=1; i<=N; i++) {
            counts[i] = i - getIdx(1, i+1, times[i] - D + 1) + 1;
        }

        for(int k=1; k<=K; k++) {
            for(int n=1; n<=N; n++) {
                int cnt = counts[n];
                dp[n][k] = Math.max(dp[n-1][k], dp[n-cnt][k-1] + cnt);
            }
        }
        
        return dp[N][K];
    }//getMax

    
    private static int getIdx(int start, int end, int target) {
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(times[mid] < target) start = mid + 1;
            else end = mid;
        }

        return start;
    }//getIdx

    
}//class