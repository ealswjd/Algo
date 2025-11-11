import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10816
public class Main {
    private static final int MAX = 10_000_000;   

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
        int N = Integer.parseInt(br.readLine()); // 숫자 카드의 개수
        int[] count = new int[MAX * 2 + 1];

        // 숫자 카드에 적혀있는 정수가 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken()) + MAX;
            count[num]++;
        }

        // M(1 ≤ M ≤ 500,000)이 주어진다.
        int M = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        // 구해야 할 M개의 정수가 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int target = Integer.parseInt(st.nextToken()) + MAX;
            ans.append(count[target]).append(' ');
        }

        br.close();
        System.out.print(ans);
    }//main


}//class