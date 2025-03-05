import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25795
public class Main {
    private static final int MOD = 100_000;
    private static int N; // 화이트, 다크 초콜릿 개수
    private static int a, w, d; // 특정한 정수 a에서 시작, 화이트 w 더하기, 다크 d 곱하기
    private static int max; // 최고점수
    private static boolean[][][] visited; // 방문체크


    public static void main(String[] args) throws IOException {
        init();
        findMax(0, 0, a);

        System.out.print(max);
    }//main


    private static void findMax(int white, int dark, long score) {
        int val = (int) score;
        if(visited[white][dark][val]) return;

        visited[white][dark][val] = true;

        if(dark == N) {
            max = Math.max(max, val);
            return;
        }

        // 화이트
        if(white < N) {
            long nextScore = (score + w) % MOD;
            findMax(white + 1, dark, nextScore);
        }
        // 다크
        if(white > dark) {
            long nextScore = (score * d) % MOD;
            findMax(white, dark + 1, nextScore);
        }
    }//findMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 화이트, 다크 초콜릿 개수
        a = Integer.parseInt(st.nextToken()); // 특정한 정수 a에서 시작
        w = Integer.parseInt(st.nextToken()); // 화이트 초콜릿 더하기
        d = Integer.parseInt(st.nextToken()); // 다크 초콜릿 곱하기

        visited = new boolean[N+1][N+1][MOD];

        br.close();
    }//init


}//class