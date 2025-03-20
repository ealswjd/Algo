import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/17255
public class Main {
    private static int len; // N의 길이
    private static char[] N; // 만들고자 하는 수
    private static Set<String> checked; // N을 만드는 방법의 수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        for(int i = 0; i<len; i++){
            dfs(i, i, String.valueOf(N[i]), String.valueOf(N[i]));
        }

        System.out.print(checked.size());
    }//sol


    private static void dfs(int left, int right, String cur, String path) {
        if(left == 0 && right == len - 1) {
            checked.add(path);
            return;
        }

        if(left - 1 >= 0) {
            String next = N[left - 1] + cur;
            dfs(left - 1, right, next, path + next);
        }
        if(right + 1 < len) {
            String next = cur + N[right + 1];
            dfs(left, right + 1, next, path + next);
        }
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = input.toCharArray(); // 만들고자 하는 수
        len = N.length; // N의 길이
        checked = new HashSet<>(); // N을 만드는 방법의 수

        br.close();
    }//main


}//class