import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1948
public class Main {
    private static int N, start, end; // 도시 개수, 시작 도시, 도착 도시
    private static int[] inDegree; // 진입차수
    private static long[] maxTime; // 도시별 도착 시간
    private static List<List<Edge>> list, reverseList; // 도로의 정보

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        long maxTime = getMaxTime(); // 모두 로마에 도착하는 데 걸리는 시간
        int cnt = getCnt(); // 황금을 칠해야 할 도로의 수

        System.out.printf("%d\n%d", maxTime, cnt);
    }//solve

    private static int getCnt() {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];

        q.offer(end);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(Edge prev : reverseList.get(cur)) {
                int to = prev.to;
                long time = prev.time;
                if (visited[cur][to]) continue;
                
                // 현재 도로가 가장 오래 달린 사람들이 지나온 도로면 황금칠
                if (maxTime[cur] - time == maxTime[to]) {
                    cnt++;
                    q.offer(to);
                    visited[cur][to] = true;
                }
            }
        }

        return cnt;
    }//getCnt

    private static long getMaxTime() {
        Queue<Edge> q = new LinkedList<>();
        int from = start;
        long time = 0;

        q.offer(new Edge(from, time));

        Edge cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            from = cur.to;
            time = cur.time;

            if (from == end) {
                continue;
            }

            for(Edge next : list.get(from)) {
                int to = next.to;
                if (inDegree[to] == 0) continue;

                maxTime[to] = Math.max(maxTime[to], time + next.time);

                if (--inDegree[to] == 0) {
                    q.offer(new Edge(to, maxTime[to]));
                }
            }
        }

        return maxTime[end];
    }//getMaxTime

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 도로의 개수

        list = new ArrayList<>(N+1); // 도로의 정보
        reverseList = new ArrayList<>(N+1); // 반대 방향 도로의 정보
        inDegree = new int[N+1]; // 진입차수
        maxTime = new long[N+1]; // 도착 최대 시간

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        // 도로의 정보가 주어진다
        StringTokenizer st;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            inDegree[to]++;
            list.get(from).add(new Edge(to, time));
            reverseList.get(to).add(new Edge(from, time));
        }

        // 한걸음과 로마의 도시 번호가 공백으로 구분되어 주어진다
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        br.close();
    }//init

    private static class Edge {
        int to;
        long time;
        Edge(int to, long time) {
            this.to = to;
            this.time = time;
        }
    }//Edge

}//class