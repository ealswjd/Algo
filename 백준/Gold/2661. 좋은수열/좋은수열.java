import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2661
public class Main {
    private static int N; // 수열의 길이
    private static int[] arr; // 수열

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        dfs(0); // 좋은 수열 찾기

        // 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서
        // 가장 작은 수를 나타내는 수열만 출력
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<N; i++) {
            ans.append(arr[i]);
        }

        System.out.print(ans);
    }//sol

    private static boolean dfs(int cnt) {
        if (cnt == N) {
            return true;
        }

        boolean find = false;
        for(int n=1; n<=3; n++) {
            if (cnt > 0 && n == arr[cnt-1]) continue;
            if (cnt > 2 && !isGood(cnt, n)) continue;

            arr[cnt] = n;
            find = dfs(cnt+1);

            if (find) break;
        }

        return find;
    }//dfs

    private static boolean isGood(int cur, int n) {
        int half = cur / 2;
        int cnt = cur - half;
        arr[cur] = n;

        int sameCnt;
        for(int target=2; target<=cnt; target++) {
            sameCnt = 0;
            for(int i=cur; i>cur-target; i--) {
                if (arr[i] == arr[i-target]) {
                    sameCnt++;
                }
            }
            if (sameCnt == target) return false;
        }

        return true;
    }//isGood

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        br.close();
    }//init

}//class