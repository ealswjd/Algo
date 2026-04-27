import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5721
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        String input;

        while((input = br.readLine()) != null) {
            // 사탕 박스 행, 열
            StringTokenizer st = new StringTokenizer(input);
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 입력의 마지막 줄에는 0이 두 개 주어진다.
            if (R == 0 && C == 0) {
                break;
            }

            // 각 행에서 얻을 수 있는 최대 사탕 개수
            int[] rowMax = new int[R];

            // 각 행에서 얻을 수 있는 최대 사탕 개수 먼저 구하기
            for(int r=0; r<R; r++) {
                st = new StringTokenizer(br.readLine());
                int[] row = new int[C];
                for(int c=0; c<C; c++) {
                    row[c] = Integer.parseInt(st.nextToken());
                }

                rowMax[r] = getMax(row, C);
            }

            // 구해진 각 행의 최댓값들을 기준으로 세로 탐색
            int max = getMax(rowMax, R);

            // 각 테스트 케이스에 대해 집을 수 있는 사탕의 최대 개수를 출력
            ans.append(max).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getMax(int[] candy, int size) {
        if (size == 1) return candy[0];

        int[] dp = new int[size];

        dp[0] = candy[0];
        dp[1] = Math.max(candy[0], candy[1]);

        for(int cur=2; cur<size; cur++) {
            // 현재 사탕을 고르지 않는 경우, 현재 사탕을 고르는 경우
            dp[cur] = Math.max(dp[cur-1], dp[cur-2] + candy[cur]);
        }

        return dp[size - 1];
    }//getMax

}//class
/*

5 5
1 8 2 1 9
1 7 3 5 2
1 2 10 3 10
8 4 7 9 1
7 1 3 1 6
4 4
10 1 1 10
1 1 1 1
1 1 1 1
10 1 1 10
2 4
9 10 2 7
5 1 1 5
0 0
----------
54
40
17

 */