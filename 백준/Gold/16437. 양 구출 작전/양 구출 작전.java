import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16437
public class Main {
    private static List<List<Integer>> tree; // 다리 연결 정보
    private static long[] counts; // 각 섬에 살고 있는 늑대/양의 수 (양:양수, 늑대:음수)

    public static void main(String[] args) throws IOException {
        init();
        // 구명보트가 있는 1번섬부터 탐색 시작
        long result = dfs(1);

        System.out.print(result);
    }//main

    private static long dfs(int cur) {
        long sum = counts[cur];

        // 올라오는 양의 수를 모두 더해줌 
        for(int next : tree.get(cur)) {
            sum += dfs(next);
        }

        if (sum < 0) return 0; // 늑대한테 다 잡아먹힘
        return sum; // 살아남은 양의 수
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 섬의 개수

        counts = new long[N+1]; // 섬에 살고있는 늑대/양의 수
        tree = new ArrayList<>(N+1); // 다리 연결 정보

        for(int i=0; i<=N; i++) {
            tree.add(new ArrayList<>());
        }

        // 2번 섬부터 N번 섬까지 섬의 정보가 주어진다
        StringTokenizer st;
        for(int i=2; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);
            long a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            // 'W'인 경우 i번 섬에 늑대 살고 있음
            if (t == 'W') {
                counts[i] = -a; // 늑대는 음수
            } else { // 양이 살고 있음
                counts[i] = a; // 양은 양수
            }

            // i번 섬에서 p번 섬으로 갈 수 있는 다리가 있음
            tree.get(p).add(i);
        }

        br.close();
    }//init

}//class

/*

4
S 100 3
W 50 1
S 10 1
--------
60

7
S 100 1
S 100 1
W 100 1
S 1000 2
W 1000 2
S 900 6
---------
1200

*/