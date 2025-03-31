import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/22868
public class Main {
    private static int N; // 정점의 개수
    private static int S, E; // 출발, 도착 정점
    private static List<List<Integer>> list;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        boolean[] checked = new boolean[N+1];

        int len = bfs(S, E, checked);
        len += bfs(E, S, checked);

        System.out.print(len);
    }//sol


    private static int bfs(int s, int e, boolean[] checked) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        int[] prev = new int[N+1];

        q.offer(new int[] {s, 0});
        visited[s] = true;
        prev[s] = s;

        int[] cur;
        int node, dist;
        while(!q.isEmpty()) {
            cur = q.poll();
            node = cur[0];
            dist = cur[1];

            if(node == e) {
                check(s, e, prev, checked);
                return dist;
            }

            for(int next : list.get(node)) {
                if(visited[next] || checked[next]) continue;

                prev[next] = node;
                visited[next] = true;
                q.offer(new int[] {next, dist + 1});
            }
        }

        return 0;
    }//bfs


    private static void check(int s, int e, int[] prev, boolean[] checked) {

        for(int i=e; prev[i]!=i; i=prev[i]) {
            checked[i] = true;
        }

        checked[s] = checked[e] = false;
    }//check


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        // 두 정점 사이를 잇는 도로 a, b가 공백으로 구분되어 주어진다.
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 정점 S와 정점 E가 공백으로 구분되어 주어진다.
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 정렬
        for(int i=1; i<=N; i++) {
            Collections.sort(list.get(i));
        }

        br.close();
    }//main


}//class