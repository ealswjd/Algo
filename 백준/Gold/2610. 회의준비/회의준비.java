import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2610
public class Main {
    static final int INF = 98765;
    static int N; // 회의에 참석하는 사람의 수
    static int[] parent;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 회의에 참석하는 사람의 수
        int M = Integer.parseInt(br.readLine()); // 관계의 수

        init();

        StringTokenizer st;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
            dist[a][b] = dist[b][a] = 1;
        }

        getResult();
    }//main

    
    private static void getResult() {
        StringBuilder ans = new StringBuilder();
        Set<Integer> teams = new HashSet<>();
        int K; // 위원회의 수

        for(int i=1; i<=N; i++) {
            teams.add(find(i));
        }

        floyd();

        K = teams.size();
        int[] leaders = new int[K];

        // 각 위원회의 대표 번호 구하기
        int i=0;
        for(int team : teams) {
            leaders[i++] = getLeader(team); // 위원회의 대표
        }
        Arrays.sort(leaders); // 대표 번호 작은 수부터

        ans.append(K).append('\n');
        for(int leader : leaders) {
            ans.append(leader).append('\n');
        }

        System.out.print(ans);
    }//getResult

    
    private static int getLeader(int team) {
        int min = INF;
        int leader = 0;

        for(int s=1; s<=N; s++) {
            int max = 0;
            if(parent[s] == team) {
                for(int e=1; e<=N; e++) {
                    if(dist[s][e] == INF || s == e) continue;
                    max = Math.max(max, dist[s][e]);
                }

                // 최댓값이 최소가 되도록 대표 정하기
                if(max < min) {
                    min = max;
                    leader = s;
                }
            }
        }

        return leader;
    }//getLeader

    
    private static void floyd() {

        for(int i=1; i<=N; i++) {
            for(int s=1; s<=N; s++) {
                for(int e=1; e<=N; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][i] + dist[i][e]);
                }
            }
        }

    }//floyd

    
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
        dist = new int[N+1][N+1];
        parent = new int[N+1];

        for(int i=0; i<=N; i++) {
            Arrays.fill(dist[i], INF);
            parent[i] = i;
        }
    }//init

}//class