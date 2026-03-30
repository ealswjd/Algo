import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16198
public class Main {
    private static int N; // 구슬의 개수
    private static int max; // 모을 수 있는 에너지의 최댓값
    private static int[] w; // 구슬의 무게
    private static boolean[] used; // 구슬 사용 여부

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0);

        System.out.print(max);
    }//main

    private static void dfs(int cnt, int total) {
        if (cnt == N-2) {
            max = Math.max(max, total);
            return;
        }

        for(int i=1; i<N-1; i++) {
            if (used[i]) continue;

            used[i] = true;
            int prev = getPrev(i);
            int next = getNext(i);
            dfs(cnt+1, total + (prev * next));
            used[i] = false;
        }
    }//dfs

    private static int getPrev(int x) {
        int prev = 0;

        for(int i=x-1; i>=0; i--) {
            if (!used[i]) {
                prev = w[i];
                break;
            }
        }

        return prev;
    }//getPrev

    private static int getNext(int x) {
        int next = 0;

        for(int i=x+1; i<N; i++) {
            if (!used[i]) {
                next = w[i];
                break;
            }
        }

        return next;
    }//getNext

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 구슬의 개수

        w = new int[N]; // 구슬의 무게
        used = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

}//class