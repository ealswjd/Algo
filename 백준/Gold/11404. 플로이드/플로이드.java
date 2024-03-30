import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11404
public class Main {
    static final int INF=987654321;
    static int N;
    static int[][] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            cost[a][b] = Math.min(cost[a][b], c);
        }//while
        br.close();

        floyd();
        print();
    }//main

    private static void print() {
        StringBuilder ans = new StringBuilder();
        for(int[] tmp : cost) {
            for(int c : tmp) {
                if(c == INF) ans.append(0).append(' ');
                else ans.append(c).append(' ');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//print

    private static void floyd() {
        for(int i=0; i<N; i++) {
            for(int s=0; s<N; s++) {
                for(int e=0; e<N; e++) {
                    cost[s][e] = Math.min(cost[s][e], cost[s][i] + cost[i][e]);
                }
            }
        }
    }//floyd

    private static void init() {
        cost = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }
    }//init

}//class