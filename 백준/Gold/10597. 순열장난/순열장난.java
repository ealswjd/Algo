import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/10597
public class Main {
    private static final int MAX = 50;
    private static int len;
    private static int[] numbers;
    private static boolean[] used;
    private static boolean found;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        List<Integer> result = new ArrayList<>();

        dfs(0, 0, result);

        for(int num : result) {
            ans.append(num).append(' ');
        }

        System.out.print(ans);
    }//sol

    private static void dfs(int idx, int max, List<Integer> result) {
        if (found) return;
        if (idx == len) {
            if (result.size() == max) {
                found = true;
            }
            return;
        }

        // 1자리
        int one = numbers[idx];
        if (one > 0 && !used[one]) {
            used[one] = true;
            result.add(one);
            int nMax = Math.max(max, one);

            dfs(idx + 1, nMax, result);
            if (found) return;

            used[one] = false;
            result.remove(result.size() - 1);
        }

        // 2자리
        if (idx + 1 < len) {
            int two = numbers[idx] * 10 + numbers[idx+1];
            if (numbers[idx] > 0 && two > 0 && two <= MAX && !used[two]) {
                used[two] = true;
                result.add(two);
                int nMax = Math.max(max, two);

                dfs(idx + 2, nMax, result);
                if (found) return;

                used[two] = false;
                result.remove(result.size() - 1);
            }
        }
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strNumber = br.readLine();
        len = strNumber.length();

        numbers = new int[len];
        used = new boolean[MAX+1];

        for(int i=0; i<len; i++) {
            numbers[i] = strNumber.charAt(i) - '0';
        }

        br.close();
    }//init

}//class