import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1029
public class Main {
    private static int N; // 예술가의 수
    private static int[][] price; // j번 예술가가 i번 예술가에게 그 그림을 살 때의 가격


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main


    // 그 그림을 소유 했던 사람들 (잠시라도 소유했던 사람도 포함)의 최댓값을 출력
    private static void solution() {
        int bit = 1 << N;
        int[][] dp = new int[bit][N];

        //            현재 번호, 이전 가격, 방문 체크, dp
        int max = getMax(0, 0, 0, dp);
        System.out.print(max + 1); // 1번 아티스트도 포함한다
    }//solution


    private static int getMax(int cur, int prevPrice, int visited, int[][] dp) {
        if(dp[visited][cur] != 0) {
            return dp[visited][cur];
        }

        int nVisited = visited | (1 << cur);
        int cnt = 0;

        for(int next=1; next<N; next++) {
            if ((nVisited & (1 << next)) == 0 && price[cur][next] >= prevPrice) {
                int nCnt = getMax(next, price[cur][next], nVisited, dp) + 1;
                cnt = Math.max(cnt, nCnt);;
            }
        }

        return dp[visited][cur] = cnt;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 예술가의 수

        price = new int[N][N]; // j번 예술가가 i번 예술가에게 그 그림을 살 때의 가격

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                price[i][j] = tmp[j] - '0';
            }
        }

        br.close();
    }//init
    

}//class