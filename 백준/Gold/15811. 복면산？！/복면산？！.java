import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15811
public class Main {
    private static final int MAX_N = 10;
    private static int N; // 사용된 알파벳 개수
    private static boolean isPossible; // 정답 존재 여부
    private static boolean[] used; // 0~9 숫자 사용 여부
    private static long[] numbers; // 알파벳 사용 정보

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        if (N > MAX_N) {
            System.out.print("NO");
            return;
        }

        dfs(0, 0L);

        System.out.print(isPossible ? "YES" : "NO");
    }//sol

    private static void dfs(int cur, long result) {
        if (isPossible) return;
        if (cur == N) {
            if (result == 0) {
                isPossible = true;
            }
            
            return;
        }

        for(int n=0; n<MAX_N; n++) {
            if (used[n]) continue;

            used[n] = true; // 사용 처리
            dfs(cur + 1, result + (numbers[cur] * n));
            used[n] = false; // 복구
        }
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] a = st.nextToken().toCharArray(); // 첫 번째 단어
        char[] b = st.nextToken().toCharArray(); // 두 번째 단어
        char[] result = st.nextToken().toCharArray(); // a + b = result

        br.close();

        Map<Character, Long> map = new HashMap<>();
        setNum(1, a, map);
        setNum(1, b, map);
        setNum(-1, result, map);

        N = map.size(); // 사용한 알파벳 개수
        numbers = new long[N]; // 알파벳 사용 정보
        used = new boolean[MAX_N]; // 0~9 사용 여부

        int idx = 0;
        for(long val : map.values()) {
            numbers[idx++] = val;
        }
    }//init

    private static void setNum(int x, char[] word, Map<Character, Long> map) {
        long cur = 1; // 현재 자리 (1의 자리부터)
        int len = word.length; // 단어 길이

        for(int i=len-1; i>=0; i--) {
            char c = word[i];
            long prev = map.getOrDefault(c, 0L); // 이전 숫자
            long sum = prev + cur * x;

            map.put(c, sum);
            cur *= 10; // 다음 자리로
        }

    }//setNum

}//class