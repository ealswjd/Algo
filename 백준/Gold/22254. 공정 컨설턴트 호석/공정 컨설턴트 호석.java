import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/22254
 모든 선물을 X시간 이내에 제작하기 위해 필요한 최소 공정 라인 개수를 출력
 */
public class Main {
    private static int N; // 선물 주문의 개수
    private static int X; // 제작 완료까지 남은 시간
    private static int[] products; // 선물별 필요한 공정 시간


    public static void main(String[] args) throws IOException {
        init();

        int minCnt = getMinCnt();
        System.out.print(minCnt);
    }//main


    private static int getMinCnt() {
        PriorityQueue<Integer> line = new PriorityQueue<>(); // 공정 라인
        int cnt = N; // 최소 공정 라인 개수
        int start = 1;
        int end = N;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid, line)) {
                end = mid - 1;
                cnt = mid;
            }
            else {
                start = mid + 1;
            }

            line.clear();
        }

        return cnt;
    }//getMinCnt


    private static boolean isPossible(int cnt, PriorityQueue<Integer> line) {
        int cur = 0; // 현재 선물 인덱스
        int time; // 공정 시간

        // 공정 라인 개수만큼 선물 올리기
        for(; cur<cnt; cur++) {
            line.add(products[cur]);
        }

        while(cur < N) {
            time = line.poll();
            // X 시간 이내에 제작 가능한지 체크
            if(time + products[cur] > X) return false;

            // 가능하다면 선물 배정
            line.add(time + products[cur]);
            cur++;
        }

        return cur == N;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 선물 주문의 개수
        X = Integer.parseInt(st.nextToken()); // 제작 완료까지 남은 시간

        products = new int[N]; // 선물별 필요한 공정 시간

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            products[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class