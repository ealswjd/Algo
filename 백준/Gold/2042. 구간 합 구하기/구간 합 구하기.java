import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2042
public class Main {
    private static int N; // 수의 개수
    private static long[] numbers; // N개의 숫자들
    private static long[] rangeSumTree; // 구간합 트리


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        numbers = new long[N+1]; // N개의 수
        rangeSumTree = new long[N*4]; // 구간합 트리

        for(int i=1; i<=N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        buildTree(1, 1, N); // 트리 채우기

        StringBuilder ans = new StringBuilder();
        int Q = M + K;
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 1:변경, 2:구간합
            int a = Integer.parseInt(st.nextToken()); // 인덱스

            if(q == 1) { // numbers[a]-> n 변경
                long n = Long.parseLong(st.nextToken());
                long diff = -1 * (numbers[a] - n); // 기존숫자와의 차
                // 변경
                numbers[a] = n;
                updateTree(1, 1, N, a, diff);
            } else { // [a ~ b] 구간합
                int b = Integer.parseInt(st.nextToken());
                long sum = getSum(1, 1, N, a, b);

                ans.append(sum).append("\n");
            }
        }

        br.close();
        System.out.print(ans);
    }//sol


    // idx번째 수 변경사항 트리에 적용
    private static void updateTree(int cur, int start, int end, int idx, long diff) {
        if(idx < start || end < idx) {
            return;
        }

        rangeSumTree[cur] += diff;

        if(start != end) {
            int mid = (start + end) / 2;

            updateTree(cur * 2, start, mid, idx, diff);
            updateTree(cur * 2 + 1, mid + 1, end, idx, diff);
        }
    }//updateTree


    // a부터 b까지의 구간합 구하기
    private static long getSum(int cur, int start, int end, int a, int b) {
        if(b < start || end < a) {
            return 0;
        }
        if(a <= start && end <= b) {
            return rangeSumTree[cur];
        }

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, a, b)
                + getSum(cur * 2 + 1, mid + 1, end, a, b);
    }//getSum


    private static void buildTree(int cur, int start, int end) {
        if(start == end) { // leaf node
            rangeSumTree[cur] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;

        buildTree(cur * 2, start, mid);
        buildTree(cur * 2 + 1, mid + 1, end);

        rangeSumTree[cur] = rangeSumTree[cur*2] + rangeSumTree[cur*2+1];
    }//buildTree
    

}//class