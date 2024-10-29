import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/26607
 팀의 능력치를 최대화하게 지원자들을 선발하려 할 때 그때 그 팀의 능력치를 출력하라.
 */
public class Main {
    private static int N, K, X; // 사람의 수 N, 뽑을 인원 K, 힘과 스피드 수치의 합 X
    private static int[] P; // 힘 능력치

    
    public static void main(String[] args) throws IOException {
        init();

        int power = calculatePower();
        System.out.print(power);
    }//main

    
    private static int calculatePower() {       
        int maxPower = K * X;
        boolean[][] dp = new boolean[K + 1][maxPower + 1];
        dp[0][0] = true;

        for(int power : P) {
            for(int k=K; k>0; k--) {
                for(int p=maxPower; p>=power; p--) {
                    dp[k][p] |= dp[k - 1][p - power];
                }
            }
        }

        
        int max = 0; // 팀의 능력치 최댓값
        int total;   // 종합 능력치
        
        for(int p=1; p<=maxPower; p++) {
            if(!dp[K][p]) continue;
            
            // 종합 능력치 = 힘 * 스피드
            total = p * (maxPower - p);
            max = Math.max(max, total);
        }

        return max;
    }//calculatePower

    
    private static void init() throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사람의 수
        K = Integer.parseInt(st.nextToken()); // 뽑을 인원
        X = Integer.parseInt(st.nextToken()); // 힘과 스피드 수치의 합

        P = new int[N]; // 힘 능력치
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            P[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class