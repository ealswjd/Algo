import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23030
public class Main {
    private static final int MAX = 987654321;
    private static int N, M; // 노선의 개수, 환승역의 개수
    private static List<List<Subway>> subwayInfo; // 노선 정보
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
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        int[][] visited = new int[N][];
        int transferTime = request.time; // 환승시간
        int line = request.startLine;
        int station = request.startStation;
        int time = 0;
        int endLine = request.endLine;
        int endStation = request.endStation;

        for(int i=0; i<N; i++) {
            visited[i] = new int[stationCnt[i]];
            Arrays.fill(visited[i], MAX);
        }

        pq.offer(new int[] {line, station, time});
        visited[line][station] = time;

        int[] cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            line = cur[0];
            station = cur[1];
            time = cur[2];

            if(visited[line][station] < time) continue;
            if(line == endLine && station == endStation) {
                return time;
            }

            // 인접한 역 이동
            // 이전역
            int nextStation = station - 1;
            if(nextStation >= 0 && visited[line][nextStation] > time + 1) {
                visited[line][nextStation] = time + 1;
                pq.offer(new int[] {line, nextStation, time + 1});
            }
            // 다음역
            nextStation = station + 1;
            if(nextStation < stationCnt[line] && visited[line][nextStation] > time + 1) {
                visited[line][nextStation] = time + 1;
                pq.offer(new int[] {line, nextStation, time + 1});
            }

            // 환승역일경우 환승
            if(subwayInfo.get(line).get(station).isTransfer) {
                Subway subway = subwayInfo.get(line).get(station);
                int nextLine = subway.transferLine;
                nextStation = subway.transferStation;

                if(visited[nextLine][nextStation] > time + transferTime) {
                    visited[nextLine][nextStation] = time + transferTime;
                    pq.offer(new int[] {nextLine, nextStation, time + transferTime});
                }
            }
        }

        return visited[endLine][endStation];
    }//getMinTime


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 노선의 개수 N(2 ≤ N ≤ 10)이 주어진다.
        N = Integer.parseInt(br.readLine());

        subwayInfo = new ArrayList<>(N); // 노선 정보
        stationCnt = new int[N];

        // 둘째 줄에 N개의 노선별로 속한 역의 개수가 차례로 주어진다. (1 ≤ 역의 수 ≤ 50)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int line=0; line<N; line++) {
            subwayInfo.add(new ArrayList<>());
            int cnt = Integer.parseInt(st.nextToken());
            stationCnt[line] = cnt;

            for(int station=0; station<cnt; station++) {
                subwayInfo.get(line).add(new Subway(line, station));
            }
        }

        // 셋째 줄에 환승역의 개수 M(1 ≤ M ≤ ⌊모든 역의 수 / 2⌋)이 주어진다.
        M = Integer.parseInt(br.readLine());

        // 넷째 줄부터 M개의 줄에 환승역의 정보가 주어진다.
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // P1번 노선의 P2역과 Q1번 노선의 Q2역이 연결된 환승역이라는 것
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;
            int q1 = Integer.parseInt(st.nextToken()) - 1;
            int q2 = Integer.parseInt(st.nextToken()) - 1;

            // 환승역 설정
            subwayInfo.get(p1).get(p2).setTransfer(q1, q2);
            subwayInfo.get(q1).get(q2).setTransfer(p1, p2);
        }

        // 사용자의 요청 개수 K(1 ≤ K ≤ 1,000)가 주어진다.
        int K = Integer.parseInt(br.readLine());
        requests = new Request[K];

        // K개의 줄에 걸쳐 5개의 양의 정수 T(0 ≤ T ≤ 1,000), U1, U2, V1, V2가 주어짐
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            // 환승에 걸리는 소요 시간이 T이며 출발지는 U1번 노선의 U2역이고 도착지는 V1번 노선의 V2역
            int t = Integer.parseInt(st.nextToken());
            int u1 = Integer.parseInt(st.nextToken()) - 1;
            int u2 = Integer.parseInt(st.nextToken()) - 1;
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;

            requests[i] = new Request(t, u1, u2, v1, v2);
        }

        br.close();
    }//init


    private static class Subway {
        int line; // 노선
        int station; // 역 번호
        boolean isTransfer; // 환승역 여부
        int transferLine; // 환승 노선
        int transferStation; // 환승역

        Subway(int line, int station) {
            this.line = line;
            this.station = station;
        }

        public void setTransfer(int transferLine, int transferStation) {
            this.isTransfer = true;
            this.transferLine = transferLine;
            this.transferStation = transferStation;
        }
    }//Subway


    private static class Request {
        int time; // 환승에 걸리는 소요 시간
        int startLine; // 출발 노선
        int startStation; // 출발 역
        int endLine; // 도착 노선
        int endStation; // 도착 역
        Request(int time, int startLine, int startStation, int endLine, int endStation) {
            this.time = time;
            this.startLine = startLine;
            this.startStation = startStation;
            this.endLine = endLine;
            this.endStation = endStation;
        }
    }//Request


}//class