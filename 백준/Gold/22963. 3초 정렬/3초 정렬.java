import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22963
public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static final int MAX = 1_000_000_000; // 원소 최댓값
    private static int N; // 수열의 길이
    private static int[] A; // 수열
    private static int[] length; // 인덱스별 오름차순 수열 길이


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main


    private static void solution() {
        // LIS 길이 먼저 구하기
        int lisCnt = getLIS(); // LIS 길이
        int K = N - lisCnt; // 연산 횟수

        // 3번의 연산 안에 수열을 오름차순으로 정렬된 상태로 만들 수 없음
        if(K > 3) {
            System.out.print("NO");
            return;
        }

        // 3번의 연산 안에 수열을 오름차순으로 정렬된 상태로 만들 수 있음
        StringBuilder ans = new StringBuilder();
        int cnt = lisCnt;
        int[] lis = new int[N];

        for(int i=N-1; i>=0; i--) {
            if(length[i] == cnt) {
                lis[cnt] = A[i];
                cnt--;
            }
        }

        // 첫째 줄에, 연산 횟수 K 출력
        ans.append("YES\n").append(K).append('\n');

        // 이후, K개의 줄에 걸쳐, 적용할 연산을 순서대로 출력
        cnt = lisCnt;
        for(int i=N-1; i>=0; i--) {
            // 현재 원소가 그대로임
            if(A[i] == lis[cnt]) {
                cnt--;
                continue;
            }

            // 현재 원소를 x로 변경
            int x = A[i + 1];
            // 바꾼 원소의 인덱스, 바뀐 수 출력
            ans.append(i+1).append(' ');
            ans.append(x).append('\n');

            A[i] = x;
        }

        System.out.print(ans);
    }//solution


    private static int getLIS() {
        int[] lis = new int[N+1]; // LIS
        int cnt = 0; // LIS 길이
        int idx;

        Arrays.fill(lis, INF);
        lis[0] = 0;

        for(int i=0; i<N; i++) {
            idx = binarySearch(A[i], cnt + 1, lis);
            lis[idx] = A[i];
            length[i] = idx;

            // LIS 길이 갱신
            cnt = Math.max(cnt, idx);
        }

        return cnt;
    }//getLIS


    private static int binarySearch(int target, int end, int[] lis) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(lis[mid] <= target) start = mid + 1;
            else end = mid;
        }

        return end;
    }//binarySearch


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 수열의 길이

        A = new int[N + 1]; // 수열
        length = new int[N]; // 인덱스별 오름차순 수열 길이

        // 수열의 원소들을 나타내는 N개의 정수가 공백으로 구분되어 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        A[N] = MAX;

        br.close();
    }//init


}//class
/*
16
1 2 3 4 5 6 7 100 200 50 8 9 10 11 12 13
 */