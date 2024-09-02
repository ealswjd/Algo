import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2637
public class Main {
    static final int NUM = 0, CNT = 1;
    static int N, M;
    static int[] cnt, result;
    static List<List<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 1 ~ N-1 : 부품 번호, N : 완제품 번호
        M = Integer.parseInt(br.readLine()); // 부품들 간의 관계 개수

        init();

        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // X를 만드는데 중간 부품 혹은 기본 부품 Y가 K개 필요
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            cnt[y]++;
            list.get(x).add(new int[] {y, k});
        }

        getCnt();
        print();
    }//main


    private static void getCnt() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {N, 1}); // 완제품
        result[N] = 1;

        int[] cur;
        int num, prevNum, prevCnt;
        while(!q.isEmpty()) {
            cur = q.poll();
            num = cur[NUM];

            for(int[] prev : list.get(num)) {
                prevNum = prev[NUM];
                prevCnt = prev[CNT];

                cnt[prevNum]--;
                result[prevNum] += result[num] * prevCnt;

                if(cnt[prevNum] == 0) {
                    q.offer(new int[] {prevNum, result[prevNum]});
                }
            }
        }

    }//getCnt


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<N; i++) { // N은 완제품
            if(list.get(i).isEmpty()) { // 기본 부품
                ans.append(i).append(" ").append(result[i]).append("\n");
            }
        }

        System.out.print(ans);
    }//print


    private static void init() {
        cnt = new int[N+1]; // 사용 수
        result = new int[N+1]; // 부품 별 필요한 개수
        list = new ArrayList<>(N+1); // 부품 관계

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init


}//class