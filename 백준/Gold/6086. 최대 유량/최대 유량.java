import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/6086
public class Main {
    private static final int TOTAL = 52, MAX = 987654321, A=0, Z=25, AZ=26;
    private static int[][] pipe; // 파이프의 용량


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int[][] flow = new int[TOTAL][TOTAL]; // 현재 흐르는 유량
        int[] parent = new int[TOTAL]; // A - Z까지 갈 수 있는 경로
        int total = 0; // A에서 Z까지의 최대 유량

        while(true) {
            Queue<Integer> q = new LinkedList<>();
            Arrays.fill(parent, -1);

            q.offer(A);
            parent[A] = A;

            while(!q.isEmpty() && parent[Z] == -1) {
                int cur = q.poll();
                for(int next=0; next<TOTAL; next++) {
                    // 잔여 용량이 남아 있고, 아직 방문하지 않은 노드라면
                    if(pipe[cur][next] - flow[cur][next] > 0 && parent[next] == -1) {
                        q.offer(next);
                        parent[next] = cur;
                    }
                }
            }

            // 더이상 경로 없음
            if(parent[Z] == -1) break;

            // 찾은 경로에서 보낼 수 있는 최대 유량 찾기 (직렬 연결)
            int min = MAX;
            for(int i=Z; i!=A; i=parent[i]) {
                // 배수관이 한줄로 연결 돼 있을 때 두 관의 유량 중 최솟값으로 흐르게 된다
                min = Math.min(min, pipe[parent[i]][i] - flow[parent[i]][i]);
            }

            // 유량 업데이트
            for(int i=Z; i!=A; i=parent[i]) {
                flow[parent[i]][i] += min; // 순방향
                flow[i][parent[i]] -= min; // 역방향
            }

            total += min;
        }

        System.out.print(total);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N개의 배수관

        pipe = new int[TOTAL][TOTAL]; // 파이프의 용량

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = toIndex(st.nextToken().charAt(0));
            int b = toIndex(st.nextToken().charAt(0));
            int p = Integer.parseInt(st.nextToken());

            // 병렬 연결은 용량의 합
            pipe[a][b] += p;
            pipe[b][a] += p;
        }

        br.close();
    }//init

    private static int toIndex(int n) {
        if (n <= 'Z') return n - 'A';
        else return n - 'a' + AZ;
    }//toIndex


}//class