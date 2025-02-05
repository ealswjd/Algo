import java.io.*;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/3020
 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지 구하는 프로그램
 */
public class Main {
    private static final int MAX = 200_000;
    private static int N; // 동굴의 길이
    private static int H; // 동굴의 높이
    private static int[] top; // 종유석
    private static int[] bottom; // 석순


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main


    private static void solution() {
        int[] count = new int[H]; // 구간별 장애물 개수
        int min = MAX; // 파괴해야하는 장애물의 최솟값
        int cnt = 0;   // 그러한 구간 개수

        for(int i=1, j=0; i<=H; i++, j++) {
            count[j] = bottom[i] + top[H - i + 1];

            if(min > count[j]) {
                min = count[j];
                cnt = 1;
            }
            else if(min == count[j]) {
                cnt++;
            }
        }

        System.out.printf("%d %d", min, cnt);
    }//solution


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 동굴의 길이
        H = Integer.parseInt(st.nextToken()); // 동굴의 높이

        top = new int[H + 1]; // 종유석
        bottom = new int[H + 1]; // 석순

        for(int i=1; i<=N; i++) {
            int h = Integer.parseInt(br.readLine());

            // 석순
            if(i % 2 == 1) {
                bottom[1]++;
                bottom[h + 1]--;
            }
            // 종유석
            else {
                top[1]++;
                top[h + 1]--;
            }
        }

        for(int i=1; i<=H; i++) {
            top[i] += top[i-1];
            bottom[i] += bottom[i-1];
        }

        br.close();
    }//init


}//class