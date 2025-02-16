import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1818
public class Main {
    private static int N; // 책의 개수
    private static int[] books; // 책번호


    public static void main(String[] args) throws IOException {
        init();

        int MinCnt = getMinCnt();
        System.out.print(MinCnt);
    }//main


    private static int getMinCnt() {
        int[] dp = new int[N+1];
        int cnt = 0;
        int idx;

        for(int i=0; i<N; i++) {
            if(books[i] > dp[cnt]) {
                dp[++cnt] = books[i];
                continue;
            }

            idx = getIndex(books[i], cnt, dp);
            dp[idx] = books[i];
        }

        return N - cnt;
    }//getMinCnt


    private static int getIndex(int target, int end, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] > target) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 책의 개수

        books = new int[N]; // 책번호

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            books[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class