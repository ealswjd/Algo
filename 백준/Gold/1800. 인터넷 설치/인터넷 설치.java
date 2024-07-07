import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1800
public class Main {
    static final int INF = 987654321;
    static int N, P, K;
    static int[] minArr;
    static ArrayList<ArrayList<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
        P = Integer.parseInt(st.nextToken()); // 케이블선 개수
        K = Integer.parseInt(st.nextToken()); // 공짜로 지원해주는 케이블 개수

        init();

        int max = 0; // 가장 비싼 가격
        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, cost});
            list.get(b).add(new int[] {a, cost});

            max = Math.max(max, cost);
        }
        br.close();

        int minCost = getMinCost(max);
        System.out.print(minCost);
    }//main

    private static int getMinCost(int max) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int cost = -1; // 원장선생님이 내게 되는 최소의 돈
        int start = 0;
        int end = max;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid, pq)) {
                end = mid - 1;
                cost = mid;
            }
            else start = mid + 1;
        }

        return cost;
    }//getMinCost

    private static boolean isPossible(int max, PriorityQueue<int[]> pq) {
        Arrays.fill(minArr, INF);

        int cur = 1; // 현재 컴퓨터 번호
        int cnt = 0; // max 보다 비싼 케이블 개수
        pq.offer(new int[] {cur, cnt});
        minArr[cur] = cnt;

        int[] tmp;
        while(!pq.isEmpty()) {
            tmp = pq.poll();
            cur = tmp[0];
            cnt = tmp[1];

            if(minArr[cur] < cnt) continue;

            for(int[] next : list.get(cur)) {
                int nextCnt = cnt;
                if(next[1] > max) nextCnt++;

                if(nextCnt < minArr[next[0]]) {
                    minArr[next[0]] = nextCnt;
                    pq.offer(new int[] {next[0], nextCnt});
                }
            }
        }

        return minArr[N] <= K;
    }//isPossible

    private static void init() {
        minArr = new int[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class
