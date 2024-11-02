import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12920
public class Main {
    private static int M; // 민호가 들 수 있는 가방의 최대 무게
    private static List<Integer> W; // 물건의 무게
    private static List<Integer> C; // 민호의 만족도

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int[] dp = new int[M+1];

        for(int i=0, size=W.size(); i<size; i++) {
            int w = W.get(i);
            int c = C.get(i);

            for(int m=M; m>=w; m--) {
                dp[m] = Math.max(dp[m], dp[m-w] + c);
            }
        }

        return dp[M];
    }//getMax

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 개수
        M = Integer.parseInt(st.nextToken()); // 민호가 들 수 있는 가방의 최대 무게

        W = new ArrayList<>(); // 무게
        C = new ArrayList<>(); // 만족도

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken()); // 무게
            int cost = Integer.parseInt(st.nextToken()); // 만족도
            int k = Integer.parseInt(st.nextToken()); // 개수
            
            // 물건 분리
            for(int j=1; k>0; j<<=1) {
                int cnt = Math.min(j, k);
                int w = weight * cnt;
                int c = cost * cnt;

                W.add(w);
                C.add(c);

                k -= cnt;
            }
        }

    }//init

    
}//class