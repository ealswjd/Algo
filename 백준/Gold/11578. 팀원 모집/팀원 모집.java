import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11578
public class Main {
    private static final int MAX = 11; // N과 M은 1이상 10이하의 자연수
    private static int N, M; // 문제의 수 N, 학생들의 수 M
    private static int minCnt, complete; // 가장 적은 팀원의 수, 모든 문제 해결
    private static int[] students; // i번 학생이 풀 수 있는 문제 비트마스크


    public static void main(String[] args) throws IOException{
        init();
        comb(0, 0, 0);

        System.out.print(minCnt == MAX ? -1 : minCnt);
    }//main


    private static void comb(int cur, int cnt, int result) {
        if(cur == M) {
            // 모든 문제를 풀 수 있음
            if(result == complete) {
                minCnt = Math.min(minCnt, cnt);
            }
            return;
        }

        // 현재 학생 선택 o
        comb(cur + 1, cnt + 1, result | students[cur]);
        // 현재 학생 선택 x
        comb(cur + 1, cnt, result);
    }//comb


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 문제의 수
        M = Integer.parseInt(st.nextToken()); // 학생들의 수

        minCnt = MAX; // 가장 적은 팀원의 수
        complete = (1 << N) - 1; // 모든 문제 해결 비트마스크
        students = new int[M]; // i번 학생이 풀 수 있는 문제 비트마스크

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken()); // 문제의 개수

            while(cnt-- > 0) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                students[i] |= (1 << p);
            }
        }

        br.close();
    }//init


}//class