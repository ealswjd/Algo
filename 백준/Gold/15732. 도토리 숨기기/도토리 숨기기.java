import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15732
public class Main {
    private static final int A = 0, B = 1, C = 2;
    private static int N; // 상자의 개수
    private static int D; // 도토리의 개수
    private static int[][] rules; // 규칙


    public static void main(String[] args) throws IOException {
        init();

        int box = getBoxNumber();
        System.out.print(box);
    }//main


    private static int getBoxNumber() {
        int start = 1;
        int end = N;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(getCnt(mid) < D) start = mid + 1;
            else end = mid - 1;
        }

        return start;
    }//getBoxNumber


    private static long getCnt(int mid) {
        long cnt = 0;

        for(int[] rule : rules) {
            if(rule[A] <= mid) {
                cnt += (Math.min(mid, rule[B]) - rule[A]) / rule[C] + 1;
            }
        }

        return cnt;
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 상자의 개수
        int K = Integer.parseInt(st.nextToken()); // 규칙의 개수
        D = Integer.parseInt(st.nextToken()); // 도토리의 개수

        rules = new int[K][3];

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            // A번 상자부터 B번 상자까지 C개 간격으로 도토리를 하나씩 넣는 규칙
            rules[i][A] = Integer.parseInt(st.nextToken());
            rules[i][B] = Integer.parseInt(st.nextToken());
            rules[i][C] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class