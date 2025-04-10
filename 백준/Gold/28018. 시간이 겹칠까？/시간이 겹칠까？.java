import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28018
public class Main {
    private static final int MAX = 1_000_002;
    private static int[] count; // 사용중인 좌석 개수
    private static int[] time; // 알고자 하는 특정 시각


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();

        // 누적합
        for(int i=1; i<MAX; i++) {
            count[i] += count[i-1];
        }

        // 특정 시각에 선택할 수 없는 좌석 수를 주어진 순서에 따라 줄마다 출력
        for(int t : time) {
            ans.append(count[t]).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 댓글을 달아준 학생 수

        count = new int[MAX]; // 사용중인 좌석 개수

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 좌석 배정 시각
            int e = Integer.parseInt(st.nextToken()); // 종료 시각

            count[s]++;   // 착석
            count[e+1]--; // 종료 (종료되는 시각에 곧바로 선택될 수 없다.)
        }

        int Q = Integer.parseInt(br.readLine()); // 특정한 시각의 개수
        time = new int[Q]; // 알고자 하는 특정 시각

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

    
}//class