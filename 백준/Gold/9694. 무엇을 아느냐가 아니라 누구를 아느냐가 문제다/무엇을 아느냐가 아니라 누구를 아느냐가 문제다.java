import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9694
public class Main {
    private static final int INF = 100;
    private static int N;
    private static List<List<int[]>> list;
    private static int[] prev;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<=T; i++) {
            init(br);
            dijkstra();
            getResult(i, ans);
        }

        br.close();
        System.out.print(ans);
    }//main


    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] minCost = new int[N];
        Arrays.fill(minCost, INF);

        pq.offer(new int[] {0, 0});
        minCost[0] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int num = cur[0];
            int cost = cur[1];

            if(minCost[num] < cost) continue;
            if(num == N-1) break;

            for(int[] next : list.get(num)) {
                int nextNum = next[0];
                int nextCost = next[1] + cost;

                if(minCost[nextNum] > nextCost) {
                    prev[nextNum] = num;
                    minCost[nextNum] = nextCost;
                    pq.offer(new int[] {nextNum, nextCost});
                }
            }
        }

    }//dijkstra


    private static void getResult(int num, StringBuilder ans) {
        ans.append("Case #").append(num).append(": ");

        if(prev[N-1] == -1) ans.append("-1");
        else {
            Stack<Integer> stack = new Stack<>();

            for(int i=N-1; i!=prev[i]; i=prev[i]) {
                stack.add(prev[i]);
            }

            while(!stack.isEmpty()) {
                ans.append(stack.pop()).append(' ');
            }
            ans.append(N-1);
        }

        ans.append('\n');
    }//getResult


    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 관계 수
        N = Integer.parseInt(st.nextToken()); // 정치인 수

        list = new ArrayList<>(N);
        prev = new int[N];

        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
            prev[i] = -1;
        }
        prev[0] = 0;

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken()); // 친밀도

            list.get(a).add(new int[] {b, z});
            list.get(b).add(new int[] {a, z});
        }

    }//init


}//class