import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2529
public class Main {
    private static final long MAX = 9876543210L;
    private static final int NUM = 10;
    private static int K; // 부등호 문자의 개수
    private static int N; // 정수 개수
    private static long max, min; // 최대, 최소 정수
    private static char[] A; // 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열 A
    private static boolean[] used; // 숫자 사용 체크


    public static void main(String[] args) throws IOException {
        init();
        sol(0, 0, 0, K);

        /*
           최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력해야 한다.
           첫 자리가 0인 경우도 정수에 포함되어야 한다.
         */
        System.out.printf("%d\n%0"+ N + "d", max, min);
    }//main


    private static void sol(int prev, int cnt, long result, int b) {
        if(cnt == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);

            return;
        }

        for(int n=0; n<NUM; n++) {
            if(used[n]) continue;

            if (cnt != 0) {
                if (A[cnt - 1] == '<' && prev > n) continue;
                if (A[cnt - 1] == '>' && prev < n) continue;
            }

            used[n] = true;
            sol(n, cnt + 1, result + ((long) (int) Math.pow(10, b) * n), b-1);
            used[n] = false;
        }
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); // 부등호 문자의 개수
        N = K + 1;  // 정수 개수

        A = new char[K]; // 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열 A
        used = new boolean[NUM]; // 숫자 사용 체크

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            A[i] = st.nextToken().charAt(0);
        }

        min = MAX;

        br.close();
    }//init


}//class