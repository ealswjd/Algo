import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9470
public class Main {
    private static int K, N;
    private static int[] inDegree;
    private static List<List<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        StringBuilder ans = new StringBuilder();
        while(T-- > 0) {
            init(br);

            int result = solve();
            ans.append(K).append(' ');
            ans.append(result).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int solve() {
        Queue<Integer> q = new LinkedList<>();
        int[] order = new int[N+1]; // 최종 순서
        int[] maxCount = new int[N+1]; // 최대 순서 횟수

        for(int i=1; i<=N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                order[i] = 1;
            }
        }

        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            for(int next : list.get(cur)) {
                if (order[next] < order[cur]) {
                    order[next] = order[cur];
                    maxCount[next] = 1;
                } else if (order[next] == order[cur]) {
                    maxCount[next]++;
                }

                if (--inDegree[next] == 0) {
                    q.offer(next);
                    if (maxCount[next] > 1) {
                        order[next]++;
                    }
                }
            }
        }

        return order[N];
    }//solve

    private static void init(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 테스트 케이스 번호
        N = Integer.parseInt(st.nextToken()); // 노드의 수
        int P = Integer.parseInt(st.nextToken()); // 간선의 수

        inDegree = new int[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            inDegree[b]++;
            list.get(a).add(b);
        }
    }//init

}//class