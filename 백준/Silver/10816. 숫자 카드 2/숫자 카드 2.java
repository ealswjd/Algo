import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10816
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
        int N = Integer.parseInt(br.readLine()); // 숫자 카드의 개수
        Map<Integer, Integer> count = new HashMap<>();

        // 숫자 카드에 적혀있는 정수가 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // M(1 ≤ M ≤ 500,000)이 주어진다.
        int M = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        // 구해야 할 M개의 정수가 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int target = Integer.parseInt(st.nextToken());
            ans.append(count.getOrDefault(target, 0)).append(' ');
        }

        br.close();
        System.out.print(ans);
    }//main


}//class