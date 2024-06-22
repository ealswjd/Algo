import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1351
public class Main {
    static long N;
    static int P, Q;
    static Map<Long, Long> dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        dp = new HashMap<>();

        long ans = getResult(N);
        System.out.print(ans);
    }//main

    private static long getResult(long n) {
        if(n == 0) return 1;
        if(dp.containsKey(n)) return dp.get(n);

        long tmp = getResult(n/P) + getResult(n/Q);
        dp.put(n, tmp);

        return tmp;
    }//getResult

}//class