import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/19940
public class Main {
    // 버튼을 눌러서 시간을 추가 or 감소 {ADDH, ADDT, MINT, ADDO, MINO}
    private static final int[] BUTTON = {60, 10, -10, 1, -1}; 
    private static final int H = 60;
    private static int[][] times; // 시간별 버튼 정보


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        bfs(); // 미리 0~60분에 대한 버튼 조합 계산

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine()); // 설정해야 하는 시간
            int h = N / H;      // 60분 단위 (ADDH 버튼 횟수)
            int remain = N % H; // 남은 시간

            int[] result = times[remain].clone();
            result[0] += h;

            for(int i=0; i<4; i++) {
                ans.append(result[i]).append(' ');
            }
            ans.append(result[4]).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        times = new int[H+1][]; // 0 ~ 60분까지 버튼 횟수 정보 저장

        q.offer(0);
        times[0] = new int[]{0, 0, 0, 0, 0};

        int cur;
        while(!q.isEmpty()) {
            cur = q.poll(); // 현재 시간

            for(int i=4; i>=0; i--) {
                int next = cur + BUTTON[i];

                // 범위 내 시간이고 방문하지 않은 경우만 처리
                if(rangeCheck(next) && times[next] == null) {
                    times[next] = times[cur].clone();
                    times[next][i]++;

                    q.offer(next);
                }
            }
        }

    }//bfs


    private static boolean rangeCheck(int t) {
        return t >= 0 && t <= H;
    }//rangeCheck


}//class