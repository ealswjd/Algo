import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/14677
public class Main {
    private static int N; // 약 개수
    private static int[] medicine; // 약의 상태


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] checked = new int[N][N];
        Queue<int[]> q = new LinkedList<>();
        int max = 0; // 약의 최대 개수

        q.offer(new int[] {0, N-1, 0}); // 맨 앞, 맨 뒤, 먹은 약 개수

        int[] cur;
        int first, last, cnt;
        while(!q.isEmpty()) {
            cur = q.poll();
            first = cur[0];
            last = cur[1];
            cnt = cur[2];

            // 맨 앞의 약 먹기
            if(medicine[first] == cnt % 3) {
                max = Math.max(max, cnt + 1);
                if(first + 1 > last) break;

                if(checked[first+1][last] < cnt + 1) {
                    checked[first+1][last] = cnt + 1;
                    q.offer(new int[] {first + 1, last, cnt + 1});
                }
            }

            // 맨 뒤의 약 먹기
            if(medicine[last] == cnt % 3) {
                max = Math.max(max, cnt + 1);
                if(last - 1 < first) break;

                if(checked[first][last-1] < cnt + 1) {
                    checked[first][last-1] = cnt + 1;
                    q.offer(new int[] {first, last - 1, cnt + 1});
                }
            }
        }

        System.out.print(max);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine()); // 약을 먹어야 하는 날짜
        N = D * 3; // 약 개수

        medicine = new int[N]; // 약의 상태
        char[] tmp = br.readLine().toCharArray();

        for(int i=0; i<N; i++) {
            if(tmp[i] == 'L') medicine[i] = 1;
            else if(tmp[i] == 'D') medicine[i] = 2;
        }

        br.close();
    }//init


}//class

/*
2
BBLDDL
----
6
 */