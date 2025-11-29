import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2268
public class Main {
    private static long[] segSum; // 합


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N과 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 명령 횟수

        int[] numbers = new int[N+1]; // 수
        segSum = new long[N*4]; // 수들의 합

        StringBuilder ans = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 0:합 출력, 1:변경 
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(q == 0) { // 0 : numbers[a] ~ numbers[b] 더한 값 출력
                int from = Math.min(a, b);
                int to = Math.max(a, b);
                long sum = getSum(1, 1, N, from, to);

                ans.append(sum).append('\n');
            } else { // 1 : numbers[a] = b 로 변경
                long dist = -1 * (numbers[a] - b);

                numbers[a] = b;
                update(1, 1, N, a, dist);
            }
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static long getSum(int cur, int start, int end, int from, int to) {
        if(to < start || end < from) {
            return 0;
        }
        if(from <= start && end <= to) {
            return segSum[cur];
        }

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, from, to) +
                getSum(cur * 2 + 1, mid + 1, end, from, to);
    }//getSum


    private static void update(int cur, int start, int end, int idx, long dist) {
        if(idx < start || end < idx) return;

        segSum[cur] += dist;

        if(start != end) {
            int mid = (start + end) / 2;

            update(cur * 2, start, mid, idx, dist);
            update(cur * 2 + 1, mid + 1, end, idx, dist);
        }
    }//update


}//class