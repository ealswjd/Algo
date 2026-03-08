import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12896
public class Main {
    private static int N; // 도시의 수
    private static int end, len;
    private static List<List<Integer>> list; // 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        dfs(1, 0, new boolean[N+1]);
        dfs(end, 0, new boolean[N+1]);

        int result = (len + 1) / 2;
        System.out.print(result);
    }//sol

    private static void dfs(int cur, int dist, boolean[] visited) {
        if (len < dist) {
            len = dist;
            end = cur;
        }

        visited[cur] = true;

        for(int next : list.get(cur)) {
            if (visited[next]) continue;
            dfs(next, dist + 1, visited);
        }
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시의 수

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        br.close();
    }//init

}//class