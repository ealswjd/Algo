import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14567
public class Main {
    static int N, M;
    static int[] cnt;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 과목의 수
        M = Integer.parseInt(st.nextToken()); // 선수 조건의 수

        init();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            cnt[b]++;
            list.get(a).add(b);
        }
        br.close();

        sol();
    }//main

    private static void sol() {
        int[] result = new int[N];
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<N; i++) {
            if(cnt[i] == 0) {
                q.offer(i);
                result[i] = 1;
            }
        }

        int cur;

        while(!q.isEmpty()) {
            cur = q.poll();

            for(int next : list.get(cur)) {
                if(cnt[next] == 0) continue;
                if(--cnt[next] == 0) {
                    q.offer(next);
                    result[next] = result[cur] + 1;
                }
            }

        }

        print(result);
    }//sol

    private static void print(int[] result) {
        StringBuilder ans = new StringBuilder();

        for(int time : result) {
            ans.append(time).append(' ');
        }

        System.out.print(ans);
    }//print

    private static void init() {
        cnt = new int[N];
        list = new ArrayList<>(N);

        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class