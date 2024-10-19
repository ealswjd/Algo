import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2352
public class Main {
    static int N;
    static int[] port;

    public static void main(String[] args) throws IOException {
        init();
        getMax();
    }//main

    
    private static void getMax() {
        int[] dp = new int[N+1];
        int cnt = 0;

        for(int i=0; i<N; i++) {
            if(port[i] > dp[cnt]) {
                dp[++cnt] = port[i];

                continue;
            }

            int idx = binarySearch(cnt, port[i], dp);
            dp[idx] = port[i];
        }

        System.out.print(cnt);
    }//getMax

    
    private static int binarySearch(int end, int target, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] < target) start = mid + 1;
            else end = mid;
        }

        return end;
    }//binarySearch

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        port = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            port[i] = Integer.parseInt(st.nextToken());
        }
    }//init

    
}//class