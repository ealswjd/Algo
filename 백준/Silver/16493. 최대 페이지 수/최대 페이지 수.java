import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16493
public class Main {
    static final int D=0, P=1;
    static int N, M;
    static int[][] chapters;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chapters = new int[M][2];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            chapters[i][D] = Integer.parseInt(st.nextToken()); // 읽는데 소요되는 일 수
            chapters[i][P] = Integer.parseInt(st.nextToken()); // 페이지 수
        }
        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int[] dp = new int[N+1];

        for(int[] chapter : chapters) {
            for(int i=N; i>0; i--) {
                if(i-chapter[D] < 0) continue;
                dp[i] = Math.max(dp[i], dp[i-chapter[D]] + chapter[P]);
            }
        }

        return dp[N];
    }//getMax

}//class