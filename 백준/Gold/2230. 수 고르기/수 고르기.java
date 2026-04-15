import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2230
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N, M; // 숫자의 개수, 두 수의 차이 최소 기준
    private static int[] numbers; // 수열

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        Arrays.sort(numbers);

        int s = 0;
        int e = 0;
        int min = MAX;

        while(e < N) {
            int diff = numbers[e] - numbers[s];

            if (M <= diff) {
                min = Math.min(min, diff);
                s++;

                if (diff == M) break;
            } else {
                e++;
            }
        }

        System.out.print(min);
    }//solve

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 숫자의 개수
        M = Integer.parseInt(st.nextToken()); // 두 수의 차이 최소 기준

        numbers = new int[N]; // 수열

        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init

}//class