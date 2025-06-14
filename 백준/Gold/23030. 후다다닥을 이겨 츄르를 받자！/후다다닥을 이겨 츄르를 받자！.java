import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23030
public class Main {
    private static final int MAX = 987654321, L = 1000, S = 50;
    private static final int TOTAL = L + S + 1;
    private static final int[] D = {-1, 1};
    private static int[] transfer; // 환승정보
    private static int[] stationCnt; // 노선별로 속한 역의 개수
    private static Request[] requests; // 사용자의 요청


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int minTime;

        // 각 사용자의 요청에 대하여 출발지에서 도착지로 가는 최단 시간을 한 줄에 하나씩 출력
        for(Request request : requests) {
            minTime = getMinTime(request); // 최단 시간
            ans.append(minTime).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static int getMinTime(Request request) {
        PriorityQueue<Station> pq = new PriorityQueue<>();
        int[] visited = new int[TOTAL];
        int transferTime = request.time; // 환승시간
        int cur = request.start; // 현재역
        int end = request.end; // 도착역
        int time = 0; // 소요시간

        Arrays.fill(visited, MAX);

        pq.offer(new Station(cur, time));
        visited[cur] = time;

        Station station;
        while(!pq.isEmpty()) {
            station = pq.poll();
            cur = station.station;
            time = station.time;

            if(visited[cur] < time) continue;
            if(cur == end) return time;

            // 인접한 역 이동
            for(int i=0; i<2; i++) {
                int next = cur + D[i];
                if(isAvailable(cur, next) && visited[next] > time + 1) {
                    visited[next] = time + 1;
                    pq.offer(new Station(next, time + 1));
                }
            }

            // 환승역일경우 환승
            if(transfer[cur] != 0) {
                int next = transfer[cur];
                if(visited[next] > time + transferTime) {
                    visited[next] = time + transferTime;
                    pq.offer(new Station(next, time + transferTime));
                }
            }
        }

        return visited[end];
    }//getMinTime


    private static boolean isAvailable(int cur, int next) {
        int curLine = cur / 100;
        int nextLine = next / 100;
        int nextStation = next % 100;

        return curLine == nextLine && nextStation > 0 && nextStation <= stationCnt[nextLine];
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 노선의 개수 N(2 ≤ N ≤ 10)이 주어진다.
        int N = Integer.parseInt(br.readLine());

        transfer = new int[TOTAL]; // 환승정보
        stationCnt = new int[N+1]; // 노선별로 속한 역의 개수

        // 둘째 줄에 N개의 노선별로 속한 역의 개수가 차례로 주어진다. (1 ≤ 역의 수 ≤ 50)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int line=1; line<=N; line++) {
            stationCnt[line] = Integer.parseInt(st.nextToken());
        }

        // 셋째 줄에 환승역의 개수 M(1 ≤ M ≤ ⌊모든 역의 수 / 2⌋)이 주어진다.
        int M = Integer.parseInt(br.readLine());

        // 넷째 줄부터 M개의 줄에 환승역의 정보가 주어진다.
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // P1번 노선의 P2역과 Q1번 노선의 Q2역이 연결된 환승역이라는 것
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            // 환승역 설정
            int p = p1 * 100 + p2;
            int q = q1 * 100 + q2;

            transfer[p] = q;
            transfer[q] = p;
        }

        // 사용자의 요청 개수 K(1 ≤ K ≤ 1,000)가 주어진다.
        int K = Integer.parseInt(br.readLine());
        requests = new Request[K];

        // K개의 줄에 걸쳐 5개의 양의 정수 T(0 ≤ T ≤ 1,000), U1, U2, V1, V2가 주어짐
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            // 환승에 걸리는 소요 시간이 T이며 출발지는 U1번 노선의 U2역이고 도착지는 V1번 노선의 V2역
            int t = Integer.parseInt(st.nextToken());
            int u1 = Integer.parseInt(st.nextToken());
            int u2 = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            requests[i] = new Request(t, u1 * 100 + u2, v1 * 100 + v2);
        }

        br.close();
    }//init


    private static class Station implements Comparable<Station>{
        int station; // 현재역
        int time; // 소요시간
        Station(int station, int time) {
            this.station = station;
            this.time = time;
        }

        @Override
        public int compareTo(Station s) {
            return this.time - s.time;
        }
    }//Station


    private static class Request {
        int time; // 환승에 걸리는 소요 시간
        int start; // 출발역
        int end; // 도착역
        Request(int time, int start, int end) {
            this.time = time;
            this.start = start;
            this.end = end;
        }
    }//Request


}//class