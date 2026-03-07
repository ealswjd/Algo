import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10868
public class Main {
    private static final int MAX = 1_000_000_004;
    private static int leaf; // 리프 노드 개수
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정수의 개수
        int M = Integer.parseInt(st.nextToken()); // 질문 개수

        leaf = 1;
        while(leaf < N) {
            leaf <<= 1;
        }
        tree = new int[leaf * 2]; // 최솟값
        Arrays.fill(tree, MAX);

        // 리프 노드 위치에 입력
        for(int i=0; i<N; i++) {
            tree[leaf+i] = Integer.parseInt(br.readLine());
        }

        for(int i=leaf-1; i>0; i--) {
            // 자식 노드 (1 * 2)와 (i * 2 + 1) 중 더 작은 값을 부모 노드에 저장
            tree[i] = Math.min(tree[i << 1], tree[(i << 1) | 1]);
        }

        StringBuilder ans = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = getMin(a, b);
            ans.append(min).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getMin(int left, int right) {
        left += leaf - 1;
        right += leaf - 1;
        int min = MAX;

        while(left <= right) {
            // 왼쪽 노드가 오른쪽 자식(홀수)이면 비교, 인덱스 이동
            if ((left & 1) == 1) min = Math.min(min, tree[left++]);
            // 오른쪽 노드가 왼쪽 자식(짝수)이면 비교, 인덱스 이동
            if ((right & 1) == 0) min = Math.min(min, tree[right--]);

            // 부모 노드로 이동
            left >>= 1;
            right >>= 1;
        }

        return min;
    }//getMin

}//class