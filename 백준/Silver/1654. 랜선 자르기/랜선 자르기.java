import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1654
public class Main {
    static int N, K;
    static int[] lines; // 각 랜선의 길이

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
        K = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수

        lines = new int[N];
        int max = 0;
        for(int i=0; i<N; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lines[i]);
        }

        br.close();

        // K개를 만들 수 있는 랜선의 최대 길이
        long len = getMax(max);
        System.out.print(len);
    }//main

    
    private static long getMax(int max) {
        long len = 0;
        long start = 1;
        long end = max;
        long mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(getCnt(mid) >= K) {
                len = mid;
                start = mid + 1;
            }
            else end = mid - 1;
        }

        return len;
    }//getMax

    
    private static long getCnt(long len) {
        // len 만큼 잘라서 만들 수 있는 랜선 개수
        long cnt = 0;

        for(int line : lines) {
            cnt += line / len;
        }

        return cnt;
    }//getCnt

    
}//class