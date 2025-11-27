import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14427
public class Main {
    private static int N; // 수열의 크기
    private static int[] numbers; // 수열
    private static Number[] minTree; // 최솟값 트리


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //수열의 크기 N이 주어진다. (1 ≤ N ≤ 100,000)
        N = Integer.parseInt(br.readLine());

        numbers = new int[N+1]; // 수열
        minTree = new Number[N*4]; // 최솟값 트리

        // A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 1, N); // 세그먼트 트리 생성

        // 쿼리의 개수 M이 주어진다. (1 ≤ M ≤ 100,000)
        int M = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        // M개의 줄에는 쿼리가 주어진다.
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 1:변경, 2:인덱스 출력

            if(q == 1) { // numbers[a]를 b로 바꾼다.
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                numbers[a] = b;
                update(1, 1, N, a, b);
            } else { // 크기가 가장 작은 값의 인덱스를 출력
                ans.append(minTree[1].idx).append('\n');
            }
        }

        // 쿼리에 대해서 정답을 한 줄에 하나씩 순서대로 출력
        System.out.print(ans);
    }//sol


    private static void update(int cur, int start, int end, int idx, int val) {
        if(start == end) {
            minTree[cur] = new Number(idx, val);
            return;
        }

        int mid = (start + end) / 2;

        if(idx <= mid) update(cur * 2, start, mid, idx, val);
        else update(cur * 2 + 1, mid + 1, end, idx, val);

        minTree[cur] = merge(minTree[cur*2], minTree[cur*2+1]);
    }//update


    private static void build(int cur, int start, int end) {
        if(start == end) { // 리프노드
            minTree[cur] = new Number(start, numbers[start]);
        } else {
            int mid = (start + end) / 2;

            build(cur * 2, start, mid);
            build(cur * 2 + 1, mid + 1, end);

            minTree[cur] = merge(minTree[cur*2], minTree[cur*2+1]);
        }
    }//build


    private static Number merge(Number a, Number b) {
        if(a.number < b.number) return a;
        if(a.number > b.number) return b;

        return a.idx < b.idx ? a : b;
    }//merge


    private static class Number {
        int idx; // 인덱스
        int number; // 수
        Number(int idx, int number) {
            this.idx = idx;
            this.number = number;
        }
    }//Number


}//class