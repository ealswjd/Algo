import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31503
public class Main {
    private static int N, Q; // 문제의 수, 쿼리의 수
    private static int[] left; // 왼쪽부터
    private static int[] right; // 오른쪽부터


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        init(br);

        // Q개의 줄에 걸쳐 선배가 풀라고 한 문제의 번호가 주어진다.
        int num, cnt;
        while(Q-- > 0) {
            num = Integer.parseInt(br.readLine()) - 1;
            cnt = left[num] + right[N - num - 1] - 1;
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main


    private static void getCnt(int[] D, int[] length) {
        int[] lis = new int[N];
        int idx, level;
        Arrays.fill(lis, Integer.MAX_VALUE);

        for(int i=0; i<N; i++) {
            level = D[i];

            idx = lowerBound(level, lis);
            lis[idx] = level;
            length[i] = idx + 1;
        }

    }//getCnt


    private static int lowerBound(int target, int[] lis) {
        int start = 0;
        int end = N;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(lis[mid] >= target) end = mid;
            else start = mid + 1;
        }

        return start;
    }//getIndex


    private static void init(BufferedReader br) throws IOException {
        // 문제의 수 N과 쿼리의 수 Q가 공백으로 구분되어 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 문제의 수
        Q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        int[] levels = new int[N]; // 문제의 난이도
        int[] reverse = new int[N]; // 문제의 난이도 반대방향
        left = new int[N]; // 왼쪽부터
        right = new int[N]; // 오른쪽부터

        // 문제의 난이도를 나타내는 음이 아닌 정수가 문제 번호의 순서대로 공백으로 구분되어 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            levels[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            reverse[i] = - levels[N-i-1];
        }

        getCnt(levels, left); // 왼쪽부터(정방향)
        getCnt(reverse, right); // 오른쪽부터(반대방향)
    }//init


}//class