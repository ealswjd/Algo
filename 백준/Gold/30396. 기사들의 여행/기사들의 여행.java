import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 https://www.acmicpc.net/problem/30396
 나이트들을 옮겨 체스판 A에서 체스판 B로 만드는 데 필요한 나이트들의 최소 이동 횟수를 출력
 */
public class Main {
    private static final int[] DR = {-2, -2, -1, 1, 2, 2, 1, -1};
    private static final int[] DC = {-1, 1, 2, 2, 1, -1, -2, -2};
    private static final int N = 4;
    private static final int TOTAL = 16;
    private static int A; // 체스판 A
    private static int B; // 체스판 B


    public static void main(String[] args) throws IOException {
        init();

        int count = getCount();
        System.out.print(count);
    }//main


    private static int getCount() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[1 << TOTAL];
        int result = -1;

        q.offer(new int[] {A, 0}); // 체스판 상태, 이동 횟수
        visited[A] = true;

        int[] cur;
        int r, c, nr, nc;
        int status, count;

        while(!q.isEmpty()) {
            cur = q.poll();
            status = cur[0]; // 현재 상태
            count = cur[1]; // 이동 횟수

            // 체스판 A의 상태가 B와 같아짐
            if(status == B) {
                result = count;
                break;
            }

            for(int i = 0, k = 1; i<TOTAL; i++, k<<=1) {
                if((status & k) == 0) continue; // 나이트 없음

                // 현재 칸의 행, 열
                r = i / N;
                c = i % N;

                for(int d=0; d<8; d++) {
                    nr = r + DR[d];
                    nc = c + DC[d];

                    if(rangeCheck(nr, nc)) continue;

                    // 다음 칸으로 나이트 이동
                    int nk = 1 << (nr * N + nc);
                    if((status & nk) != 0) continue; // 이미 나이트 있음

                    // 현재 칸에서 나이트 제거 후 이동
                    int next = (status ^ k) | nk;
                    if(visited[next]) continue;

                    visited[next] = true;
                    q.offer(new int[] {next, count + 1});
                }
            }
        }

        return result;
    }//getCount


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tmp = 1;
        char[] row;

        // 체스판 A
        for(int i=0; i<N; i++) {
            row = br.readLine().toCharArray();
            for(int j=0; j<N; j++){
                if(row[j] == '1') A += tmp;
                tmp <<= 1;
            }
        }

        // 체스판 B
        tmp = 1;
        for(int i=0; i<N; i++) {
            row = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if(row[j] == '1') B += tmp;
                tmp <<= 1;
            }
        }

        br.close();
    }//init


}//class