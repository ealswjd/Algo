import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6236
public class Main {
    private static int N, M; // N일 동안 정확히 M번만 통장에서 돈을 빼서 쓰기로
    private static int max, total;
    private static int[] money; // i번째 날에 이용할 금액


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int start = max;
        int end = total;
        int mid;
        int K = total; // 통장에서 인출해야 할 최소 금액 K

        while(start <= end) {
            mid = (start + end) / 2;

            if(isAvailable(mid)) {
                end = mid - 1;
                K = mid;
            }
            else {
                start = mid + 1;
            }
        }

        // 현우가 통장에서 인출해야 할 최소 금액 K를 출력한다.
        System.out.print(K);
    }//sol


    private static boolean isAvailable(int k) {
        int cnt = 1; // 인출 횟수
        int cur = k; // 현재 금액

        for(int i=0; i<N; i++) {
            // 모자라게 되면 다시 K원을 인출
            if(money[i] > cur) {
                if(cnt >= M) return false;

                cnt++;
                cur = k;
            }
            cur -= money[i];
        }

        return true;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N일 동안
        M = Integer.parseInt(st.nextToken()); // 정확히 M번만 통장에서 돈을 빼서 쓰기로

        money = new int[N]; // i번째 날에 이용할 금액

        for(int i=0; i<N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            total += money[i];
            max = Math.max(max, money[i]);
        }

        br.close();
    }//init


}//class