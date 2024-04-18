import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/24484
public class Main {
    static int N, cnt; // 정점 개수, 방문 순서
    static long sum; // di × ti 값의 합
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 개수
        int start = Integer.parseInt(st.nextToken()); // 시작 정점

        init();

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for(int i=1; i<=N; i++) {
            list.get(i).sort(Collections.reverseOrder());
        }

        dfs(start, 0);
        System.out.print(sum);
    }//main

    private static void dfs(int cur, long depth) {
        visited[cur] = true;
        sum += depth * cnt;

        for(int next : list.get(cur)) {
            if(visited[next]) continue;
            cnt++;
            dfs(next, depth+1);
        }
    }//dfs

    private static void init() {
        cnt = 1; // 방문 순서
        visited = new boolean[N+1];

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class