import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9082
public class Main {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];

            char[] tmp = br.readLine().toCharArray();
            for(int i=0; i<N; i++) {
                numbers[i] = tmp[i] - '0';
            }

            br.readLine();

            int cnt = getCnt();
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main

    
    private static int getCnt() {
        int cnt = 0;
        int start = N % 3 == 0 ? 1 : 0;

        for(int i=start; i<N; i+=3) {
            cnt += numbers[i];
        }

        return cnt;
    }//getCnt

}//class