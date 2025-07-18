import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18119
public class Main {
    private static int N; // 단어 개수
    private static int X; // 준석이가 기억하는 알파벳 상태
    private static int[] words; // 사전에 적혀있는 N가지 단어
    private static int[][] Q; // 쿼리


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();

        // 각 쿼리마다 완전히 알고 있는 단어의 개수를 출력
        for(int[] q : Q) {
            executeQuery(q);

            int cnt = getCnt();
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static int getCnt() {
        int cnt = 0; // 완전히 알고 있는 단어의 개수

        for(int word : words) {
            if((word & X) == 0) cnt++;
        }

        return cnt;
    }//getCnt


    private static void executeQuery(int[] q) {
        int o = q[0]; // 명령 종류
        int x = q[1]; // 알파벳

        if(o == 1) X |= (1 << x); // o가 1이면 x를 잊는다      
        else X &= ~(1 << x); // o가 2면 x를 기억해낸다

    }//executeQuery


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 단어 개수
        int M = Integer.parseInt(st.nextToken()); // 쿼리 개수

        words = new int[N]; // 사전에 적혀있는 N가지 단어
        Q = new int[M][2]; // 쿼리

        for(int i=0; i<N; i++) {
            char[] str = br.readLine().toCharArray();
            int word = 0;
            for(char c : str) {
                word |= (1 << (c - 'a'));
            }

            words[i] = word;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // o는 1, 2중 하나이고, x는 알파벳 소문자이다.
            int o = Integer.parseInt(st.nextToken());
            int x = st.nextToken().charAt(0) - 'a';

            Q[i][0] = o;
            Q[i][1] = x;
        }

        br.close();
    }//init
    

}//class