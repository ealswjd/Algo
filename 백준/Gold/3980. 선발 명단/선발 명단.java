import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3980
public class Main {
    private static final int N = 11;
    private static int max;
    private static int[][] powers;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        powers = new int[N][N];
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            init(br);
            getMax(0, 0, 0);
            ans.append(max).append('\n');
        }

        System.out.print(ans);
    }//main


    private static void getMax(int player, int sum, int used) {
        if(player == N) {
            max = Math.max(max, sum);
            return;
        }

        for(int pos = 0; pos < N; pos++) {
            if((used & (1 << pos)) == 0 && powers[player][pos] > 0) {
                getMax(player + 1, sum + powers[player][pos], used | (1 << pos));
            }
        }

    }//getMax


    private static void init(BufferedReader br) throws IOException {
        max = 0;
        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                powers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }//init


}//class