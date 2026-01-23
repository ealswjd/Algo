import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2211
public class Main {
    private static final int MAX = 10004, START=1;
    private static int N;
    private static int[] prev;
    private static List<List<Line>> list;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[] originTime = dijkstra();
        int minCnt = N-1;
        boolean[][] checked = new boolean[N+1][N+1];
        StringBuilder ans = new StringBuilder();

        ans.append(minCnt).append('\n');

        for(int from=N; from>1; from--) {
            int to = prev[from];
            if (!checked[from][to]) {
                checked[from][to] = checked[to][from] = true;
                ans.append(from).append(' ').append(to).append('\n');
            }
        }

        System.out.print(ans);
    }//sol


    private static int[] dijkstra() {
        int[] minTime = new int[N+1];
        PriorityQueue<Line> pq = new PriorityQueue<>();

        Arrays.fill(minTime, MAX);

        int idx = START;
        int time = 0;
        minTime[idx] = time;
        pq.offer(new Line(idx, time));

        Line cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.to;
            time = cur.time;

            if (minTime[idx] < time) continue;

            for(Line next : list.get(idx)) {
                int to = next.to;
                int nTime = time + next.time;

                if (minTime[to] > nTime) {
                    prev[to] = idx;
                    minTime[to] = nTime;
                    pq.offer(new Line(to, nTime));
                }
            }
        }

        return minTime;
    }//dijkstra


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 두 정수 N, M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(N+1);
        prev = new int[N+1];

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
            prev[i] = -1;
        }

        // M개의 줄에는 회선의 정보를 나타내는 세 정수 A, B, C가 주어진다.
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // A번 컴퓨터와 B번 컴퓨터가 통신 시간이 C인 회선으로 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Line(b, c));
            list.get(b).add(new Line(a, c));
        }

        br.close();
    }//init

    private static class Line implements Comparable<Line> {
        int to; // 연결된 컴퓨터 번호
        int time; // 통신 시간
        int lineCnt; // 복구 회선 수

        Line(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Line o) {
            return this.time - o.time;
        }
    }//Line

}//class