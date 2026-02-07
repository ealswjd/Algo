import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/32197
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N; // 지하철역의 수
    private static int S, E;
    private static List<List<int[]>> list; // 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        int minCnt = getMinCnt();

        System.out.print(minCnt);
    }//main

    private static int getMinCnt() {
        Deque<int[]> q = new ArrayDeque<>();
        int[][] minCnt = new int[2][N+1];

        for(int i=0; i<2; i++) {
            Arrays.fill(minCnt[i], MAX);
        }

        int from, cnt, type;
        for(int[] next : list.get(S)) {
            int to = next[0];
            type = next[1];
            if (minCnt[type][to] > 0) {
                minCnt[type][to] = 0;
                q.addFirst(new int[] {to, 0, type});
            }
        }

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.pollFirst();
            from = cur[0]; // 역
            cnt = cur[1];  // 절연 횟수
            type = cur[2]; // 선로 타입

            if (minCnt[type][from] < cnt) {
                continue;
            }
            if (from == E) {
                return cnt;
            }

            for(int[] next : list.get(from)) {
                int to = next[0];    // 다음 역
                int nType = next[1]; // 선로 타입
                int t = type^nType;  // 절연 여부

                if (minCnt[nType][to] > cnt + t) {
                    minCnt[nType][to] = cnt + t;

                    if (t == 0) {
                        q.addFirst(new int[] {to, minCnt[nType][to], nType});
                    } else {
                        q.addLast(new int[] {to, minCnt[nType][to], nType});
                    }
                }
            }
        }

        return -1;
    }//getMinCnt

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지하철역의 수
        int M = Integer.parseInt(st.nextToken()); // 선로의 수

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, t});
            list.get(b).add(new int[] {a, t});
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        br.close();
    }//init

}//class