import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1939
public class Main {
    static int N, M;
    static int S, E;
    static ArrayList<ArrayList<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 섬의 개수
        M = Integer.parseInt(st.nextToken()); // 다리 정보 개수

        init();

        int maxW = 0;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a번 섬과
            int b = Integer.parseInt(st.nextToken()); // b번 섬을
            int c = Integer.parseInt(st.nextToken()); // 잇는 다리의 중량 제한 c

            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});

            maxW = Math.max(maxW, c);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 공장1
        E = Integer.parseInt(st.nextToken()); // 공장2

        int max = getMax(maxW);
        System.out.print(max);
    }//main

    private static int getMax(int end) {
        int start = 1;
        int mid;
        int max = 0;

        while(start <= end) {
            mid = (start + end) / 2;

            if(bfs(mid)) { // 중량 가능
                start = mid + 1;
                max = mid;
            }
            else end = mid - 1;
        }

        return max;
    }//getMax

    private static boolean bfs(int w) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.offer(S);
        visited[S] = true;

        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            if(cur == E) return true;

            for(int[] next : list.get(cur)) {
                if(visited[next[0]] || w > next[1]) continue;
                q.offer(next[0]);
                visited[next[0]] = true;
            }
        }

        return false;
    }//bfs

    private static void init() {
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class