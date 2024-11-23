import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12763
public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N; // 건물의 개수
    private static int T, M; // 남은 시간 T, 현재 가지고 있는 돈 M
    private static int minCost;
    private static List<List<int[]>> list;
    private static int[] checkedTime;
    private static int[] checkedCost;


    public static void main(String[] args) throws IOException {
        init();

        int start = 1;
        checkedTime[start] = 0;
        checkedCost[start] = 0;
        dfs(start, 0, 0);

        if(minCost == INF) minCost = -1;
        System.out.print(minCost);
    }//main


    private static void dfs(int cur, int time, int cost) {
        if(cur == N) {
            minCost = Math.min(minCost, cost);
            return;
        }

        for(int[] next : list.get(cur)) {
            int nextTime = time + next[1];
            int nextCost = cost + next[2];
            int idx = next[0];

            if(nextTime > T || nextCost > M) continue;

            if(checkedTime[idx] > nextTime) {
                checkedTime[idx] = Math.min(checkedTime[idx], nextTime);
                dfs(idx, checkedTime[idx], nextCost);
            }
            if(checkedCost[idx] > nextCost) {
                checkedCost[idx] = Math.min(checkedCost[idx], nextCost);
                dfs(idx, nextTime, checkedCost[idx]);
            }
        }
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 건물의 개수 N이 주어진다.
        N = Integer.parseInt(br.readLine()); // 건물의 개수

        checkedTime = new int[N+1];
        checkedCost = new int[N+1];
        list = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
            checkedTime[i] = INF;
            checkedCost[i] = INF;
        }

        // 수업 출석까지 남은 시간 T와 현재 가지고 있는 돈 M이 차례로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 노트에 적혀있는 건물 사이의 길의 개수 L
        int L = Integer.parseInt(br.readLine());

        while(L-- > 0) {
            // 두 건물의 번호와 이동 시간(분), 택시비가 주어진다.
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, time, cost});
            list.get(b).add(new int[] {a, time, cost});
        }

        minCost = INF;

        br.close();
    }//init


}//class