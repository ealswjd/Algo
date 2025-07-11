import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/13022
public class Main {
    private static final int N = 4, W = 0, O = 1, L = 2, F = 3;
    private static int len;
    private static int[] word;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        // w로 시작 안 하면 올바르지 않은 단어.
        if(word[0] != 0) {
            System.out.print(0);
            return;
        }

        boolean isWolf = true; // 올바른 단어
        int[] cnt = new int[N]; // w, o, l, f가 나온 횟수
        int cur;

        cnt[0] = 1;

        for(int i=1; i<len; i++) {
            cur = word[i];
            cnt[cur]++;

            // 순서 맞는지 확인
            if(word[i-1] > cur && word[i-1] != F) {
                isWolf = false;
                break;
            }

            // 올바른 단어인지 확인
            if(!isAvailable(cur, cnt)) {
                isWolf = false;
                break;
            }
        }

        // w, o, l, f가 동일한 횟수로 나왔나 확인
        for(int i=O; i<=F; i++) {
            if(cnt[i-1] != cnt[i]) {
                isWolf = false;
                break;
            }
        }

        System.out.print(isWolf ? 1 : 0);
    }//sol


    private static boolean isAvailable(int cur, int[] cnt) {
        if(cur - 1 >= 0 && cnt[cur - 1] < cnt[cur]) return false;
        if(cur == W) return cnt[F] <= cnt[cur];

        for(int i=O; i<cur; i++) {
            if(cnt[i - 1] != cnt[i]) return false;
        }

        return true;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray(); // 입력으로 주어진 단어
        len = input.length;
        word = new int[len];

        for(int i=0; i<len; i++) {
            if(input[i] == 'o') word[i] = 1;
            else if(input[i] == 'l') word[i] = 2;
            else if(input[i] == 'f') word[i] = 3;
        }

        br.close();
    }//init

    
}//class