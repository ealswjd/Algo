import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16118
public class Main {
    private static final long MAX = Long.MAX_VALUE;
    private static final int START=1;
    private static int N; // 그루터기의 개수
    private static List<List<Node>> list;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        long[] foxMinTime = getFoxMinTime();// 여우
        long[] wolfMinTime = getWolfMinTime(); // 늑대

        int total = 0; // 여우가 늑대보다 먼저 도착할 수 있는 그루터기의 개수
        for(int i=1; i<=N; i++) {
            if (foxMinTime[i] < wolfMinTime[i]) {
                total++;
            }
        }

        System.out.print(total);
    }//sol

    private static long[] getFoxMinTime() {
        long[] minTime = new long[N+1];
        PriorityQueue<Player> pq = new PriorityQueue<>();

        Arrays.fill(minTime, MAX);

        int idx = START;
        int time = 0;
        int order = 0;
        pq.offer(new Player(idx, time, order));
        minTime[idx] = time;

        Player cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.idx;
            time = cur.time;
            order = cur.order;

            if (minTime[idx] < time) {
                continue;
            }

            for(Node next : list.get(idx)) {
                int to = next.to;
                int nTime = time + next.time;
                
                if (minTime[to] > nTime) {
                    minTime[to] = nTime;
                    pq.offer(new Player(to, nTime, order+1));
                }
            }

        }

        return minTime;
    }//getFoxMinTime

    private static long[] getWolfMinTime() {
        long[][] minTime = new long[2][N+1];
        PriorityQueue<Player> pq = new PriorityQueue<>();

        for (int i=0; i<2; i++) {
            Arrays.fill(minTime[i], MAX);
        }

        int idx = START;
        int time = 0;
        int order = 0;
        pq.offer(new Player(idx, time, order));
        minTime[order][idx] = time;

        Player cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.idx;
            time = cur.time;
            order = cur.order;

            if (minTime[order][idx] < time) {
                continue;
            }

            int nOrder = order^1;
            for(Node next : list.get(idx)) {
                int to = next.to;
                int nTime = (nOrder == 1 ? next.time/2 : next.time*2) + time;
                
                if (minTime[nOrder][to] > nTime) {
                    minTime[nOrder][to] = nTime;
                    pq.offer(new Player(to, nTime, nOrder));
                }
            }

        }

        long[] result = new long[N+1];
        Arrays.fill(result, MAX);
        for(int i=1; i<=N; i++) {
            result[i] = Math.min(minTime[0][i], minTime[1][i]);
        }

        return result;
    }//getMinTime


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // (2 ≤ N ≤ 4,000, 1 ≤ M ≤ 100,000)
        // 그루터기의 개수와 오솔길의 개수를 의미하는 정수 N, M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            // (1 ≤ a, b ≤ N, a ≠ b, 1 ≤ d ≤ 100,000)
            // M개의 줄에 걸쳐 각 줄에 세 개의 정수 a, b, d가 주어진다.
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) * 2;

            // a와 b 사이에 길이가 d인 오솔길이 있음
            list.get(a).add(new Node(b, d));
            list.get(b).add(new Node(a, d));
        }

        br.close();
    }//init


    private static class Node {
        int to;
        int time;

        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }//Node

    private static class Player implements Comparable<Player> {
        int idx; // 그루터기 번호
        int time; // 시간
        int order; // 그루터기 수

        Player(int idx, int time, int order) {
            this.idx = idx;
            this.time = time;
            this.order = order;
        }

        @Override
        public int compareTo(Player p) {
            return this.time - p.time;
        }
    }//Player

}//class