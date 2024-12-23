import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 https://www.acmicpc.net/problem/2611
 1번 지점에서 출발하여 가장 많은 점수를 얻어 다시 1번 지점으로 돌아오는 팀이 우승
 */
public class Main {
    private static final int S = 1; // 출발지
    private static int[] dp;     // 지점 별 최대 점수
    private static int[] count;  // 이전 지점 개수
    private static int[] prev;   // 이전 지점
    private static List<List<int[]>> roadList; // 길 정보


    public static void main(String[] args) throws IOException {
        init();
        getMaxScore();
        print();
    }//main


    private static void getMaxScore() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);

        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            // 1번 지점에서 시작하여 1번 지점으로 끝나야 한다
            if(cur == S && count[S] == 0) break;

            for(int[] next : roadList.get(cur)) {
                int node = next[0];
                int score = next[1] + dp[cur];

                if(dp[node] < score) {
                    dp[node] = score;
                    prev[node] = cur;
                }
                if(--count[node] == 0) {
                    q.offer(node);
                }
            }
        }
        
    }//getMaxScore


    private static void print() {
        StringBuilder ans = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int cur = S;

        while(true) {
            stack.add(cur);

            if(prev[cur] == S) {
                stack.add(S);
                break;
            }

            cur = prev[cur];
        }

        // 첫째 줄에는 그 얻는 점수를 출력
        ans.append(dp[S]).append('\n');
        // 둘째 줄에는 그 경로를 출력
        while(!stack.isEmpty()) {
            ans.append(stack.pop()).append(' ');
        }

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        roadList = new ArrayList<>(N+1);
        dp = new int[N+1];
        count = new int[N+1];
        prev = new int[N+1];

        for(int i=0; i<=N; i++) {
            roadList.add(new ArrayList<>());
        }

        StringTokenizer st;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            roadList.get(from).add(new int[] {to, score});
            count[to]++;
        }

        br.close();
    }//init


}//class