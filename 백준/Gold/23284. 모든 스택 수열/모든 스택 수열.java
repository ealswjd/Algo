import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/23284
public class Main {
    private static int N, top;
    private static int[] stack, result;
    private static StringBuilder ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정수의 개수
        br.close();

        top = -1; // 스택 최상단 인덱스
        stack = new int[N];
        result = new int[N]; // 완성된 수열
        ans = new StringBuilder();

        // 1부터 시작
        dfs(1, 0);

        System.out.print(ans);
    }//main

    private static void dfs(int num, int cnt) {
        if (cnt == N) {
            for(int i=0; i<N; i++) {
                ans.append(result[i]).append(' ');
            }
            ans.append('\n');

            return;
        }

        // 사전순 출력을 위해 pop부터 시도
        // 꺼내기
        if (top >= 0) {
            int val = stack[top--];
            // stack에 있는 숫자 사용
            result[cnt] = val;
            dfs(num, cnt + 1);
            // 복구
            stack[++top] = val;
        }

        // 넣기
        if (num <= N) {
            // 스택에 현재 숫자 넣기
            stack[++top] = num;
            dfs(num + 1, cnt);
            // 복구
            top--;
        }
    }//dfs

}//class