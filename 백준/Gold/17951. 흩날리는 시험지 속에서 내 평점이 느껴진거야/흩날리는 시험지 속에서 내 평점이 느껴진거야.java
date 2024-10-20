import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17951
public class Main {
    // 시험지의 개수 N과 시험지를 나눌 그룹의 수 K
    private static int N, K;
    private static int[] score; // 각 시험지 점수
    private static int total; // 총점

    public static void main(String[] args) throws IOException {
        init();
        getScore();
    }//main

    private static void getScore() {
        int start = 0;
        int end = total;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(getCnt(mid) >= K) start = mid + 1;
            else end = mid - 1;
        }

        System.out.print(end);
    }//getScore

    private static int getCnt(int target) {
        int cnt = 0;
        int sum = 0;

        for(int i=0; i<N; i++) {
            sum += score[i];

            if(sum >= target) {
                if(++cnt >= K) return cnt;
                sum = 0;
            }
        }

        return cnt;
    }//getCnt

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 시험지의 개수
        K = Integer.parseInt(st.nextToken()); // 시험지를 나눌 그룹의 수

        score = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            total += score[i];
        }
    }//init

}//class