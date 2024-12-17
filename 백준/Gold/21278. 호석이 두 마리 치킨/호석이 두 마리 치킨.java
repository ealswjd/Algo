import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21278
public class Main {
    private static final int INF = 2222222;
    private static int N;
    private static int[][] dist;


    public static void main(String[] args) throws IOException {
        init();
        floyd();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int storeA = 0;
        int storeB = 0;
        int min = Integer.MAX_VALUE;

        for(int a=1; a<=N; a++) {
            for(int b=a+1; b<=N; b++) {
                // a, b에 치킨집 
                int distance = getDistance(a, b);

                if(distance < min) {
                    min = distance;
                    storeA = a;
                    storeB = b;
                }
            }
        }

        ans.append(storeA).append(" ");
        ans.append(storeB).append(" ");
        ans.append(min * 2); // 왕복

        System.out.print(ans);
    }//sol


    private static int getDistance(int a, int b) {
        int sum = 0;

        // 모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합
        for(int i=1; i<=N; i++) {
            sum += Math.min(dist[a][i], dist[b][i]);
        }

        return sum;
    }//getDistance


    private static void floyd() {

        for(int m=1; m<=N; m++) { // 경유
            for(int s=1; s<=N; s++) { // 출발
                if(m == s) continue;

                for(int e=1; e<=N; e++) { // 도착
                    if(m == e || s == e) continue;

                    dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
                }
            }
        }

    }//floyd


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) continue;

                dist[i][j] = INF;
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        br.close();
    }//init


}//class