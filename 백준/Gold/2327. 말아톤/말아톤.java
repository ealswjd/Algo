import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2327
public class Main {
    static final int INF = 100_001;
    static int H, N; // 구성원 키 조건 H, 학생 수 N
    static int[] height, speed;

    
    public static void main(String[] args) throws IOException {
        init();

        int result = getSpeed();
        System.out.println(result);
    }//main

    
    private static int getSpeed() {
        int[] dp = new int[H+1];
        Arrays.fill(dp, -1);
        dp[0] = INF;

        for(int i=0; i<N; i++) {
            int h = height[i];  // i번 학생 키
            int s = speed[i];   // i번 학생 속도

            for(int j=H; j>=h; j--) {
                // 구성원 중 달리기 속도가 가장 느린 사람이 가장 빠르게 되도록 팀을 선발
                dp[j] = Math.max(dp[j], Math.min(dp[j - h], s));
            }
        }

        // 키가 H일 때 가장 느린 팀원의 속도
        return dp[H];
    }//getSpeed

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken()); // 구성원들의 키 합이 정확히 H가 되어야 함
        N = Integer.parseInt(st.nextToken()); // 학생 수

        height = new int[N]; // 학생의 키
        speed = new int[N]; // 학생의 달리기 속도

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            height[i] = Integer.parseInt(st.nextToken()); // 키
            speed[i] = Integer.parseInt(st.nextToken()); // 달리기 속도
        }

        br.close();
    }//init

    
}//class

/*
2 6
4 5
3 6
2 3
1 4
6 7
1 5
-----
4
 */