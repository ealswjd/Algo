import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/24482
 알고리즘 수업 - 깊이 우선 탐색 4
 */
public class Main {
    private static int N, R; // 정점의 수 N, 간선의 수 M, 시작 정점 R
    private static int[] depth; // 깊이
    private static List<List<Integer>> list;


    public static void main(String[] args) throws IOException {
        init();
        dfs(R, 0);
        print();
    }//main


    private static void dfs(int cur, int d) {
        depth[cur] = d;

        for(int next : list.get(cur)) {
            if(depth[next] != -1) continue;
            dfs(next, d+1);
        }
    }//dfs


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<N; i++) {
            ans.append(depth[i]).append('\n');
        }
        ans.append(depth[N]);

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 정점의 수 N, 간선의 수 M, 시작 정점 R
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        depth = new int[N+1]; // 깊이
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            depth[i] = -1;
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 내림차순 정렬
        for(int i=1; i<=N; i++) {
            list.get(i).sort(Collections.reverseOrder());
        }

        br.close();
    }//init


}//class