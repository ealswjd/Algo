import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/15804
public class Main {
    private static final int T = 0, P = 1, I = 2;
    private static int N, M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정류장 길이
        M = Integer.parseInt(st.nextToken()); // 영우가 타려는 버스

        Deque<int[]> busStop = new ArrayDeque<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int arrive = Integer.parseInt(st.nextToken()); // 정류장에 도착하는 시간
            int depart = Integer.parseInt(st.nextToken()); // 정차하고 있는 시간

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

        br.close();

        int position = busStop.peekLast()[I];
        System.out.print(position);
    }//main


}//class