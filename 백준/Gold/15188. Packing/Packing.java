import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15188
public class Main {
    static int N, A, B;
    static int[] W, V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        for(int p=1; p<=P; p++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            init();

            inputArr(new StringTokenizer(br.readLine()), W);
            inputArr(new StringTokenizer(br.readLine()), V);

            int max = getMax();

            ans.append("Problem ").append(p).append(": ");
            ans.append(max).append('\n');
        }

        System.out.print(ans);
    }//main


    private static int getMax() {
        int max = 0;
        int[][] dp = new int[A+1][B+1];
        boolean[][] selected = new boolean[A+1][B+1];
        selected[0][0] = true;

        for(int i=0; i<N; i++) {
            for(int a=A; a>=0; a--) {
                for(int b=B; b>=0; b--) {
                    if(a - W[i] >= 0) {
                        selected[a][b] |= selected[a-W[i]][b];
                        if(selected[a][b]) {
                            dp[a][b] = Math.max(dp[a][b], dp[a-W[i]][b] + V[i]);
                        }
                    }
                    if(b - W[i] >= 0) {
                        selected[a][b] |= selected[a][b-W[i]];
                        if(selected[a][b]) {
                            dp[a][b] = Math.max(dp[a][b], dp[a][b-W[i]] + V[i]);
                        }
                    }
                }
            }
        }

        for(int a=0; a<=A; a++) {
            for(int b=0; b<=B; b++) {
                max = Math.max(max, dp[a][b]);
            }
        }

        return max;
    }//getMax
    
    private static void inputArr(StringTokenizer st, int[] arr) {

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

    }//inputArr

    private static void init() {
        W = new int[N];
        V = new int[N];
    }//init

}//class