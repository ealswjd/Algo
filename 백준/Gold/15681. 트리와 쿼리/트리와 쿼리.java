import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15681
public class Main {
    private static int[] count; // 정점 i를 루트로 하는 서브트리에 속한 정점의 수
    private static List<List<Integer>> list; // 연결 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점 개수
        int R = Integer.parseInt(st.nextToken()); // 루트 번호
        int Q = Integer.parseInt(st.nextToken()); // 쿼리 개수

        count = new int[N+1]; // 서브트리에 속한 정점의 수
        list = new ArrayList<>(N+1); // 연결 정보

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 서브트리에 속한 정점의 수 구하기
        dfs(R, -1);

        StringBuilder ans = new StringBuilder();
        while(Q-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ans.append(count[n]).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int dfs(int cur, int parent) {
        count[cur] = 1;

        for(int next : list.get(cur)) {
            if (next == parent) continue;
            count[cur] += dfs(next, cur);
        }

        return count[cur];
    }//dfs

}//class