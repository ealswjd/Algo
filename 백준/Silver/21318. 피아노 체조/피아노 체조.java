import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21318
public class Main {
    static int N; // 악보의 개수
    static int[] difficulty; // 난이도
    static int[] mistake;   // 실수 횟수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            difficulty[i] = Integer.parseInt(st.nextToken());
        }

        prefixSum(); // 실수 횟수 누적합

        StringBuilder ans = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ans.append(mistake[y] - mistake[x]).append('\n');
        }

        System.out.print(ans);
    }//main

    
    private static void prefixSum() {

        for(int i=1; i<N; i++) {
            mistake[i+1] = mistake[i];

            if(difficulty[i] > difficulty[i+1]) {
                mistake[i+1]++;
            }
        }

    }//prefixSum

    
    private static void init() {
        difficulty = new int[N+1];  // 난이도
        mistake = new int[N+1];     // 실수 횟수
    }//init

    
}//class