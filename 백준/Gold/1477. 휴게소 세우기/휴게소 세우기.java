import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1477
public class Main {
    static int N, M, L;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 현재 휴게소의 개수 N, 더 지으려고 하는 휴게소의 개수 M, 고속도로의 길이 L
        N = tokenToInt(st);
        M = tokenToInt(st);
        L = tokenToInt(st);

        init();
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = tokenToInt(st); // 현재 휴게소의 위치
        }
        br.close();

        int min = getMin();
        System.out.print(min);
    }//main

    private static int getMin() {
        Arrays.sort(arr);

        int len = N+2;
        int start = 1;
        int end = L;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(getCnt(mid, len) > M) start = mid+1;
            else end = mid-1;
        }

        return start;
    }//getMin

    private static int getCnt(int mid, int len) {
        int cnt = 0;

        for(int i=1; i<len; i++) {
            cnt += (arr[i] - arr[i-1] - 1) / mid;
        }

        return cnt;
    }//getCnt

    private static void init() {
        arr = new int[N+2];
        arr[N+1] = L;
    }//init

    private static int tokenToInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }//tokenToInt

}//class