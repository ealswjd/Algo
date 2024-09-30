import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17250
public class Main {
    static int N, M; // 은하의 수 N과 철도의 개수 M
    static int[] parent;
    static HashMap<Integer, Long> cntMap; // 은하 내에 존재하는 행성 수

    public static void main(String[] args) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 은하의 수
        M = Integer.parseInt(st.nextToken()); // 철도의 개수

        init();

        for(int i=1; i<=N; i++) {
            cntMap.put(i, Long.parseLong(br.readLine()));
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int key = union(a, b); // 행성 연결 

            ans.append(cntMap.get(key)).append('\n');
        }

        System.out.print(ans);
    }//main

    private static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return a;

        if(a < b) {
            parent[b] = a;
            cntMap.put(a, cntMap.get(a) + cntMap.get(b));
        }
        else {
            parent[a] = b;
            cntMap.put(b, cntMap.get(a) + cntMap.get(b));
        }

        return parent[a];
    }//union

    private static int find(int n) {
        if(n == parent[n]) return n;

        return parent[n] = find(parent[n]);
    }//find

    private static void init() {
        cntMap = new HashMap<>();
        parent = new int[N+1];

        for(int i=0; i<=N; i++) {
            parent[i] = i;
        }
    }//init

    
}//class