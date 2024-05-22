import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11725
public class Main {
    static int N;
    static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        br.close();

        bfs(1);
    }//main

    private static void bfs(int cur) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        int[] parent = new int[N+1];
        q.add(cur);
        visited[cur] = true;

        while(!q.isEmpty()) {
            cur = q.poll();

            for(int next : list.get(cur)) {
                if(visited[next]) continue;
                visited[next] = true;
                parent[next] = cur;
                q.offer(next);
            }
        }

        print(parent);
    }//bfs

    private static void print(int[] parent) {
        StringBuilder ans = new StringBuilder();

        for(int i=2; i<=N; i++) {
            ans.append(parent[i]).append('\n');
        }

        System.out.print(ans);
    }//print

    private static void init() {
        list = new ArrayList<>(N+1);
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class