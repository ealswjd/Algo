import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15661
public class Main {
    private static int N; // 인원수
    private static int totalScore; // 총 점수
    private static int[] row;
    private static int[] col;
    private static int min; // 능력치의 차이의 최솟값


    public static void main(String[] args) throws IOException {
        init();

        // 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력
        getMin(0, 0, totalScore);
        System.out.print(min);
    }//main


    private static void getMin(int cur, int cnt, int total) {
        if(cur == N || cnt == N / 2) {
            min = Math.min(min, Math.abs(total));
            return;
        }

        getMin(cur + 1, cnt + 1, total - row[cur] - col[cur]);
        getMin(cur + 1, cnt, total);
    }//getMin


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 인원수

        min = Integer.MAX_VALUE; // 능력치의 차이의 최솟값
        row = new int[N];
        col = new int[N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int score = Integer.parseInt(st.nextToken());
                row[i] += score;
                col[j] += score;
                totalScore += score;
            }
        }

        br.close();
    }//init


}//class