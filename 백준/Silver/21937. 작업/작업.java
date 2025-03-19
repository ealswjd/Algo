import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21937
public class Main {
    private static int N, X; // 작업할 개수, 오늘 반드시 끝내야하는 작업
    private static List<List<Integer>> list; // 작업 순서 정보
    private static boolean[] visited; // 작업 완료 체크


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt(X);
        System.out.print(cnt);
    }//main


    private static int getCnt(int cur) {
        visited[cur] = true;
        if(list.get(cur).isEmpty()) return 0;

        int cnt = 0;
        for(int next : list.get(cur)) {
            if(visited[next]) continue;
            cnt += getCnt(next) + 1;
        }

        return cnt;
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 작업할 개수
        int M = Integer.parseInt(st.nextToken()); // 작업 순서 정보의 개수

        list = new ArrayList<>(N+1); // 작업 순서 정보
        visited = new boolean[N+1]; // 작업 완료 체크

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 작업 b를 하기 위해서 바로 이전에 작업 a를 먼저 해야한다는 의미
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(b).add(a);
        }

        X = Integer.parseInt(br.readLine()); // 오늘 반드시 끝내야하는 작업

        br.close();
    }//init


}//class