import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1946
public class Main {
    private static int N; // 지원자 수
    private static long[] scores; // 지원자의 서류+면접 등수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            init(br);
            int maxCnt = getCnt();

            ans.append(maxCnt).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getCnt() {
        int cnt = 1;
        int minInterview = (int) scores[0]; // 서류 심사 1등의 면접 순위

        for(int i=1; i<N; i++) {
            int curInterview = (int) scores[i]; // i번 지원자 면접 순위

            // i번 지원자가 면접 잘 봤으면 선발
            if (curInterview < minInterview) {
                cnt++;
                minInterview = curInterview;
            }
        }

        return cnt;
    }//getCnt

    private static void init(BufferedReader br) throws IOException{
        N = Integer.parseInt(br.readLine()); // 지원자의 수
        scores = new long[N]; // 지원자의 서류심사 성적, 면접 성적의 순위

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            long docRank = Integer.parseInt(st.nextToken()); // 서류심사 성적 순위
            int interviewRank = Integer.parseInt(st.nextToken()); // 면접 성적의 순위

            // 서류 기준으로 정렬하도록
            scores[i] = (docRank << 32) | interviewRank;
        }

        // 서류 기준 정렬
        Arrays.sort(scores);
    }//init

}//class