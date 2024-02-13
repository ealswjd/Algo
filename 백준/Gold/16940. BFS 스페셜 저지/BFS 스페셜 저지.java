import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] order;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        int a, b;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            list.get(a).add(b);
            list.get(b).add(a);
        }//for

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            order[i] = Integer.parseInt(st.nextToken()) - 1;
        }//for
        br.close();

        int result = checkOrder(0);
        System.out.print(result);
    }//main

    private static int checkOrder(int start) {
        if(order[0] != start) return 0;

        boolean[] visited = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int cur, cnt, orderIdx=1;
        while(!q.isEmpty()) {
            cur = q.poll();
            cnt = 0;
            for(int next : list.get(cur)) {
                if(visited[next]) continue;
                visited[next] = true;
                cnt++;
            }//for

            for(int i=orderIdx, max=orderIdx+cnt; i<max; i++) {
                if(!visited[order[i]]) return 0;
                q.offer(order[i]);
            }//for

            orderIdx += cnt;
        }//while

        return 1;
    }//checkOrder

    private static void init() {
        order = new int[N];
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }//for
    }//init

}//class