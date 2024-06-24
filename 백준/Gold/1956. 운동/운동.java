import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 도로의 길이의 합이 가장 작은 사이클을 찾는 프로그램
// https://www.acmicpc.net/problem/1956
public class Main {
    static final int INF = 9_000_000;
    static int V;
    static int[][] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 마을 개수
        int E = Integer.parseInt(st.nextToken()); // 도로 개수

        init();

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            // a -> b 까지 길이 c
            cost[a][b] = c;
        }

        int min = floyd();
        System.out.print(min);
    }//main

    private static int floyd() {
        int min = INF; // 도로의 길이의 합 최솟값

        for(int k=0; k<V; k++) { // 경유지
            for(int s=0; s<V; s++) { //출발
                for(int e=0; e<V; e++) { // 도착
                    cost[s][e] = Math.min(cost[s][e], cost[s][k] + cost[k][e]);
                }
                
                // 사이클 가능하다면 최솟값 갱신
                min = Math.min(min, cost[s][s]);
            }
        }

        if(min == INF) return -1; // 운동 경로 찾기 불가능
        return min;
    }//floyd

    private static void init() {
        cost = new int[V][V];

        for(int i=0; i<V; i++) {
            Arrays.fill(cost[i], INF);
        }
    }//init

}//class