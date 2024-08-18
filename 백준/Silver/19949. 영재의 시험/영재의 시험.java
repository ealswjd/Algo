import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19949
public class Main {
    static final int N = 10, M=5;
    static int cnt; // 5점 이상일 경우의 수
    static int[] correctAnswers; // 정답

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        correctAnswers = new int[N];

        for(int i=0; i<N; i++) {
            correctAnswers[i] = Integer.parseInt(st.nextToken());
        }

        // 문제, 점수, 이전 점수1, 이전 점수2
        backtracking(0, 0, -1, -1);

        System.out.print(cnt);
    }//main

    
    private static void backtracking(int idx, int score, int prev1, int prev2) {
        if(idx == N) { // 문제 다 품
            if(score >= M) cnt++; // 점수 5점 이상

            return;
        }

        for(int i=1; i<=M; i++) {
            if(i == prev1 && i == prev2) continue;

            int sum = score;
            if(correctAnswers[idx] == i) sum++; // 정답이면 +1

            backtracking(idx+1, sum, prev2, i);
        }
    }//backtracking

    
}//class