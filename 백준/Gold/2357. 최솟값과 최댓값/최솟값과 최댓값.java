import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2357
public class Main {
    private static final int MIN = 0, MAX = 1_000_000_004;
    private static int[] numbers; // N개의 정수들
    private static int[] minSeg, maxSeg; // 최솟값, 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정수의 개수
        int M = Integer.parseInt(st.nextToken()); // 질문 개수

        numbers = new int[N + 1];
        minSeg = new int[N * 4];
        maxSeg = new int[N * 4];

        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        build(1, 1, N);

        StringBuilder ans = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 제일 큰 정수 찾기
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = getMin(1, 1, N, a, b);
            int max = getMax(1, 1, N, a, b);

            ans.append(min).append(' ').append(max).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getMin(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) {
            return MAX;
        }
        if (from <= start && end <= to) {
            return minSeg[cur];
        }

        int mid = (start + end) / 2;

        return Math.min(getMin(cur * 2, start, mid, from, to),
                getMin(cur * 2 + 1, mid + 1, end, from, to));
    }//getMin

    private static int getMax(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) {
            return MIN;
        }
        if (from <= start && end <= to) {
            return maxSeg[cur];
        }

        int mid = (start + end) / 2;

        return Math.max(getMax(cur * 2, start, mid, from, to),
                getMax(cur * 2 + 1, mid + 1, end, from, to));
    }//getMax

    private static void build(int cur, int start, int end) {
        if (start == end) {
            minSeg[cur] = numbers[start];
            maxSeg[cur] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;

        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);

        minSeg[cur] = Math.min(minSeg[cur * 2], minSeg[cur * 2 + 1]);
        maxSeg[cur] = Math.max(maxSeg[cur * 2], maxSeg[cur * 2 + 1]);
    }//build

}//class