import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4803
public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[][] visited;
    static boolean[] checked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int caseNum = 1;
        int cnt;

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N==0 && M==0) break;

            init();

            while(M-->0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                list.get(b).add(a);
            }

            ans.append(String.format("Case %d: ", caseNum++));
            cnt = 0;

            for(int i=1; i<=N; i++) {
                if(checked[i]) continue;
                if(isTree(i, 1, 0)) cnt++;
            }

            if(cnt == 0) ans.append("No trees.");
            else if(cnt == 1) ans.append("There is one tree.");
            else ans.append(String.format("A forest of %d trees.", cnt));

            ans.append('\n');
        }//while

        System.out.print(ans);
    }//main

    private static boolean isTree(int cur, int v, int e) {
        checked[cur] = true;
        if(list.get(cur).isEmpty()){
            return v == e + 1;
        }

        boolean flag = true;
        for(int next : list.get(cur)) {
            if(visited[next][cur]) continue;
            if(checked[next]) {
                flag = false;
                break;
            }
            visited[cur][next] = true;
            flag = isTree(next, v+1, e+1);
            if(!flag) break;
        }

        return flag;
    }

    private static void init() {
        visited = new boolean[N+1][N+1];
        checked = new boolean[N+1];
        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class