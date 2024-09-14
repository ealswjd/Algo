import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17140
public class Main {
    static final int N = 100;
    static final int NUM = 0;
    static final int CNT = 1;
    static int rCnt, cCnt;
    static int[][] A;


    public static void main(String[] args) throws Exception {
        init();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        // 초기에 주어지는 배열은 3×3
        for(int i=0; i<rCnt; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<cCnt; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = getTime(r, c, k);
        System.out.print(time);
    }//main


    private static int getTime(int r, int c, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[CNT] == o2[CNT]) return o1[NUM] - o2[NUM];
            return o1[CNT] - o2[CNT];
        });

        int time = 0; // 시간
        int max;

        // 제한 시간 100초
        while(time <= 100) {
            // A[r][c]에 들어있는 값이 k인지 확인
            if(A[r][c] == k) return time;

            max = 0;

            if(rCnt >= cCnt) { // 행의 개수 ≥ 열의 개수인 경우
                for(int i=0; i<rCnt; i++) {
                    max = Math.max(max, R(i, pq));
                }

                cCnt = max;
            }
            else { // 행의 개수 < 열의 개수인 경우
                for(int i=0; i<cCnt; i++) {
                    max = Math.max(max, C(i, pq));
                }

                rCnt = max;
            }

            time++;
        }

        
        return -1;
    }//getTime


    // R 연산 : 배열 A의 모든 행에 대해서 정렬을 수행
    static int R(int r, PriorityQueue<int[]> pq) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        int size = 0;

        // 수가 몇 개 있는지 개수 세기
        for(int c=0; c<cCnt; c++) {
            if(A[r][c] == 0) continue;
            cntMap.put(A[r][c], cntMap.getOrDefault(A[r][c], 0) + 1);
        }

        // pq에 0을 제외한 수와 개수 담기
        for(int n=1; n<=N; n++) {
            if(cntMap.containsKey(n)) {
                pq.offer(new int[] {n, cntMap.get(n)});
            }
        }

        // 가장 큰 열
        size = Math.min(pq.size() * 2, N);
        int[] row;
        int c = 0;

        // 해당 열 0으로 초기화
        Arrays.fill(A[r], 0);

        while(!pq.isEmpty()) {
            row = pq.poll();
            if(c < N) A[r][c++] = row[NUM];
            if(c < N) A[r][c++] = row[CNT];
        }

        return size;
    }//R


    // C 연산 : 배열 A의 모든 열에 대해서 정렬을 수행
    static int C(int c, PriorityQueue<int[]> pq) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        int size = 0;

        // 수가 몇 개 있는지 개수 세기
        for(int r=0; r<rCnt; r++) {
            if(A[r][c] == 0) continue;
            cntMap.put(A[r][c], cntMap.getOrDefault(A[r][c], 0) + 1);
        }

        // pq에 0을 제외한 수와 개수 담기
        for(int n=1; n<=N; n++) {
            if(cntMap.containsKey(n)) {
                pq.offer(new int[] {n, cntMap.get(n)});
            }
        }

        // 가장 큰 행
        size = Math.min(pq.size() * 2, N);
        int[] col;
        int r = 0;

        // 해당 행 0으로 초기화
        for(int i=0; i<N; i++) {
            A[i][c] = 0;
        }

        while(!pq.isEmpty()) {
            col = pq.poll();
            if(r < N) A[r++][c] = col[NUM];
            if(r < N) A[r++][c] = col[CNT];
        }

        return size;
    }//C


    private static void init() {
        A = new int[N][N];
        rCnt = 3; // 행의 개수
        cCnt = 3; // 열의 개수
    }//init


}//class