import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24954
public class Main {
    private static int N; // 물약의 종류
    private static int minCost; // 가장 저렴하게 물약을 살 수 있는 가격
    private static int[] cost; // 물약의 가격
    private static List<List<int[]>> list; // 물약 할인 정보


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        init();
        dfs(0, 0, 0);

        System.out.print(minCost);
    }//sol


    private static void dfs(int cnt, int coin, int checked) {
        if(cnt == N) {
            minCost = Math.min(minCost, coin);
            return;
        }
        if(minCost <= coin) return;

        for(int i=0; i<N; i++) {
            if((checked & (1 << i)) != 0) continue;

            // 할인 적용
            setting(i, -1);

            // 물약의 가격이 내려가더라도 0 이하로 내려가지는 않는다.
            int nextCost = coin + Math.max(cost[i], 1);
            dfs(cnt + 1, nextCost, checked | (1 << i));

            // 할인 취소
            setting(i, 1);
        }
    }//dfs


    private static void setting(int cur, int d) {
        for(int[] next : list.get(cur)) {
            int n = next[0];   // 할인되는 물약 번호
            int cnt = next[1]; // 할인되는 동전 개수

            cost[n] += cnt * d;
        }
    }//setting


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 물약의 종류

        cost = new int[N]; // 물약의 가격
        list = new ArrayList<>(N); // 물약 할인 정보

        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }

        // 물약의 가격이 공백을 사이에 두고 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            minCost += cost[i];
        }

        // 물약 할인 정보가 N개 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 할인되는 물약 개수

            for(int j=0; j<p; j++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1; // 할인되는 물약 번호
                int cnt = Integer.parseInt(st.nextToken()); // 할인되는 동전 개수

                list.get(i).add(new int[] {num, cnt});
            }
        }

        br.close();
    }//init


}//class