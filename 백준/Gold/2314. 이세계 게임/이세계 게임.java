import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/2314
public class Main {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static final int N = 4;
    private static final int TOTAL = 16;
    private static int origin;
    private static int target;


    public static void main(String[] args) throws IOException {
        init();

        int count = getCount();
        System.out.print(count);
    }//main


    private static int getCount() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[1 << TOTAL];

        q.offer(new int[] {origin, 0});
        visited[origin] = true;

        int[] cur;
        int status, move;
        int r, c, nr, nc;

        while(!q.isEmpty()) {
            cur = q.poll();
            status = cur[0]; // 현재 상태
            move = cur[1]; // 이동 횟수

            // 택희가 원하는 배치 완성
            if(status == target) return move;

            for(int i=0, m=1; i<TOTAL; i++, m<<=1) {
                if((status & m) == 0) continue;

                r = i / N;
                c = i % N;

                for(int d=0; d<4; d++) {
                    nr = r + DR[d];
                    nc = c + DC[d];

                    if(rangeCheck(nr, nc)) continue;

                    // 다음 칸으로 이동가능한지 확인
                    int nm = 1 << (nr * N + nc);
                    if((status & nm) != 0) continue;

                    // 다음 칸으로 이동
                    int next = (status ^ m) | nm;
                    if(visited[next]) continue;

                    visited[next] = true;
                    q.offer(new int[] {next, move + 1});
                }
            }
        }

        return -1;
    }//getCount


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] row;

        // 현재 주민들의 배치
        for(int i=0; i<N; i++) {
            row = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if(row[j] == 'P') origin |= 1 << (i * N + j);
            }
        }

        br.readLine();

        // 택희가 원하는 배치
        for(int i=0; i<N; i++) {
            row = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if(row[j] == 'P') target |= 1 << (i * N + j);
            }
        }

        br.close();
    }//init


}//class