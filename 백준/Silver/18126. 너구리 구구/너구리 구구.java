import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18126
public class Main {
    static int N;
    static long dist;
    static boolean[] visited;
    static ArrayList<ArrayList<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }//for

        StringTokenizer st;
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});
        }//for
        br.close();

        visited[0] = true;
        dfs(0, 0);

        System.out.print(dist);
    }//main

    private static void dfs(int cur, long sum) {
        if(sum > dist) dist = sum;

        for(int[] next : list.get(cur)) {
            if(visited[next[0]]) continue;
            visited[next[0]] = true;
            dfs(next[0], sum + next[1]);
        }
    }//dfs

}//class