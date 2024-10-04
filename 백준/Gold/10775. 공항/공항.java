import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/10775
public class Main {
    static int G; // 게이트의 수
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine()); // 게이트의 수
        int P = Integer.parseInt(br.readLine()); // 비행기의 수

        init();

        int total = 0; // 최대의 비행기 수

        while(P-- > 0) {
            // 1 ~ gate 중 하나 원함
            int gate = Integer.parseInt(br.readLine());
            // 도킹 가능한 게이트 탐색
            int available = find(gate);

            // 도킹할 수 없다면 공항이 폐쇄되고, 이후 어떤 비행기도 도착할 수 없다.
            if(available == 0) break;

            // 게이트 사용
            union(available, available - 1);
            total++;
        }

        br.close();

        System.out.print(total);
    }//main


    private static void union(int from, int to) {
        from = find(from);
        to = find(to);

        parent[from] = to;
    }//union


    private static int find(int n) {
        if(parent[n] == n) return n;

        return parent[n] = find(parent[n]);
    }//find


    private static void init() {
        parent = new int[G+1];

        for(int i=1; i<=G; i++) {
            parent[i] = i;
        }
    }//init


}//class