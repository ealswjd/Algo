import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/15804
public class Main {
    private static final int T = 0, P = 1, I = 2;
    private static int N, M;
    private static PriorityQueue<int[]> bus;

    
    public static void main(String[] args) throws IOException {
        init();

        int position = getPosition();
        System.out.print(position);
    }//main

    
    private static int getPosition() {
        Deque<int[]> busStop = new ArrayDeque<>();
        int arrive;
        int depart;

        while(!bus.isEmpty()) {
            int[] cur = bus.poll();
            arrive = cur[T]; // 도착
            depart = cur[P]; // 출발

            // 도착했을 때 정차시간 끝나는 이전 버스 제거
            while(!busStop.isEmpty() && busStop.peekFirst()[P] <= arrive) {
                busStop.pollFirst();
            }

            if(busStop.isEmpty()) {
                depart += arrive;
                busStop.add(new int[] {arrive, depart, 1});
            }
            else if(busStop.peekLast()[I] == N) {
                while(!busStop.isEmpty()) {
                    arrive = Math.max(arrive, busStop.pollFirst()[P]);
                }

                depart += arrive;
                busStop.addLast(new int[] {arrive, depart, 1});
            }
            else {
                arrive = Math.max(arrive, busStop.peekLast()[T]);
                depart += arrive;
                busStop.addLast(new int[] {arrive, depart, busStop.peekLast()[I] + 1});
            }

        }

        return busStop.peekLast()[I];
    }//getPosition

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정류장 길이
        M = Integer.parseInt(st.nextToken()); // 영우가 타려는 버스

        bus = new PriorityQueue<>(((o1, o2) -> {
            if(o1[T] == o2[T]) return o1[I] - o2[I];
            return o1[T] - o2[T];
        }));

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()); // 정류장에 도착하는 시간
            int p = Integer.parseInt(st.nextToken()); // 정차하고 있는 시간

            bus.offer(new int[] {t, p, i});
        }

        br.close();
    }//init

    
}//class