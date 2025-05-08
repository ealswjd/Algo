import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11509
public class Main {
    private static final int MAX = 1_000_002;
    private static int N; // 풍선 개수
    private static int[] count; // 높이별 풍선 개수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int total = 0; // 최소한 필요한 화살의 개수

        for(int h=1; h<MAX; h++) {
            total += count[h];
        }

        System.out.print(total);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 풍선 개수

        count = new int[MAX]; // 높이별 풍선 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h;
        for(int i=0; i<N; i++) {
            h = Integer.parseInt(st.nextToken()); // 풍선 높이

            count[h]++;
            if(count[h+1] > 0) count[h+1]--;
        }

        br.close();
    }//init


}//class