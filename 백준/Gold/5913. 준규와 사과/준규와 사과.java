import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5913
public class Main {
    static final int N=5, J=0, H=1;
    static int K, M;
    static boolean[][] visited;
    static int[][] dr = {{1, 1, 1, 1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0}
                        , {1, -1, 0, 0, 1, -1, 0, 0, 1, -1, 0, 0, 1, -1, 0, 0}};
    static int[][] dc = {{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, -1, -1, -1, -1}
                        , {0, 0, -1, 1, 0, 0, -1, 1, 0, 0, -1, 1, 0, 0, -1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        StringTokenizer st;
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            visited[r][c] = true;
        }
        br.close();

        visited[0][0] = true;
        visited[N-1][N-1] = true;

        M = (24-K)/2;
        int cnt = getCnt(0, 0, 4, 4, 0);
        System.out.print(cnt);
    }//main

    private static int getCnt(int jr, int jc, int hr, int hc, int moveCnt) {
        if(jr==hr && jc==hc) {
            if(moveCnt == M) return 1;
            else return 0;
        }

        int cnt = 0;
        int njr, njc, nhr, nhc;

        for(int i=0; i<16; i++) {
            njr = jr + dr[J][i];
            njc = jc + dc[J][i];
            nhr = hr + dr[H][i];
            nhc = hc + dc[H][i];
            if(!checkAvailability(njr, njc, nhr, nhc)) continue;

            visited[njr][njc] = true;
            visited[nhr][nhc] = true;
            cnt += getCnt(njr, njc, nhr, nhc, moveCnt+1);
            visited[njr][njc] = false;
            visited[nhr][nhc] = false;
        }

        return cnt;
    }//getCnt

    private static boolean checkAvailability(int jr, int jc, int hr, int hc) {
        if(jr >= N || jr < 0 || hr >= N || hr < 0 
           || jc >= N || jc < 0 || hc >= N || hc < 0) return false;
        return !visited[jr][jc] && !visited[hr][hc];
    }

}//class