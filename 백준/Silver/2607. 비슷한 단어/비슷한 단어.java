import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2607
public class Main {
    private static final int C = 26;
    private static int N; // 단어의 개수
    private static int[] length; // 단어의 길이
    private static int[][] words; // 단어


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[] firstWord = words[0]; // 첫 번째 단어
        int firstWordLen = length[0]; // 첫 번째 단어 길이
        int cnt = 0; // 첫 번째 단어와 비슷한 단어의 개수

        for(int n=1; n<N; n++) {
            int diff = 0;
            // 같은 구성이거나 하나의 문자를 더하기, 빼기, 바꿔서 같은 구성
            for(int c=0; c<C; c++) {
                if(firstWord[c] != words[n][c]) {
                    diff += Math.abs(firstWord[c] - words[n][c]);
                }
            }

            if(firstWordLen == length[n]) {
                // 단어 길이가 같음
                if(diff <= 2) cnt++;
            } else {
                // 단어 길이가 다름
                if(diff <= 1) cnt++;
            }
        }

        System.out.print(cnt);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 단어의 개수

        words = new int[N][C]; // 단어
        length = new int[N]; // 단어 길이

        for(int i=0; i<N; i++) {
            char[] word = br.readLine().toCharArray();
            length[i] = word.length;

            for(char c : word) {
                words[i][c-'A']++;
            }
        }

        br.close();
    }//init


}//class