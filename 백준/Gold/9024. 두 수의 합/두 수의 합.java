import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9024
public class Main {
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int cnt = getCnt(arr);
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main

    private static int getCnt(int[] arr) {
        Arrays.sort(arr);

        int cnt = 1;
        int best = (int)(2 * 10e8);
        int start = 0;
        int end = N-1;
        int sum, k;

        while(start < end) {
            sum = arr[start] + arr[end];
            k = Math.abs(K - sum);

            if(k == best) cnt++;
            else if(k < best) {
                best = k;
                cnt = 1;
            }

            if(sum == K) {
                start++;
                end--;
            }
            else if(sum < K) start++;
            else end--;
        }

        return cnt;
    }//getCnt

}//class