import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2243
public class Main {
    // 각각의 사탕은 그 맛의 좋고 나쁨이 1부터 1,000,000까지의 정수로 구분
    private static final int N = 1_000_000;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int q = Integer.parseInt(br.readLine()); // 수정이가 사탕 상자에 손을 댄 횟수

        tree = new int[N * 4];
        StringTokenizer st;

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = -1;

            if (a == 1) { // b번 사탕을 꺼내는 경우
                b = getNum(1, 1, N, b); // 꺼낼 사탕의 맛
                ans.append(b).append('\n');
            } else { // b맛 사탕을 c개 넣는 경우
                c = Integer.parseInt(st.nextToken());
            }

            update(1, 1, N, b, c);
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getNum(int cur, int start, int end, int target) {
        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;
        int left = cur << 1;
        int right = left | 1;
        int leftCnt = tree[left];

        if (target <= leftCnt) {
            return getNum(left, start, mid, target);
        } else {
            return getNum(right, mid + 1, end, target - leftCnt);
        }
    }//getNum

    private static void update(int cur, int start, int end, int target, int cnt) {
        if (target < start || end < target) return;

        tree[cur] += cnt;

        if (start != end) {
            int mid = (start + end) >> 1;
            int next = (cur << 1);

            update(next, start, mid, target, cnt);
            update(next | 1, mid + 1, end, target, cnt);
        }
    }//update

}//class

/*

6
2 1 2
2 3 3
1 2
1 2
2 1 -1
1 2
---------
1
3
3

*/