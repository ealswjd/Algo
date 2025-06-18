import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32176
public class Main {
    private static int N, M, K1, K2; // 마을 크기, 노드 개수, 낮 활성화 개수, 밤 활성화 개수
    private static Node[] nodes;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int dayLevel = dfs(0, 0, new boolean[M], M - K1, true); // 낮
        int nightLevel = dfs(0, 0, new boolean[M], K2, false); // 밤

        System.out.printf("%d\n%d", dayLevel, nightLevel);
    }//sol


    private static int dfs(int idx, int cnt, boolean[] visited, int K, boolean isDay) {
        if(cnt == K) {
            return getMax(visited, isDay);
        }
        if(idx == M) {
            return 0;
        }

        int c = 0; // 통신 성능 저하 수치

        for(int i=idx; i<M; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            c = Math.max(c, dfs(i, cnt + 1, visited, K, isDay));
            visited[i] = false;
        }

        return c;
    }//dfs


    private static int getMax(boolean[] visited, boolean isDay) {
        int minR = N, minC = N;
        int maxR = 0, maxC = 0;
        int p = 0;

        for(int i=0; i<M; i++) {
            boolean isChecked = isDay != visited[i];

            if(isChecked) {
                p += nodes[i].dist;
                minR = Math.min(minR, nodes[i].r);
                minC = Math.min(minC, nodes[i].c);
                maxR = Math.max(maxR, nodes[i].r);
                maxC = Math.max(maxC, nodes[i].c);
            }
        }
        // 활성화된 모든 노드를 포함하는 최소 직사각형의 넓이
        int u = (maxR - minR + 1) * (maxC - minC + 1);

        // 통신 성능 저하 수치 max(p-u, 0)
        return Math.max(p - u, 0);
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 줄에 N, M, K1, K2가 공백으로 구분되어 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 마을 크기
        M = Integer.parseInt(st.nextToken()); // 노드 개수
        K1 = Integer.parseInt(st.nextToken()); // 낮 활성화 개수
        K2 = Integer.parseInt(st.nextToken()); // 밤 활성화 개수

        char[][] map = new char[N][N]; // 마을
        nodes = new Node[M];
        int r = 0;
        int c = 0;

        // 마을의 정보가 주어진다. F라면 빈칸, B라면 기지국, N이라면 비활성화된 노드를 의미
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if(map[i][j] == 'B') {
                    r = i;
                    c = j;
                }
            }
        }

        int m = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 'N') {
                    int dist = Math.abs(r - i) + Math.abs(c - j);
                    nodes[m++] = new Node(i, j, dist);
                }
            }
        }

        br.close();
    }//init


    private static class Node {
        int r;
        int c;
        int dist;
        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }//Node


}//class