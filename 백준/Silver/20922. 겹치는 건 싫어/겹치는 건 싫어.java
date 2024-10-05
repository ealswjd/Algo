import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20922
public class Main {
    static int N, K;
    static int[] numbers; // 수열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수열 길이
        K = Integer.parseInt(st.nextToken()); // 같은 원소를 포함할 수 있는 개수

        numbers = new int[N]; // 수열
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, numbers[i]);
        }

        br.close();

        int maxLen = getMaxLen(max);
        System.out.print(maxLen);
    }//main

    
    private static int getMaxLen(int max) {
        int[] count = new int[max+1];
        int len = 0;
        int start = 0;

        for(int end = 0; end < N; end++) {
            int cur = numbers[end];

            count[cur]++;

            while(count[cur] > K) {
                int prev = numbers[start];
                count[prev]--;
                start++;
            }

            len = Math.max(len, end - start + 1);
        }

        return len;
    }//getMaxLen

    
}//class