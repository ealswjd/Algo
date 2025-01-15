import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27977
public class Main {
    private static int L; // 학교 위치
    private static int K; // 최대 충전소 방문 횟수
    private static int len; // 총 거리
    private static int max; // 최대 거리
    private static int[] positions; // 충전소의 위치


    public static void main(String[] args) throws IOException {
        init();

        // 킥보드 중에서도 가장 싼 킥보드를 구매하고자 한다.
        int cost = getMinCost();
        System.out.print(cost);
    }//main


    private static int getMinCost() {
        int minCost = L;
        int start = max;
        int end = L;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                end = mid - 1;
                minCost = mid;
            }
            else start = mid + 1;
        }

        return minCost;
    }//getMinCost


    private static boolean isPossible(int mid) {
        int cnt = 0;    // 충전소 방문 횟수
        int cur = mid;  // 현재 배터리 용량
        int dist;       // 거리

        for(int i=1; i<len; i++) {
            dist = positions[i] - positions[i-1];

            if(cur < dist) {
                if(++cnt > K) return false;
                cur = mid - dist;
            }
            else {
                cur -= dist;
            }
        }

        return cnt <= K;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 학교까지의 거리
        int N = Integer.parseInt(st.nextToken()); // 킥보드 충전소의 개수
        K = Integer.parseInt(st.nextToken()); // 최대 충전소 방문 횟수

        len = N + 2; // 집, 학교 포함
        positions = new int[len]; // 충전소의 위치
        positions[N+1] = L; // 학교 위치 추가

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, positions[i] - positions[i-1]);
        }
        max = Math.max(max, positions[N+1] - positions[N]);

        br.close();
    }//init


}//class