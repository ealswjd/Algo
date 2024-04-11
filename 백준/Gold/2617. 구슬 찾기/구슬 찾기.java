import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2617
public class Main {
    static int N;
    static boolean[][] visited;
    static ArrayList<ArrayList<Integer>> heavyList, lightList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            heavyList.get(b).add(a);
            lightList.get(a).add(b);
        }
        br.close();

        int cnt = 0;
        int half = (N+1) / 2;
        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1][2];

            int lightCnt = dfs(i, 0, 0, lightList);
            int heavyCnt = dfs(i, 1, 0, heavyList);

            if(lightCnt >= half || heavyCnt >= half) cnt++;
        }

        System.out.print(cnt);
    }//main


    private static int dfs(int cur, int idx, int cnt, ArrayList<ArrayList<Integer>> list) {
        visited[cur][idx] = true;
        if(list.get(cur).isEmpty()) return cnt;

        int tmp = 0;
        for(int next : list.get(cur)) {
            if(visited[next][idx]) continue;
            tmp += dfs(next, idx, cnt, list)+1;
        }
        cnt = tmp;

        return cnt;
    }//dfs


    private static void init() {
        heavyList = new ArrayList<>(N+1);
        lightList = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            heavyList.add(new ArrayList<>());
            lightList.add(new ArrayList<>());
        }
    }//init

}//class