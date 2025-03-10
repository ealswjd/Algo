import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19542
public class Main {
    private static int N; // 노드의 개수
    private static int S, D; // 케니소프트의 위치, 힘
    private static int minDist; // 이동해야 하는 최소 거리
    private static boolean[] visited; // 방문 체크
    private static List<List<Integer>> list; // // 연결 정보


    public static void main(String[] args) throws IOException {
        init();
        dfs(S);

        System.out.print(minDist);
    }//init


    private static int dfs(int cur) {
        if(visited[cur]) return 0;
        visited[cur] = true;

        int max = 0;

        for(int next : list.get(cur)) {
            if(visited[next]) continue;
            max = Math.max(max, dfs(next));
        }

        if(cur != S && max >= D) minDist += 2;

        return max + 1;
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드의 개수
        S = Integer.parseInt(st.nextToken()); // 케니소프트의 위치
        D = Integer.parseInt(st.nextToken()); // 힘

        list = new ArrayList<>(N+1); // 연결 정보
        visited = new boolean[N+1]; // 방문 체크

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.get(x).add(y);
            list.get(y).add(x);
        }

        br.close();
    }//init


}//class