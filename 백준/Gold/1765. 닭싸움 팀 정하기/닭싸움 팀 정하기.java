import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1765
public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> E;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 학생의 수
        M = Integer.parseInt(br.readLine()); // 인간관계 중 알려진 것의 개수

        init();

        StringTokenizer st;
        int a, b;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "E" : // 원수
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    E.get(a).add(b);
                    E.get(b).add(a);

                    break;
                case "F" : // 친구
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    union(a, b);

                    break;
            }

        }

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        Set<Integer> set = new HashSet<>(N+1);

        for(int i=1; i<=N; i++) {
            for(int e : E.get(i)) {
                for(int f : E.get(e)) {
                    union(i, f);
                }
            }
        }

        
        for(int i=1; i<=N; i++) {
            set.add(find(i));
        }

        return set.size();
    }//getCnt

    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a <= b) parent[b] = a;
        else parent[a] = b;
    }//union

    
    private static int find(int n) {
        if(parent[n] == n) return n;

        return parent[n] = find(parent[n]);
    }//find

    
    private static void init() {
        parent = new int[N+1];
        E = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            parent[i] = i;
            E.add(new ArrayList<>());
        }
    }//init

    
}//class