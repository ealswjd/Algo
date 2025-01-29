import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2515
public class Main {
    private static int N; // 그림의 개수
    private static int S; // 판매가능 그림 높이 기준
    private static Picture[] pictures; // 그림


    public static void main(String[] args) throws IOException {
        init();

        int maxCost = getMaxCost();
        System.out.print(maxCost);
    }//main


    private static int getMaxCost() {
        int[] dp = new int[N];
        dp[0] = pictures[0].cost;

        for(int i=1; i<N; i++) {
            int maxIndex = getMaxIndex(i);

            // 탐색 x
            if(maxIndex == -1) {
                dp[i] = Math.max(dp[i-1], pictures[i].cost);
            }
            else {
                dp[i] = Math.max(dp[i-1], dp[maxIndex] + pictures[i].cost);
            }
        }

        return dp[N-1];
    }//getMaxCost


    private static int getMaxIndex(int cur) {
        int start = 0;
        int end = cur;
        int mid, diff;

        while(start <= end) {
            mid = (start + end) / 2;
            diff = pictures[cur].height - pictures[mid].height;

            if(diff >= S) start = mid + 1;
            else end = mid - 1;
        }

        return end;
    }//getMaxIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 그림의 개수
        S = Integer.parseInt(st.nextToken()); // 판매가능 그림 높이 기준

        pictures = new Picture[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken()); // 높이
            int cost = Integer.parseInt(st.nextToken()); // 가격

            pictures[i] = new Picture(height, cost);
        }

        Arrays.sort(pictures);
        br.close();
    }//init


    private static class Picture implements Comparable<Picture>{
        int height;
        int cost;

        public Picture(int height, int cost) {
            this.height = height;
            this.cost = cost;
        }

        @Override
        public int compareTo(Picture o) {
            return this.height - o.height;
        }
    }//Picture

    
}//class