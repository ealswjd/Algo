import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24479
public class Main {
    static int N, order;
    static int[] orders;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int R = Integer.parseInt(st.nextToken()) - 1; // 시작 정점

        init();

        while (M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            list.get(a).add(b);
            list.get(b).add(a);
        }
        br.close();

        for(int i=0; i<N; i++) {
            Collections.sort(list.get(i));
        }

        dfs(R);
        print();
    }//main

    
    private static void print() {
        StringBuilder ans = new StringBuilder();

        for (int n : orders) {
            ans.append(n).append('\n');
        }

        System.out.print(ans);
    }//print

    
    private static void dfs(int cur) {
        orders[cur] = order++;

        for(int next : list.get(cur)) {
            if(orders[next] != 0) continue;
            dfs(next);
        }
    }//dfs

    
    private static void init() {
        order = 1;
        orders = new int[N];
        list = new ArrayList<>(N);

        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class