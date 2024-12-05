import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9997
public class Main {
    private static final int CNT = 26;
    private static final int COMPLETED = (1 << CNT) - 1; // 알파벳 모두 포함
    private static int N; // 단어 개수
    private static int alphabet; // 사전에 포함된 알파벳 개수
    private static int total; // 만들 수 있는 테스트 문장의 개수
    private static int[] words; // 단어 사전


    public static void main(String[] args) throws IOException {
        init();

        // 테스트 문장 만들 수 있음
        if(alphabet == CNT) {
            comb(N, 0);
        }

        System.out.print(total);
    }//main


    private static void comb(int idx, int result) {
        if(result == COMPLETED) { // 테스트 문장 완성
            total += 1 << idx;
            return;
        }
        if(--idx < 0) return;

        comb(idx, result);
        comb(idx, result | words[idx]);
    }//comb


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 단어 개수

        words = new int[N]; // 단어 사전
        int[] counts = new int[CNT]; // 단어 개수

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();

            for(char c : tmp) {
                int n = c - 'a';
                if(++counts[n] == 1) alphabet++;

                words[i] |= 1 << n;
            }
        }

        br.close();
    }//init


}//class