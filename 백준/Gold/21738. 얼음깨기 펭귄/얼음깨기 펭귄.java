import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 얼음깨기 펭귄 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/21738
 * */
public class Main {
    static int N, S;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 얼음 블록의 개수
        S = Integer.parseInt(st.nextToken()); // 지지대 얼음 개수
        int P = Integer.parseInt(st.nextToken()); // 펭귄이 위치한 얼음 번호

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }//for

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }//for
        br.close();

        int cnt = bfs(P);
        System.out.print(cnt);
    }//main

    private static int bfs(int cur) {
        int total = N;
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.offer(new int[] {cur, 0});
        visited[cur] = true;

        int sCnt = 0, cnt;
        int[] tmp;
        while(!q.isEmpty()) {
            tmp = q.poll();
            cur = tmp[0];
            cnt = tmp[1];
            if(cur <= S) sCnt++;
            if(sCnt<=2 && cur <= S) {
                total -= cnt;
                if(sCnt == 2) break;
            }

            for(int next : list.get(cur)) {
                if(visited[next]) continue;
                visited[next] = true;
                q.offer(new int[] {next, cnt+1});
            }//for
        }//while

        return total - 1; // 1은 펭귄 얼음
    }//bfs

}//class