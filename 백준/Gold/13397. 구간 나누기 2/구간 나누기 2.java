import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13397
public class Main {
    static final int INF = 987654321;
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int ans = getResult(max);
        System.out.print(ans);
    }//main

    private static int getResult(int max) {
        int min = 0;
        int mid;

        while(min < max) {
            mid = (min+max) / 2;
            if(getCnt(mid) <= M) max = mid;
            else min = mid+1;
        }

        return max;
    }//getMax

    private static int getCnt(int mid) {
        int cnt = 1;
        int max = -1;
        int min = INF;

        for(int i=0; i<N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if(max-min > mid) {
                cnt++;
                i--;
                min = INF;
                max = -1;
            }
        }

        return cnt;
    }//getCnt

}//class