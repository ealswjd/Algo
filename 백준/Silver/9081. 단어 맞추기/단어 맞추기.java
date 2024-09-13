import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9081
public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            char[] word = br.readLine().toCharArray();
            N = word.length; // 단어 길이
            getNext(word);

            ans.append(String.valueOf(word)).append('\n');
        }

        System.out.print(ans);
    }//main


    // 다음 순열을 찾는 함수
    private static void getNext(char[] word) {
        // 뒤에서부터 탐색하여 처음으로 감소하는 지점을 찾음
        int i = N - 1;
        
        while (i > 0 && word[i - 1] >= word[i]) {
            i--;
        }

        // 만약 i가 0이라면 마지막 순열이므로 false
        if (i == 0) return;

        // 그 지점 바로 앞의 값보다 큰 값을 뒤에서부터 찾아 교환
        int j = N - 1;
        while (word[j] <= word[i - 1]) {
            j--;
        }

        // 교환
        swap(word, i - 1, j);

        // i부터 끝까지를 오름차순으로 정렬
        reverse(word, i, N - 1);
    }//getNext


    // 두 문자 교환
    private static void swap(char[] word, int i, int j) {
        char temp = word[i];
        word[i] = word[j];
        word[j] = temp;
    }//swap


    // 배열 뒤집기
    private static void reverse(char[] word, int start, int end) {
        while (start < end) {
            swap(word, start, end);
            start++;
            end--;
        }
    }//reverse


}//class