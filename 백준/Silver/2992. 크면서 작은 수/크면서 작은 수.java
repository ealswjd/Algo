import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2992
public class Main {
    private static final int MAX = 9999999;
    private static int X; // 정수 X
    private static int N; // 정수 X 길이
    private static int ans; // X보다 큰 수 중 가장 작은 수
    private static int[] numbers; // 정수 X의 숫자 구성


    public static void main(String[] args) throws IOException {
        init();

        // X와 구성이 같으면서 X보다 큰 수 중 가장 작은 수를 출력한다.
        sol(0, 0, 0, N-1);
        // 만약 그러한 숫자가 없는 경우에는 0을 출력
        ans = ans == MAX ? 0 : ans;

        System.out.print(ans);
    }//main


    private static void sol(int cnt, int visited, int result, int b) {
        if(cnt == N) {
            if(X < result && result < ans) {
                ans = result;
            }
            return;
        }

        for(int i=0; i<N; i++) {
            if((visited & 1 << i) != 0) continue;

            int next = (int) (Math.pow(10, b) * numbers[i] + result);
            sol(cnt+1, visited | 1 << i, next, b-1);
        }
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        X = Integer.parseInt(input); // 정수 X
        N = input.length(); // 정수 X 길이
        numbers = new int[N]; // 정수 X의 숫자 구성
        ans = MAX; // X보다 큰 수 중 가장 작은 수

        for(int i=0; i<N; i++) {
            numbers[i] = input.charAt(i) - '0';
        }

        br.close();
    }//init


}//class