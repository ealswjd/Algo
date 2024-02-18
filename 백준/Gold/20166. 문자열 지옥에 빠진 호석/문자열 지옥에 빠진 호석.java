import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20166
public class Main {
    static int N, M, K, len;
    static char[][] map;
    static int[][][] dp;
    static HashMap<String, Integer> cntMap;
    static int[] dr = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 신이 좋아하는 문자열의 개수
        map = new char[N][M];
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }//for

        cntMap = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        String target;
        int cnt;
        while(K-->0) {
            target = br.readLine();

            if(cntMap.containsKey(target)) {
                ans.append(cntMap.get(target)).append('\n');
                continue;
            }//if

            cnt = 0;
            len = target.length();
            init(len);

            for(int r=0; r<N; r++) {
                for(int c=0; c<M; c++) {
                    if(map[r][c] == target.charAt(0)) {
                        cnt += move(r, c, 1, target);
                    }
                }//for c
            }//for r

            cntMap.put(target, cnt);
            ans.append(cnt).append('\n');
        }//while

        System.out.print(ans);
    }//main

    private static int move(int r, int c, int cur, String target) {
        if(cur == len) return 1;
        if(dp[r][c][cur] != -1) return dp[r][c][cur];

        dp[r][c][cur] = 0;
        for(int i=0; i<8; i++) {
            int nr = getNext(r + dr[i], N);
            int nc = getNext(c + dc[i], M);

            if(map[nr][nc] == target.charAt(cur)) {
                dp[r][c][cur] += move(nr, nc, cur+1, target);
            }
        }//for

        return dp[r][c][cur];
    }//move

    private static int getNext(int next, int max) {
        if(next < 0) return max-1;
        else if(next >= max) return 0;
        else return next;
    }//getNext

    private static void init(int len) {
        dp = new int[N][M][len];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }//init

}//class