import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1102
public class Main {
    private static final int MAX = 3333333;
    private static int N, P; // 발전소 개수, 켜야되는 발전소 개수
    private static int workingCnt, status; // 동작 개수, 동작 상태
    private static int total;
    private static int[][] cost; // 발전소 재시작 비용
    private static int[] minCost; // 고치는 최소 비용

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main
    
    private static void solve() {
        Arrays.fill(minCost, -1);
        int result = dfs(workingCnt, status);

        System.out.print(result == MAX ? -1 : result);
    }//solve
    
    private static int dfs(int cnt, int curStatus) {
        if (P <= cnt || curStatus == total - 1) return 0;
        if (minCost[curStatus] != -1) return minCost[curStatus];
        
        int min = MAX;
        
        for(int cur=0; cur<N; cur++) {
            // 현재 발전소 이미 켜져있으면 패스
            if ((curStatus & (1 << cur)) != 0) continue;
            
            // 현재 발전소를 어떤 발전소에서 재시작할지 결정
            for(int prev=0; prev<N; prev++) {
                // 이전 발전소에서 현재 발전소 재시작할 수 없는 상태면 패스
                if (cur == prev || (curStatus & (1 << prev)) == 0) {
                    continue;
                }
                // 현재 발전소 재시작 비용
                int curCost = cost[prev][cur] + dfs(cnt + 1, curStatus | (1 << cur));
                min = Math.min(min, curCost);
            }
        }
        
        return minCost[curStatus] = min;
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 발전소 개수

        total = (1 << N);
        cost = new int[N][N]; // 시작 비용
        minCost = new int[total]; // 고치는 최소 비용

        // 발전소 i를 이용해서 발전소 j를 재시작할 때 드는 비용이 주어진다
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 발전소가 켜져있으면 Y, 꺼져있으면 N이 순서대로 주어진다
        char[] tmp = br.readLine().toCharArray();
        for(int i=0; i<N; i++) {
            // 발전소가 켜져있으면
            if (tmp[i] == 'Y') {
                workingCnt++;
                status |= (1 << i);
            }
        }

        // 마지막 줄에는 P가 주어진다
        P = Integer.parseInt(br.readLine()); // 켜야되는 발전소 개수

        br.close();
    }//init

}//class