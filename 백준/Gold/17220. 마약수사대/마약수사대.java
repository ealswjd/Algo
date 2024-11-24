import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17220
public class Main {
    private static int N; // 마약 공급책의 수
    private static int[] prev; // 이전 공급책 수
    private static List<List<Integer>> list;
    private static boolean[] visited;


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        int cnt = 0;

        for(int i=0; i<N; i++) {
            if(prev[i] == 0 && !visited[i]) {
                cnt += dfs(i, 0);
            }
        }

        return cnt;
    }//getCnt


    private static int dfs(int cur, int cnt) {
        if(list.get(cur).isEmpty()) return cnt;

        int sum = 0;
        for(int next : list.get(cur)) {
            if(visited[next]) continue;

            visited[next] = true;
            sum += dfs(next, cnt) + 1;
        }

        return sum;
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 마약 공급책의 수 N과 마약 공급책의 관계 수 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        prev = new int[N]; // 이전 공급책 수
        visited = new boolean[N];
        list = new ArrayList<>(N);

        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }

        // 각 마약 공급책의 관계가 주어진다. (A B : A -> B)
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';

            list.get(a).add(b);
            prev[b]++;
        }

        // 소재를 파악하고 있는 마약 공급책들의 수와 각 마약 공급책이 주어진다.
        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        for(int i=0; i<cnt; i++) {
            int n = st.nextToken().charAt(0) - 'A';
            visited[n] = true;
        }

        br.close();
    }//init


}//class