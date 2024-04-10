import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14722
public class Main {
    private static int N;
    private static int[][] map;
    private static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                dp[i][j][2] = -1;
            }
        }
        br.close();

        int max = move(0, 0, 0);
        System.out.print(max);
    }//main

    private static int move(int r, int c, int target) {
        if(r == N-1 && c == N-1) {
            if(target == map[r][c]) return 1;
            return 0;
        }
        if(dp[r][c][target] != -1) return dp[r][c][target];

        if(target == map[r][c]) {
            int next = (target+1) % 3;
            if(rangeCheck(r+1, c+1)) {
                dp[r][c][target] = Math.max(Math.max(move(r+1, c, next), move(r, c+1, next))+1,
                                    Math.max(move(r+1, c, target), move(r, c+1, target)));
            } else if(rangeCheck(r+1, c)) {
                dp[r][c][target] = Math.max(move(r+1, c, target), Math.max(move(r+1, c, next),
                                            move(r+1, c, next))+1);
            } else if(rangeCheck(r, c+1)) {
                dp[r][c][target] = Math.max(move(r, c+1, target), Math.max(move(r, c+1, next),
                        move(r, c+1, next))+1);
            }
        }else {
            if(rangeCheck(r+1, c+1)) {
                dp[r][c][target] = Math.max(move(r+1, c, target), move(r, c+1, target));
            } else if(rangeCheck(r+1, c)) {
                dp[r][c][target] = move(r+1, c, target);
            } else if(rangeCheck(r, c+1)) {
                dp[r][c][target] = move(r, c+1, target);
            }
        }

        return dp[r][c][target];
    }//move

    
    private static boolean rangeCheck(int r, int c) {
        return r < N && c < N;
    }//rangeCheck

    
    private static void init() {
        map = new int[N][N];
        dp = new int[N][N][3];
    }//init

}//class