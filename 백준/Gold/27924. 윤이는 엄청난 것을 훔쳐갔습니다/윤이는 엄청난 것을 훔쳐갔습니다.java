import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 윤이는 엄청난 것을 훔쳐갔습니다 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/27924
 * */
public class Main {
    static final int A=0, B=1, C=2;
    static int N;
    static ArrayList<ArrayList<Integer>> list;
    static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 마을의 정점 개수

        init();

        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            list.get(u).add(v);
            list.get(v).add(u);
        }//for

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        br.close();

        visited[a][A] = 1;
        visited[b][B] = 1;
        visited[c][C] = 1;

        bfs(b, B);
        bfs(c, C);
        bfs(a, A);

        boolean isSuccess = false;

        for(int i=0; i<N; i++) {
            if(list.get(i).size() == 1 && visited[i][A] < visited[i][B] && visited[i][A] < visited[i][C]) {
                isSuccess = true;
                break;
            }
        }

        if(isSuccess) System.out.print("YES");
        else System.out.print("NO");
    }//main

    private static void bfs(int start, int name) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start, 2});

        int[] tmp;
        int cur, time;
        while(!q.isEmpty()) {
            tmp = q.poll();
            cur = tmp[0];
            time = tmp[1];

            for(int next : list.get(cur)) {
                if(visited[next][name] > 0) continue;
                visited[next][name] = time;
                q.offer(new int[] {next, time+1});
            }//for
        }//while
    }//bfs

    private static void init() {
        visited = new int[N][3];
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }
    }//init


}//class