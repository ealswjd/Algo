import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/25691
public class Main {
    private static int N, K; // 노드의 수, 방문할 수 있는 노드의 수
    private static int max; // 수확할 수 있는 사과 개수의 최댓값
    private static int[] apples; // 사과 정보
    private static Set<String> checked; // 경로 체크
    private static List<List<Integer>> list; // 노드 연결 정보


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        init();
        dfs(0, 1, 1, apples[0]);

        // 수확할 수 있는 사과 개수의 최댓값을 출력한다.
        System.out.print(max);
    }//sol


    private static void dfs(int cur, int visited, int nodeCnt, int apple) {
        max = Math.max(max, apple);
        if(nodeCnt == K) return;

        int nVisited, nCnt, nApple;
        boolean isNewVisit;

        for(int next : list.get(cur)) {
            nVisited = visited | (1 << next);
            isNewVisit = ((visited & (1 << next)) == 0);

            String key = next + " " + nVisited;
            if(checked.contains(key)) continue;

            nCnt = nodeCnt;
            nApple = apple;

            if(isNewVisit) {
                nCnt++;
                nApple += apples[next];
            }

            checked.add(key);
            dfs(next, nVisited, nCnt, nApple);
        }
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 노드의 수 n과 정수 k가 공백을 사이에 두고 순서대로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드의 수
        K = Integer.parseInt(st.nextToken()); // 방문할 수 있는 노드의 수

        list = new ArrayList<>(N); // 노드 연결 정보
        apples = new int[N]; // 사과 정보
        checked = new HashSet<>();

        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }

        // n - 1개 줄에 걸쳐 간선의 정보가 주어진다.
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(p).add(c);
            list.get(c).add(p);
        }

        // 노드의 사과 정보를 나타내는 n개의 정수가 공백을 사이에 두고 순서대로 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            apples[i] = Integer.parseInt(st.nextToken());
        }
        
        br.close();
    }//init


}//class