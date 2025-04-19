import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28069
public class Main {
    private static int N, K; // 계단 개수, 계단을 오르는 횟수


    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }//main


    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        int num = 0; // 현재 민희는 0번째 계단에 있습니다
        int cnt = 0;

        q.offer(new int[] {num, cnt}); // 현재 계단 칸, 행동 횟수
        visited[num] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            num = cur[0];
            cnt = cur[1];

            if(num == N) {
                if(cnt <= K) {
                    System.out.print("minigimbob");
                    return;
                }
                continue;
            }

            // 계단 한 칸을 올라갑니다.
            int next = num + 1;
            int nCnt = cnt + 1;
            if(!visited[next]) {
                visited[next] = true;
                q.offer(new int[] {next, nCnt});
            }

            // i + i/2 번째 계단으로 순간이동합니다.
            next = num + num / 2;
            if(next <= N && !visited[next]) {
                visited[next] = true;
                q.offer(new int[] {next, nCnt});
            }
        }

        System.out.print("water");
    }//bfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 계단 개수
        K = Integer.parseInt(st.nextToken()); // 계단을 오르는 횟수

        br.close();
    }//init


}//class