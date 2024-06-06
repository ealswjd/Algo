import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9082
public class Main {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            map = new int[2][N+2];

            char[] tmp = br.readLine().toCharArray();
            for(int i=1; i<=N; i++) {
                map[0][i] = tmp[i-1] - '0';
            }

            tmp = br.readLine().toCharArray();
            for(int i=1; i<=N; i++) {
                if(tmp[i-1] == '*') map[1][i] = 1;
            }

            int cnt = getCnt();
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main

    private static int getCnt() {
        int cnt = 0;

        for(int i=1; i<=N; i++) {
            if(map[0][i] > 0) { // 지뢰 있음
                if(map[1][i-1] + map[1][i] + map[1][i+1] < map[0][i]) map[1][i+1] = 1;
                if(map[1][i-1] + map[1][i] + map[1][i+1] < map[0][i]) map[1][i] = 1;
            }
        }

        for(int i : map[1]) {
            cnt += i;
        }
        return cnt;
    }//getCnt

}//class