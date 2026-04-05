import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16169
public class Main {
    private static int N; // 컴퓨터의 개수
    private static int maxRank; // 가장 높은 계급
    private static int[] times; // i번 컴퓨터의 동작 속도
    private static List<List<Integer>> rankList; // 각 계급에 속한 컴퓨터 번호

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        int[] dp = new int[N+1]; // i번 컴퓨터의 수행이 끝날 때까지 걸린 시간

        // 제일 낮은 계급의 컴퓨터는 전달 받을 정보가 없으므로 시스템 시동과 동시에 동작
        for(int i : rankList.get(1)) {
            dp[i] = times[i];
        }

        for(int r=2; r<=maxRank; r++) {
            for(int cur : rankList.get(r)) {
                int maxTime = 0;

                // 이전 계급의 모든 컴퓨터에게 정보를 전달 받음
                for(int prev : rankList.get(r-1)) {
                    // i번 컴퓨터와 j번 컴퓨터 간의 전송 시간은 (i - j)^2
                    int time = dp[prev] + (prev - cur) * (prev - cur);
                    maxTime = Math.max(maxTime, time);
                }

                // 전송 완료 시간 + 현재 컴퓨터 동작 속도
                dp[cur] = maxTime + times[cur];
            }
        }

        // 가장 높은 계급의 종료 시간 중 가장 늦게 끝난 시간
        int ans = 0;
        for(int i : rankList.get(maxRank)) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.print(ans);
    }//solve

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        times = new int[N+1]; // i번 컴퓨터의 동작 속도
        rankList = new ArrayList<>(N+1); // 각 계급에 속한 컴퓨터 번호

        for(int i=0; i<=N; i++) {
            rankList.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            // 각 컴퓨터의 계급과 동작 속도 t가 공백을 두고 주어진다
            st = new StringTokenizer(br.readLine());
            int rank = Integer.parseInt(st.nextToken());
            times[i] = Integer.parseInt(st.nextToken());

            rankList.get(rank).add(i);
            maxRank = Math.max(maxRank, rank);
        }

        br.close();
    }//init

}//class