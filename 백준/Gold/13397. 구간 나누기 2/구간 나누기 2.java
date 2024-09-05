import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13397
public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열의 크기
        M = Integer.parseInt(st.nextToken()); // M개 이하의 구간

        int max = 0;
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int min = getMin(max);
        System.out.print(min);
    }//main

    
    private static int getMin(int max) {
        int start = 0;
        int end = max;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(getCnt(mid) <= M) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getMin

    
    private static int getCnt(int target) {
        int cnt = 1;
        int min = arr[0];
        int max = arr[0];

        for(int i=0; i<N; i++) {
            min = Math.min(min, arr[i]); // 최솟값
            max = Math.max(max, arr[i]); // 최댓값

            if(max - min > target) {
                if(++cnt > M) return cnt;
                min = arr[i];
                max = arr[i];
            }
        }

        return cnt;
    }//getCnt

    
}//class