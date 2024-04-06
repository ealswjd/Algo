import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1953
public class Main {
    static final int BLUE = 0, WHITE=1;
    private static int N, whiteCnt;
    private static boolean[][] rivals;
    private static boolean[] whiteTeam, visited;
    private static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            while(cnt-->0) {
                int rival = Integer.parseInt(st.nextToken()) - 1;
                list.get(i).add(rival);
                list.get(rival).add(i);
                rivals[i][rival] = true;
                rivals[rival][i] = true;
            }

        }//for

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;

            if(rivals[0][i]) dfs(i, WHITE);
            else dfs(i, BLUE);
        }

        print();
    }//main

    private static void print() {
        StringBuilder blue = new StringBuilder();
        StringBuilder white = new StringBuilder();

        blue.append(N - whiteCnt).append('\n');
        white.append(whiteCnt).append('\n');

        for(int i=0; i<N; i++) {
            if(whiteTeam[i]) white.append(i+1).append(' ');
            else blue.append(i+1).append(' ');
        }

        System.out.println(blue);
        System.out.print(white);
    }//print

    
    private static void dfs(int cur, int color) {
        visited[cur] = true;
        if(color == WHITE) {
            whiteTeam[cur] = true;
            whiteCnt++;
        }

        for(int rival : list.get(cur)) {
            if(visited[rival]) continue;
            dfs(rival, (color+1) % 2);
        }
    }//dfs

    
    private static void init() {
        whiteTeam = new boolean[N];
        visited = new boolean[N];
        rivals = new boolean[N][N];
        list = new ArrayList<>(N);
        
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class