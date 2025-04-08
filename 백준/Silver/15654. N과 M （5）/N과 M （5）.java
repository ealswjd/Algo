import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/15654
 N과 M (5)
 */
public class Main {
    private static int N, M; // 1부터 N까지 자연수 중에서 M개를 고른 수열
    private static int[] numbers; // N개의 수
    private static int[] result; // M개를 고른 수열
    private static StringBuilder ans;


    public static void main(String[] args) throws IOException {
        init();
        sol(0, 0);

        System.out.print(ans);
    }//main


    private static void sol(int cnt, int visited) {
        if(cnt == M) {
            append();
            return;
        }

        for(int i=0; i<N; i++) {
            if((visited & (1 << i)) != 0) continue;

            result[cnt] = numbers[i];
            sol(cnt+1, visited | 1 << i);
        }
    }//sol


    private static void append() {
        for(int n : result) {
            ans.append(n).append(' ');
        }

        ans.append('\n');
    }//append


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 자연수 개수
        M = Integer.parseInt(st.nextToken()); // 수열 길이

        numbers = new int[N]; // N개의 수
        result = new int[M]; // M개를 고른 수열
        ans = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(numbers);
        br.close();
    }//init


}//class