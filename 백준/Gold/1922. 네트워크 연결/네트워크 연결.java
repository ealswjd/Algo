import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1922
public class Main {
    private static final int A = 0, B = 1, C = 2;
    private static int N;
    private static int[] parent;
    private static List<int[]> computerLine;

    
    public static void main(String[] args) throws IOException {
        init();

        int minCost = getCost();
        System.out.print(minCost);
    }//main

    
    private static int getCost() {
        int cost = 0; // 총 비용
        int cnt = 0;  // 총 연결 개수

        for(int[] line : computerLine) {
            // a 컴퓨터와 b 컴퓨터를 연결하는데 비용이 c
            int a = line[A];
            int b = line[B];
            int c = line[C];

            if(union(a, b)) {
                cost += c;
                if(++cnt >= N-1) break;
            }
        }

        return cost;
    }//getCost;

    
    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(a < b) parent[b] = a;
        else parent[a] = b;

        return true;
    }//union

    
    private static int find(int n) {
        if(n == parent[n]) return n;

        return parent[n] = find(parent[n]);
    }//find

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

        parent = new int[N+1];
        computerLine = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            parent[i] = i;
        }

        // 각 컴퓨터를 연결하는데 드는 비용이 주어진다.
        StringTokenizer st;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a 컴퓨터와 b 컴퓨터를 연결하는데 비용이 c
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            computerLine.add(new int[] {a, b, c});
        }

        // 가격 기준 오름차순 정렬
        computerLine.sort(Comparator.comparingInt(o -> o[C]));

        br.close();
    }//init

    
}//class