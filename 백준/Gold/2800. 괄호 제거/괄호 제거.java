import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2800
public class Main {
    private static int total; // 괄호 개수
    private static int N; // 문자열 길이
    private static String str;
    private static List<int[]> positions;
    private static Set<String> result;


    public static void main(String[] args) throws IOException {
        init();
        comb(0, 0, new boolean[N], str.toCharArray());
        print();
    }//main


    private static void comb(int idx, int cnt, boolean[] checked, char[] tmp) {
        if(idx == total) {
            add(tmp, cnt, checked);
            return;
        }

        int open = positions.get(idx)[0];
        int close = positions.get(idx)[1];

        checked[open] = true;
        checked[close] = true;
        comb(idx+1, cnt + 1, checked, tmp);
        checked[open] = false;
        checked[close] = false;
        comb(idx+1, cnt, checked, tmp);
    }//comb


    private static void add(char[] tmp, int cnt, boolean[] checked) {
        if(cnt == 0) return;

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            if(checked[i]) continue;

            sb.append(tmp[i]);
        }

        result.add(sb.toString());
    }//add


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(String s : result) {
            ans.append(s).append('\n');
        }

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        br.close();

        N = str.length();
        positions = new ArrayList<>();
        result = new TreeSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++) {
            char c = str.charAt(i);

            if(c == '(') {
                stack.add(i);
            }
            else if(c == ')') {
                int open = stack.pop();
                positions.add(new int[] {open, i});
            }
        }

        total = positions.size();
    }//init


}//class