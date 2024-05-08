import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1477
public class Main {
    static int N, M, L;
    static int[] restAreas;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 휴게소의 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려고 하는 휴게소의 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        restAreas = new int[N+2];
        restAreas[N+1] = L;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            restAreas[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int min = getMin();
        System.out.print(min);
    }//main

    private static int getMin() {
        Arrays.sort(restAreas);
        
        int s = 1;
        int e = L;
        int len = N+2;
        int mid, cnt;

        while(s <= e) {
            mid = (s+e) / 2;
            cnt = 0;

            for(int i=1; i<len; i++) {
                cnt += (restAreas[i] - restAreas[i-1] - 1) / mid;
            }

            if(cnt > M) s = mid + 1;
            else e = mid - 1;
        }

        return s;
    }//getMin

}//class