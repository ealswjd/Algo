import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15317
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        long[] C = new long[N];
        long[] S = new long[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            S[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(C);
        Arrays.sort(S);

        int left = 0, right = Math.min(N, M);
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canAllocate(mid, C, S, X)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canAllocate(int k, long[] C, long[] S, long X) {
        long totalSupport = 0;

        for (int i = 0; i < k; i++) {
            long cost = C[i];
            long budget = S[S.length - k + i];

            if (budget < cost) {
                totalSupport += cost - budget;
                if (totalSupport > X) {
                    return false;
                }
            }
        }

        return totalSupport <= X;
    }
    
}//class