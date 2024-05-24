import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/24444
public class Main {
    static int N, M;
    static int[] orders;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 수
        M = Integer.parseInt(st.nextToken()); // 간선의 수
        int R = Integer.parseInt(st.nextToken()) - 1; // 시작 정점

        init();

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            list.get(a).add(b);
            list.get(b).add(a);
        }
        br.close();

        bfs(R);
        print();
    }//main

    private static void bfs(int cur) {
        // 인접 정점은 오름차순으로 방문
        for(int i=0; i<N; i++) {
            Collections.sort(list.get(i));
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        int cnt = 1;

        q.offer(cur);
        visited[cur] = true;

        while(!q.isEmpty()) {
            cur = q.poll();
            orders[cur] = cnt++;

            for(int next : list.get(cur)) {
                if(visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }

    }//bfs

    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int order : orders) {
            ans.append(order).append('\n');
        }

        System.out.print(ans);
    }//print

    private static void init() {
        orders = new int[N];
        list = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class