import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 https://www.acmicpc.net/problem/11437
 11437.LCA - M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.
 */
public class Main {
    private static final int LOG = 17; // 2^16 = 65536, N의 최댓값(50,000)을 커버
    private static final int ROOT = 1; // 루트는 1번이다.
    private static int N; // 정점 개수
    private static int[] depth; // 깊이
    private static int[][] parent; // 부모
    private static List<List<Integer>> tree; // 트리 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        depth = new int[N+1];
        parent = new int[LOG][N+1];
        tree = new ArrayList<>(N+1);

        // 트리 초기화
        for(int i=0; i<=N; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 연결
        StringTokenizer st;
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        // 루트 노드 기준으로 각 노드의 깊이, 직속 부모 세팅
        bfs();
        // 희소 배열 채우기
        fillParent();

        // M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력
        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ans.append(lca(a, b)).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main
    
    private static int lca(int a, int b) {
        // 무조건 b가 더 깊은 노드가 되도록
        if (depth[a] > depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        // a와 b의 깊이를 동일하게 맞추기
        for(int k=LOG-1; k>=0; k--) {
            if (depth[b] - depth[a] >= (1 << k)) {
                b = parent[k][b];
            }
        }
        
        // 깊이를 맞췄는데 두 노드가 같다면 a 자체가 LCA
        if (a == b) return a;
        
        // 공통 조상 바로 밑까지 두 노드를 동시에 끌어올림
        for(int k=LOG-1; k>=0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        
        // a와 b의 바로 윗 부모(2^0)가 최종 LCA
        return parent[0][a];
    }//lca

    // 트리의 상태(깊이, 직속 부모) 세팅
    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        q.offer(ROOT);
        visited[ROOT] = true;
        depth[ROOT] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next : tree.get(cur)) {
                if (visited[next]) continue;
                
                q.offer(next);
                visited[next] = true;
                depth[next] = depth[cur] + 1;
                parent[0][next] = cur;
            }
        }

    }//bfs

    // dp로 2^K 번째 부모들을 미리 계산하여 저장
    private static void fillParent() {
        
        for(int k=1; k<LOG; k++) {
            for(int n=1; n<=N; n++) {
                if (parent[k-1][n] != 0) {
                    parent[k][n] = parent[k-1][parent[k-1][n]];
                }
            }
        }
        
    }//fillParent

}//class