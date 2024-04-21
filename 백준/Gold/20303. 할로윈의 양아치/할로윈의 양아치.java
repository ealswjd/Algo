import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 이름(난이도) : 할로윈의 양아치 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/20303
 * */
public class Main {
    static int N, K, sum;
    static int[] candies;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;
    static ArrayList<int[]> groups;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이들의 수
        int M = Integer.parseInt(st.nextToken()); // 아이들의 친구 관계 수
        K = Integer.parseInt(st.nextToken()); // 울음소리가 공명하기 위한 최소 아이의 수

        init();

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        groups.add(new int[] {0, 0});
        for(int i=1; i<=N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            sum = 0;
            int cnt = bfs(i, q);

            groups.add(new int[] {cnt, sum});
        }

        int ans = getMaxCnt();
        System.out.print(ans);
    }//main

    private static int bfs(int cur, Queue<Integer> q) {
        int cnt = 1;
        q.offer(cur);

        while(!q.isEmpty()) {
            cur = q.poll();
            sum += candies[cur];

            for(int next : list.get(cur)) {
                if(visited[next]) continue;

                visited[next] = true;
                cnt++;
                q.offer(next);
            }
        }

        return cnt;
    }//bfs

    private static int getMaxCnt() {
        int max = 0, gSize = groups.size();
        int[][] dp = new int[gSize+1][K+1];
        int[] group;

        for(int i=1; i<gSize; i++) {
            group = groups.get(i);
            for(int j=1; j<K; j++) {
                if(group[0] > j) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-group[0]] + group[1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }//getMaxCnt


    private static void init() {
        candies = new int[N+1];
        visited = new boolean[N+1];
        list = new ArrayList<>(N+1);
        groups = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class