import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17259
public class Main {
    private static final int R = 0, C = 1;
    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {1, 0, -1, 0};
    private static int B; // 공장 크기
    private static int N; // 직원 수
    private static int M; // 선물 개수
    private static int size; // 컨테이너 사이즈
    private static int[][] map; // 공장
    private static int[][] containerPositions; // 컨테이너 위치 정보
    private static List<Integer> staffList; // 직원들 작업 시간


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        int totalCount = 0; // 포장 선물 개수
        int giftsLeft = M; // 남은 선물 개수
        int[] isPacking = new int[N + 1]; // 직원 포장 여부
        int[] container = new int[size]; // 컨테이너
        int gift = 1; // 선물

        while(giftsLeft > 0) {
            // 선물 이동
            giftsLeft -= move(container);
            if(giftsLeft == 0) break;

            // 컨테이너에 올릴 선물이 남았다면 올리기
            if(gift <= M) {
                container[0] = gift++;
            }

            // 포장 직원 확인
            int packing = findNearbyStaff(container, isPacking);
            giftsLeft -= packing;
            totalCount += packing;

            // 포장 진행
            executePacking(isPacking);
        }

        return totalCount;
    }//getCnt


    private static int findNearbyStaff(int[] container, int[] isPacking) {
        int cnt = 0;
        int r, c, nr, nc;

        // 벨트 위에 더 오래 올려져 있던 선물을 포장
        for(int i=size-1; i>=0; i--) {
            if(container[i] == 0) continue;

            r = containerPositions[i][R];
            c = containerPositions[i][C];

            for(int d=0; d<4; d++) {
                nr = r + dr[d];
                nc = c + dc[d];
                if(rangeCheck(nr, nc)) continue;

                // 포장 가능한 직원인지 확인
                int staff = map[nr][nc];
                if(isPacking[staff] > 0) continue;

                // 선물 내려서 포장
                isPacking[staff] = staffList.get(staff);
                cnt++;
                container[i] = 0;
                break;
            }
        }

        return cnt;
    }//findNearbyStaff


    private static void executePacking(int[] isPacking) {
        for(int i=1; i<=N; i++) {
            if(isPacking[i] > 0) isPacking[i]--;
        }
    }//executePacking


    private static int move(int[] container) {
        int cnt = 0; // 폐기되는 선물
        int next;

        for(int i=size-1; i>=0; i--) {
            if(container[i] == 0) continue;
            next = i + 1;

            // 벨트의 끝 지점을 지나면 그 선물은 벨트에서 떨어져 폐기
            if(next == size) {
                container[i] = 0;
                cnt++;
                continue;
            }

            // 선물 다음칸으로 이동
            container[next] = container[i];
            container[i] = 0;
        }

        return cnt;
    }//move


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= B || c < 0 || c >= B || map[r][c] == 0;
    }//rangeCheck


    private static void setContainer() {
        int num = 0;

        for(int r=0; r<B-1; r++) {
            for(int c=0; c<B; c++) {
                if(r == 0 || c == B-1 || r == B-1) {
                    containerPositions[num] = new int[] {r, c};
                    num++;
                }
            }
        }

        for(int c=B-1, r=B-1; c>=0; c--) {
            containerPositions[num] = new int[] {r, c};
            num++;
        }
    }//setContainer


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken()); // 공장 크기
        N = Integer.parseInt(st.nextToken()); // 직원 수
        M = Integer.parseInt(st.nextToken()); // 선물 개수

        size = B * 3 - 2; // 컨테이너 사이즈
        map = new int[B][B]; // 공장
        staffList = new ArrayList<>(N + 1); // 직원들
        containerPositions = new int[size][2];

        staffList.add(0); // 직원 1번부터 시작

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            map[r][c] = i + 1;
            staffList.add(time);
        }

        setContainer(); // 컨테이너 세팅

        br.close();
    }//init


}//class