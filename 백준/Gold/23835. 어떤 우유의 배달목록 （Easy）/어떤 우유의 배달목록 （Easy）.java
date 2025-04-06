import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23835
public class Main {
    private static int N; // 방의 개수
    private static int[] count; // 배달한 우유의 총 개수
    private static String[] queries; // 쿼리
    private static List<List<Integer>> list; // 비밀 통로로 연결


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int num;

        for(String query : queries) {
            st = new StringTokenizer(query);
            num = Integer.parseInt(st.nextToken()); // 쿼리 종류

            if(num == 1) { // 배달
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                dfs(start, end, 0, new boolean[N+1]);
            }
            else { // x번 방에 배달한 우유의 총 개수를 출력
                int x = Integer.parseInt(st.nextToken());
                ans.append(count[x]).append('\n');
            }
        }

        System.out.print(ans);
    }//sol


    private static boolean dfs(int cur, int end, int cnt, boolean[] visited) {
        visited[cur] = true;

        if(cur == end) {
            count[cur] += cnt;
            return true;
        }

        boolean flag = false;
        for(int next : list.get(cur)) {
            if(visited[next]) continue;
            
            flag = dfs(next, end, cnt+1, visited);
            if(flag) break;
        }

        if(flag) count[cur] += cnt;
        return flag;
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 방의 개수

        count = new int[N+1]; // 배달한 우유의 총 개수
        list = new ArrayList<>(N+1); // 비밀 통로로 연결

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // a번 방과 b번 방이 비밀 통로로 연결되어 있다는 것을 의미한다.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        int Q = Integer.parseInt(br.readLine()); // 쿼리의 개수
        queries = new String[Q]; // 쿼리

        for(int i=0; i<Q; i++) {
            queries[i] = br.readLine();
        }

        br.close();
    }//init


}//class