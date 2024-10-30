import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11062
public class Main {
    private static final int USER = 1; // 근우
    private static int N; // 카드의 개수
    private static int[] cards; // 카드 숫자
    private static int[][] dp; // 점수 저장

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            init(br);

            int score = getScore(0, N-1, USER);
            ans.append(score).append('\n');
        }

        System.out.print(ans);
    }//main

    
    private static int getScore(int L, int R, int user) {
        if(L > R) return 0;
        if(dp[L][R] != 0) return dp[L][R];

        int left = getScore(L+1, R, user^3);
        int right = getScore(L, R-1, user^3);

        if(user == USER) dp[L][R] = Math.max(left + cards[L], right + cards[R]); // 근우
        else dp[L][R] = Math.min(left, right); // 명우

        return dp[L][R];
    }//getScore

    
    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine()); // 카드의 개수
        cards = new int[N]; // 카드 숫자
        dp = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

    }//init

    
}//class