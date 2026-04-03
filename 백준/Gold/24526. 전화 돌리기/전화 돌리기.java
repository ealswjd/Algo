import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/24526
public class Main {
    private static int N; // 부원의 수
    private static int[] outDegree; // 진출 차수
    private static List<Set<Integer>> list;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        Queue<Integer> q = new LinkedList<>();
        int total = 0; // 전화를 넘길 수 있는 부원의 수

        for(int i=1; i<=N; i++) {
            if (outDegree[i] == 0) {
                total++;
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int prev : list.get(cur)) {
                if (outDegree[prev] == 0) continue;

                if (--outDegree[prev] == 0) {
                    total++;
                    q.offer(prev);
                }
            }
        }

        System.out.print(total);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 부원의 수
        int M = Integer.parseInt(st.nextToken()); // 관계의 수

        outDegree = new int[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new HashSet<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // from -> to로 전화를 넘길 수 있음
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (list.get(to).add(from)) {
                outDegree[from]++;
            }
        }

        br.close();
    }//init

}//class