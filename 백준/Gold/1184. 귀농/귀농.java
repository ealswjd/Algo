import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1184
public class Main {
    private static final int OFFSET = 2_500_000;
    private static int N; // 땅의 크기
    private static int[][] prefix; // 누적합 배열
    private static int[] counts; // 각 수익 등장 횟수
    private static int[] visited; // 방문한 인덱스
    private static int vCount;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        long totalWays = 0; // 가능한 방법의 총합

        for(int r=1; r<N; r++) {
            for(int c=1; c<N; c++) {
                // 패턴 1 - (\) 왼쪽 위 & 오른쪽 아래
                // 이전 탐색 카운트 배열 초기화
                clearCounts();

                /* 왼쪽 위 사분면의 가능한 모든 사각형 합 구해서 저장
                * 사각형은 반드시 교차점의 좌상단 칸인 (r-1, c-1)을 우측 하단 모서리로 가져야 함 */
                for(int i=0; i<=r-1; i++) {
                    for(int j=0; j<=c-1; j++) {
                        int sum = getSum(i, j, r-1, c-1);
                        addCount(sum);
                    }
                }
                /* 오른쪽 아래 사분면의 가능한 모든 사각형 합을 구하면서 매칭 확인
                * 사각형은 반드시 교차점의 우측 하단 칸인 (r, c)를 좌측 상단 모서리로 가져야 함 */
                for(int i=r; i<N; i++) {
                    for(int j=c; j<N; j++) {
                        int sum = getSum(r, c, i, j);
                        totalWays += getCount(sum);
                    }
                }

                // 패턴 2 - (/) 오른쪽 위 & 왼쪽 아래
                // 이전 탐색 카운트 배열 초기화
                clearCounts();

                /* 오른쪽 위 사분면의 가능한 모든 사각형 합 저장
                * 사각형은 반드시 교차점의 우측 상단 칸인 (r-1, c)를 좌측 하단 모서리로 가져야 함 */
                for(int i=0; i<=r-1; i++) {
                    for(int j=c; j<N; j++) {
                        int sum = getSum(i, c, r-1, j);
                        addCount(sum);
                    }
                }
                /* 왼쪽 아래 사분면의 모든 사각형 합 확인
                * 사각형은 반드시 교차점의 좌측 하단 칸인 (r, c-1)을 우측 상단 모서리로 가져야 함 */
                for(int i=r; i<N; i++) {
                    for(int j=0; j<=c-1; j++) {
                        int sum = getSum(r, j, i, c-1);
                        totalWays += getCount(sum);
                    }
                }
            }
        }

        System.out.print(totalWays);
    }//solve

    // 특정 합을 배열에 카운트
    private static void addCount(int sum) {
        int idx = sum + OFFSET; // 음수를 위해 오프셋 더하기

        // 처음 등장한 합이라면 기록
        if (counts[idx] == 0) {
            visited[vCount++] = idx;
        }

        counts[idx]++;
    }//addCount

    // 특정 합의 등장 횟수를 가져오는 메서드
    private static int getCount(int sum) {
        return counts[sum + OFFSET];
    }//getCount

    // 두 좌표 (r1, c1) ~ (r2, c2)의 총합 반환
    private static int getSum(int r1, int c1, int r2, int c2) {
        return prefix[r2+1][c2+1] - prefix[r1][c2+1] - prefix[r2+1][c1] + prefix[r1][c1];
    }//getSum

    private static void clearCounts() {
        for(int i=0; i<vCount; i++) {
            counts[visited[i]] = 0;
        }

        vCount = 0;
    }//clearCounts

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 땅의 크기

        prefix = new int[N+1][N+1]; // 누적합
        counts = new int[OFFSET + OFFSET + 10];
        visited = new int[N * N + 10];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int value = Integer.parseInt(st.nextToken());
                // 현재 값 + 위쪽 누적합 + 왼쪽 누적합 - 중복된 좌상단 누적합
                prefix[i+1][j+1] = value + prefix[i][j+1] + prefix[i+1][j] - prefix[i][j];
            }
        }

        br.close();
    }//init

}//class