import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14428
public class Main {
    private static final int MAX = 1_000_000_004;
    private static int[] numbers; // 수열
    private static int[] minSeg; // 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 크기

        numbers = new int[N+1]; // 수열
        minSeg = new int[N*4]; // 최솟값

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        numbers[0] = MAX;
        build(1, 1, N);

        int M = Integer.parseInt(br.readLine()); // 쿼리의 개수
        StringBuilder ans = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (q == 1) { // a번째 수를 b로 변경
                numbers[a] = b;
                update(1, 1, N, a);
            } else { // a ~ b번 중 크기가 가장 작은 값의 인덱스 구하기
                int minIdx = getMin(1, 1, N, a, b);
                ans.append(minIdx).append('\n');
            }
        }

        br.close();
        System.out.print(ans);
    }//main

    private static void update(int cur, int start, int end, int idx) {
        if (start == end) {
            minSeg[cur] = idx;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid) update(cur * 2, start, mid, idx);
        else update(cur * 2 + 1, mid + 1, end, idx);

        int l = minSeg[cur*2], r = minSeg[cur*2+1];
        if (numbers[l] <= numbers[r]) minSeg[cur] = l;
        else minSeg[cur] = r;
    }//update

    private static int getMin(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) {
            return 0;
        }
        if (from <= start && end <= to) {
            return minSeg[cur];
        }

        int mid = (start + end) / 2;
        int l = getMin(cur * 2, start, mid, from, to);
        int r = getMin(cur * 2 + 1, mid + 1, end, from, to);

        if (numbers[l] <= numbers[r]) return l;
        else return r;
    }//getMin

    private static void build(int cur, int start, int end) {
        if (start == end) {
            minSeg[cur] = start;
            return;
        }

        int mid = (start + end) / 2;

        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);

        int l = minSeg[cur*2], r = minSeg[cur*2+1];

        if (numbers[l] <= numbers[r]) {
            minSeg[cur] = l;
        } else {
            minSeg[cur] = r;
        }
    }//build

}//class