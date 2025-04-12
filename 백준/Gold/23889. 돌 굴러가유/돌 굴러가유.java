import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23889
public class Main {
    private static int N, M, K; // 마을의 개수 N, 가지고 있는 벽의 개수 M, 돌의 개수 K
    private static int[] count; // 모래성 개수
    private static int[] position; // 돌이 굴러가기 시작하는 마을의 위치들


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        boolean[] isWall = new boolean[N+1];
        List<int[]> list = new ArrayList<>();
        int cnt, cur, next;

        for(int i=0; i<K; i++) {
            cur = position[i];
            next = position[i+1];
            cnt = count[next-1] - count[cur-1];

            list.add(new int[] {cur, cnt});
        }

        list.sort((o1, o2) -> o2[1] - o1[1]);

        for(int i=0; i<M; i++) {
            int num = list.get(i)[0];
            isWall[num] = true;
        }

        StringBuilder ans = new StringBuilder();
        int m = 0;
        for(int i=1; i<=N && m < M; i++) {
            if(isWall[i]) {
                ans.append(i).append('\n');
                m++;
            }
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 마을의 개수 N, 가지고 있는 벽의 개수 M, 돌의 개수 K
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        count = new int[N+1];  // 모래성 개수
        position = new int[K+1]; // 돌이 굴러가기 시작하는 마을의 위치들

        // i번째 마을의 모래성의 개수가 공백으로 구분되어 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        // 돌이 굴러가기 시작하는 마을의 위치들이 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            position[i] = Integer.parseInt(st.nextToken());
        }
        position[K] = N+1;

        for(int i=1; i<=N; i++) {
            count[i] += count[i-1];
        }

        br.close();
    }//init


}//class