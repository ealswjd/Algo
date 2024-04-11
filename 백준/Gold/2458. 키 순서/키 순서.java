import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2458
public class Main {
    static int N;
    static boolean[][] visited;
    static ArrayList<ArrayList<Integer>> tallList, shortList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생들의 수
        int M = Integer.parseInt(st.nextToken()); // 두 학생 키를 비교한 횟수

        init();

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            // a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tallList.get(a).add(b);
            shortList.get(b).add(a);
        }
        br.close();

        int sum = 0;
        int shortCnt, tallCnt;

        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1][2];

            shortCnt = dfs(i, 0, 0, shortList);
            tallCnt = dfs(i, 0, 1, tallList);

            if(shortCnt + tallCnt == N-1) sum++;
        }

        System.out.print(sum);
    }//main

    
    private static int dfs(int cur, int cnt, int idx, ArrayList<ArrayList<Integer>> list) {
        visited[cur][idx] = true;

        int tmp = cnt;
        for(int next : list.get(cur)) {
            if(visited[next][idx]) continue;
            cnt += dfs(next, tmp, idx, list) + 1;
        }

        return cnt;
    }//dfs

    
    private static void init() {
        tallList = new ArrayList<>(N+1);
        shortList = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            tallList.add(new ArrayList<>());
            shortList.add(new ArrayList<>());
        }
    }//init

}//class