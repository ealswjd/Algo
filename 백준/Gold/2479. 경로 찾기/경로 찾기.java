import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2479
public class Main {
    private static int N, A, B;
    private static int[] prev;
    private static List<List<Integer>> list;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int[] result = bfs();
        int idx = result[0];
        int cnt = result[1];

        if (cnt == -1) {
            ans.append(cnt);
        } else {
            Stack<Integer> path = new Stack<>();

            for(int i=0; i<cnt; i++) {
                path.add(idx + 1);
                idx = prev[idx];
            }

            while(!path.isEmpty()) {
                ans.append(path.pop()).append(' ');
            }
        }

        System.out.print(ans);
    }//sol

    private static int[] bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        int idx = A - 1, cnt = 1;
        int[] cur;

        q.offer(new int[] {idx, cnt});
        visited[idx] = true;

        while(!q.isEmpty()) {
            cur = q.poll();
            idx = cur[0];
            cnt = cur[1];

            if (idx == B-1) {
                return cur;
            }

            for(int next : list.get(idx)) {
                if (visited[next]) continue;

                visited[next] = true;
                prev[next] = idx;
                q.offer(new int[] {next, cnt + 1});
            }
        }

        return new int[] {-1, -1};
    }//bfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] code = new int[N];
        prev = new int[N];
        list = new ArrayList<>(N);

        for(int i=0; i<N; i++) {
            int bit = 0;
            char[] tmp = br.readLine().toCharArray();
            list.add(new ArrayList<>());

            for(int j=0; j<K; j++) {
                if (tmp[j] == '1') {
                    bit |= (1 << j);
                }
            }
            code[i] = bit;
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        br.close();

        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if (Integer.bitCount(code[i] ^ code[j]) == 1) {
                    list.get(i).add(j);
                    list.get(j).add(i);
                }
            }
        }
    }//init

}//class