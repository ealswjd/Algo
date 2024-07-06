import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2234
public class Main {
    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static Map<Integer, Integer> room;
    static int[] dr = {0, -1, 0, 1}; // 서 북 동 남
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        init();

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();

        sol();
    }//main

    
    private static void sol() {
        StringBuilder ans = new StringBuilder();

        // 이 성에 있는 방의 개수, 가장 넓은 방의 넓이 구하기
        getRoom(ans);

        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기
        removeWall(ans);

        System.out.print(ans);
    }//sol

    
    private static void removeWall(StringBuilder ans) {
        int max = 0;
        int num;

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                num = map[r][c];

                for(int i=0; i<4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(rangeCheck(nr, nc) || map[nr][nc] == num) continue;

                    max = Math.max(max, room.get(num) + room.get(map[nr][nc]));
                }
            }
        }

        ans.append(max);
    }//removeWall

    
    private static void getRoom(StringBuilder ans) {
        Queue<int[]> q = new LinkedList<>();
        int max = 0;
        int num = 1;

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(visited[r][c]) continue;

                int size = bfs(r, c, num, q);
                room.put(num, size);
                max = Math.max(max, size);
                num++;
            }
        }

        ans.append(room.size()).append('\n');
        ans.append(max).append('\n');
    }//getRoom

    
    private static int bfs(int r, int c, int num, Queue<int[]> q) {
        q.offer(new int[] {r, c});
        visited[r][c] = true;
        int cnt = 0;

        int[] cur;
        int wall;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            wall = map[r][c];

            map[r][c] = num;
            cnt++;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 체크, 방문 체크
                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
                // 벽 체크
                if((wall & (1 << i)) != 0) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
        }

        return cnt;
    }//bfs

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }//rangeCheck

    
    private static void init() {
        map = new int[R][C];
        visited = new boolean[R][C];
        room = new HashMap<>();
    }//init

}//class