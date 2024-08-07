import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2568
public class Main {
    static final int A=0, B=1, INF=500_000;
    static int N; // 전깃줄의 개수
    static int[] counts; // 설치 개수
    static int[][] numbers; //전봇대와 연결되는 위치의 번호
    static int[] dp; // 연결 가능한 번호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 전깃줄의 개수

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 전봇대 A
            int b = Integer.parseInt(st.nextToken()); // 전봇대 B

            numbers[i][A] = a;
            numbers[i][B] = b;
        }


        int max = getMax(); // 전깃줄이 교차하지 않게 연결 가능한 개수
        print(max);
    }//main

    private static void print(int max) {
        StringBuilder ans = new StringBuilder();
        Stack<Integer> remove = new Stack<>(); // 없애는 전봇대 번호
        int minCnt = N - max; // 없애야 하는 전깃줄의 최소 개수

        ans.append(minCnt).append('\n');

        int cnt = max;
        for(int i=N-1; i>=0; i--) {
            if(counts[i] == cnt) cnt--;
            else {
                remove.add(numbers[i][A]);
            }
        }

        while(!remove.isEmpty()) {
            ans.append(remove.pop()).append('\n');
        }

        System.out.print(ans);
    }//print

    private static int getMax() {
        Arrays.sort(numbers, Comparator.comparingInt(o -> o[A]));
        int cnt = 0;
        int idx = 0;

        for(int i=0; i<N; i++) {
            if(numbers[i][B] > dp[cnt]) {
                dp[++cnt] = numbers[i][B];
                counts[i] = cnt;
                continue;
            }

            idx = binarySearch(cnt, numbers[i][B]);
            dp[idx] = numbers[i][B];
            counts[i] = idx;
        }

        return cnt;
    }//getMax

    private static int binarySearch(int end, int target) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] > target) end = mid;
            else start = mid+1;
        }

        return end;
    }

    private static void init() {
        numbers = new int[N][2];
        dp = new int[N+1];
        counts = new int[INF+1];
    }//init

}//class