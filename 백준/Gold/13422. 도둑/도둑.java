import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13422
public class Main {
    static int N, M, K;
    static int[] moneys;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 집의 개수
            M = Integer.parseInt(st.nextToken()); // 도득이 훔칠 수 있는 연속된 집의 개수
            K = Integer.parseInt(st.nextToken()); // 자동 방법장치가 작동하는 최소 돈의 양

            init();

            st = new StringTokenizer(br.readLine());
            int one = 0;
            for(int i=1; i<=N; i++) {
                moneys[i] = Integer.parseInt(st.nextToken());
                if(moneys[i] < K) one++;
            }

            int cnt = getCnt(one);
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main

    private static int getCnt(int one) {
        if(M == 1) return one;

        int cnt = 0;
        int[] sum = new int[N+M];

        for(int i=1; i<N+M; i++) {
            if(i <= N) sum[i] = sum[i-1] + moneys[i];
            else sum[i] = sum[i-1] + moneys[i%N];
        }

        int start = 1;
        int end = M;
        int total;

        while(start <= N) {
            total = sum[end] - sum[start-1];

            if(total < K) cnt++;
            start++;
            end++;

            if(N == M) break;
        }
        return cnt;
    }//getCnt

    private static void init() {
        moneys = new int[N+1];
    }//init

}//class
