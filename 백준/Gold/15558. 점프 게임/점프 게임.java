import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15558
public class Main {
    private static final int M = 2;
    private static final char WALL = '0';
    private static int N, K;
    private static char[][] map;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];
        int result = 0;

        int d = 0, idx = 0, time = 0;
        q.offer(new int[] {d, idx, time});
        visited[d][idx] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            d = cur[0];   // 현재 방향
            idx = cur[1]; // 현재 위치
            time = cur[2]; // 시간

            // 탈출 성공
            if(idx == N-1) {
                result = 1;
                break;
            }

            // 한 칸 앞으로 이동
            if(isAvailable(d, idx+1, time+1, visited)) {
                visited[d][idx+1] = true;
                q.offer(new int[] {d, idx+1, time+1});
            }
            // 한 칸 뒤로 이동
            if(idx-1 >= 0 && isAvailable(d, idx-1, time+1, visited)) {
                visited[d][idx-1] = true;
                q.offer(new int[] {d, idx-1, time+1});
            }
            // 반대편 줄로 점프
            if(idx + K >= N) {
                result = 1;
                break;
            }
            int nd = d ^ 1;
            if(isAvailable(nd, idx+K, time+1, visited)) {
                visited[nd][idx+K] = true;
                q.offer(new int[] {nd, idx+K, time+1});
            }
        }

        System.out.print(result);
    }//sol


    private static boolean isAvailable(int d, int idx, int time, boolean[][] visited) {
        return !visited[d][idx] && idx >= time && map[d][idx] != WALL;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[M][N];

        for(int i=0; i<M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        br.close();
    }//init


}//class