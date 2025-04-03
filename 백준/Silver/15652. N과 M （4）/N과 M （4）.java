import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/15652
 N과 M (4)
 */
public class Main {
    private static int N, M; // 1부터 N까지 자연수 중에서 M개를 고른 수열
    private static int[] numbers; // M개를 고른 수열
    private static StringBuilder ans;


    public static void main(String[] args) throws IOException {
        init();
        dfs(1, 0);

        System.out.print(ans);
    }//main


    private static void dfs(int cur, int cnt) {
        if(cnt == M) {
            append();
            return;
        }

        for(int num=1; num<=N; num++) {
            if(num < cur) continue;
            
            numbers[cnt] = num;
            dfs(num, cnt+1);
        }
    }//dfs


    private static void append() {
        for(int number : numbers) {
            ans.append(number).append(' ');
        }
        ans.append('\n');
    }//append


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 자연수 개수
        M = Integer.parseInt(st.nextToken()); // 수열 길이

        numbers = new int[M]; // M개를 고른 수열
        ans = new StringBuilder();

        br.close();
    }//init


}//class