import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18436
public class Main {
    private static final int EVEN = 0, ODD = 1;
    private static int N; // 수열의 크기
    private static int[] numbers; // 수열
    private static int[][] seg;


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수열의 크기 N (1 ≤ N ≤ 100,000)이 주어진다.
        N = Integer.parseInt(br.readLine());

        numbers = new int[N+1]; // 수열
        seg = new int[N*4][2]; // 짝수, 홀수의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = num % 2 == 0 ? EVEN : ODD;
        }

        build(1, 1, N);

        // 쿼리의 개수 M (1 ≤ M ≤ 100,000)이 주어진다.
        int M = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        // 쿼리가 한 줄에 하나씩 주어진다.
        int cnt;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 1:변경, 2:짝수, 3:홀수
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(q == 1) { // 변경
                int change = b % 2 == 0 ? EVEN : ODD;
                if(numbers[a] != change) {
                    update(1, 1, N, a, change);
                    numbers[a] = change;
                }
                continue;
            } else if(q == 2) { // 짝수 개수
                cnt = getCnt(1, 1, N, a, b, EVEN);
            } else { // 홀수 개수
                cnt = getCnt(1, 1, N, a, b, ODD);
            }

            ans.append(cnt).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static void update(int cur, int start, int end, int a, int change) {
        if(a < start || end < a) return;

        seg[cur][change]++;
        seg[cur][change^1]--;

        if(start != end) {
            int mid = (start + end) / 2;

            update(cur * 2, start, mid, a, change);
            update(cur * 2 + 1, mid + 1, end, a, change);
        }
    }//update


    private static int getCnt(int cur, int start, int end, int from, int to, int idx) {
        if(to < start || end < from) {
            return 0;
        }
        if(from <= start && end <= to) {
            return seg[cur][idx];
        }

        int mid = (start + end) / 2;

        return getCnt(cur * 2, start, mid, from, to, idx) +
                getCnt(cur * 2 + 1, mid + 1, end, from, to, idx);
    }//getCnt


    private static void build(int cur, int start, int end) {
        if(start == end) {
            int idx = numbers[start] % 2 == 0 ? EVEN : ODD;
            seg[cur][idx] = 1;

            return;
        }

        int mid = (start + end) / 2;

        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);

        seg[cur][EVEN] = seg[cur*2][EVEN] + seg[cur*2+1][EVEN];
        seg[cur][ODD] = seg[cur*2][ODD] + seg[cur*2+1][ODD];
    }//build


}//class