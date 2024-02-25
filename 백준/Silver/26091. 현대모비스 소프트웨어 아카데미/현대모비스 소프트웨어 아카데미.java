import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26091
public class Main {
    static int N, M;
    static int[] power;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        power = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }//for
        br.close();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    private static int getCnt() {
        if(N < 2) return 0;

        Arrays.sort(power);
        int cnt = 0;
        int start = 0;
        int end = N-1;
        int sum;

        while(start < end) {
            sum = power[start] + power[end];

            if(sum < M) start++;
            else {
                cnt++;
                start++;
                end--;
            }

        }//while

        return cnt;
    }//getCnt

}//class