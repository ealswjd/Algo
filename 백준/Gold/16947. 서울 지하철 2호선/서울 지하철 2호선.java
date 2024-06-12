import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16947
public class Main {
    static int N; // 역의 개수
    static boolean[] isCycle; // 순환선 여부
    static ArrayList<ArrayList<Integer>> list; // 역과 역을 연결하는 구간의 정보
    static int[] parent; // 부모 노드

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 역의 개수

        init();

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }
        br.close();

        getCycle();
        getMin();
    }//main

    private static void getMin() {
        StringBuilder ans = new StringBuilder();
        Queue<int[]> q = new LinkedList<>();
        int[] minArr = new int[N+1];
        Arrays.fill(minArr, -1);

        for(int i = 1; i <= N; i++) {
            if(isCycle[i]) {
                q.offer(new int[]{i, 0});
                minArr[i] = 0;
            }
        }

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int cur = tmp[0];
            int dist = tmp[1];

            for(int next : list.get(cur)) {
                if (minArr[next] != -1) continue;
                
                minArr[next] = dist + 1;
                q.offer(new int[]{next, dist + 1});
            }
        }

        for(int i = 1; i <= N; i++) {
            ans.append(minArr[i]).append(' ');
        }

        System.out.print(ans);
    }//getMin

    private static void getCycle() {
        boolean[] visited = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            if(visited[i]) continue;
            if(dfs(i, -1, visited)) break;
        }
        
    }//getCycle

    private static boolean dfs(int cur, int par, boolean[] visited) {
        visited[cur] = true;

        for (int next : list.get(cur)) {
            if (next == par) continue; // 부모 노드로 돌아가는 것 방지
            if (visited[next]) {
                markCycle(cur, next);
                return true;
            }
            
            parent[next] = cur;
            if (dfs(next, cur, visited)) {
                return true;
            }
        }
        
        return false;
    }//dfs

    private static void markCycle(int start, int end) {

        while (start != end) {
            isCycle[start] = true;
            start = parent[start];
        }

        isCycle[end] = true;
    }//markCycle

    private static void init() {
        list = new ArrayList<>(N+1); // 역과 역을 연결하는 구간의 정보
        isCycle = new boolean[N+1];
        parent = new int[N+1];
        Arrays.fill(parent, -1);

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class