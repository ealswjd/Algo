import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10836
public class Main {
    static int N, DAY;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        DAY = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        while(DAY-->0) {
            st = new StringTokenizer(br.readLine());

            // 제일 왼쪽 열과, 제일 위쪽 행의 애벌레들은 자신이 자라는 정도를 스스로 결정
            sol(st, N-1, 0);
        }

        print();
    }//main

    private static void sol(StringTokenizer st, int r, int c) {
        int cnt;

        for(int i=0; i<3; i++) {
            cnt = Integer.parseInt(st.nextToken());
            while(cnt-->0) {
                map[r][c] += i;

                if(r-1 < 0) c++;
                else r--;
            }
        }
    }//sol


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(r!=0 && c!=0) map[r][c] += map[r-1][c];
                ans.append(map[r][c] + 1).append(' ');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//print

}//class