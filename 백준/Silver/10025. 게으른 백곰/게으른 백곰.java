import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10025
public class Main {
    private static final int INF = 1_000_000;
    private static int K; // 좌우 거리
    private static int[] ice; // 얼음 개수

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int max = 0;
        int start = 1;
        int end = Math.min(K * 2 + 1, INF + 1);

        while(end <= INF + 1) {
            int sum = ice[end] - ice[start-1];
            max = Math.max(max, sum);

            start++;
            end++;
        }

        return max;
    }//getMax

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ice = new int[INF + 2];

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken()); // 얼음의 양
            int x = Integer.parseInt(st.nextToken()) + 1; // 양동이의 좌표

            ice[x] = g;
        }

        for(int i=1; i<=INF+1; i++) {
            ice[i] += ice[i-1];
        }

        br.close();
    }//init

    
}//class