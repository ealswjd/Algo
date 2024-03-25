import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1949
public class Main {
    static final int X=0, O=1;
    static int N;
    static int[] cnts;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            cnts[i] = Integer.parseInt(st.nextToken());
            dp[i][X] = -1; // 선택 x
            dp[i][O] = -1; // 선택 o
        }

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        br.close();

        int cur = 1;
        int max = Math.max(getMax(cur, 0, O)+cnts[cur], getMax(cur, 0, X));
        System.out.print(max);
    }//main

    
    private static int getMax(int cur, int prev, int isSelected) {
        if(dp[cur][isSelected] != -1) return dp[cur][isSelected];

        dp[cur][isSelected] = 0;
        for(int next : list.get(cur)) {
            if(next == prev) continue;
            if(isSelected == O) {
                dp[cur][isSelected] += getMax(next, cur, X);
            }else {
                dp[cur][isSelected] += Math.max(getMax(next, cur, O)+cnts[next]
                                                , getMax(next, cur, X));
            }
        }//for

        return dp[cur][isSelected];
    }//getMax

    
    private static void init() {
        cnts = new int[N+1]; // 마을 주민 수
        dp = new int[N+1][2]; // 우수마을 주민 최댓값
        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class