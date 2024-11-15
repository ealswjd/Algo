import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24428
public class Main {
    private static int N; // 행렬의 크기
    private static int[][] map;
    private static boolean[][] isP; // 중간 원소 여부

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int P = 3; // 적어도 세 개의 원소를 반드시 거쳐야 함
        int[][][] dp = new int[N+1][N+1][P+1]; // 0 ~ 3개를 거쳤을 때 가장 높은 점수
        dp[N][N][P] = -1; // 도착 여부를 확인하기 위해 -1로 초기화
        dp[1][1][0] = map[1][1]; // 시작점 초기화

        int[] fromTop;
        int[] fromLeft;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(r == 1 && c == 1) continue;

                fromTop = dp[r-1][c]; // 위
                fromLeft = dp[r][c-1]; // 왼쪽

                // 중간 원소를 0~P개 거쳤을 때 최고 점수 갱신
                for(int p=0; p<=P; p++) {
                    int max = Math.max(fromTop[p], fromLeft[p]);
                    if(max > 0) dp[r][c][p] = max + map[r][c];
                }

                // 현재 원소가 중간 원소임
                if(isP[r][c]) {
                    for(int p=P-1; p>=0; p--) {
                        int max = Math.max(fromTop[p], fromLeft[p]);
                        if(max > 0) dp[r][c][p+1] = max + map[r][c];
                    }
                }
            }
        }

        return dp[N][N][P];
    }//getMax

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        isP = new boolean[N+1][N+1];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // P개의 중간 원소
        int P = Integer.parseInt(br.readLine());
        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            isP[r][c] = true;
        }

        br.close();
    }//init

    
}//class