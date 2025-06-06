import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11561
public class Main {
    private static long N;


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        long max;

        while(T-- > 0) {
            N = Long.parseLong(br.readLine());
            max = getMax();

            ans.append(max).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static long getMax() {
        long max = 0;
        long start = 0;
        long end = (long) Math.sqrt(2 * N) + 1;
        long mid, sum;

        while(start <= end) {
            mid = (start + end) / 2;
            sum = (mid * (mid + 1)) / 2;

            if(sum <= N) {
                start = mid + 1;
                max = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return max;
    }//getMax


}//class