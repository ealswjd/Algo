import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5671
public class Main {
    private static final int NUM = 10;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        String input;

        while((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int cnt = 0;

            for(int num=N; num<=M; num++) {
                if(isUnique(num)) cnt++;
            }

            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main
    

    private static boolean isUnique(int num) {
        boolean[] used = new boolean[NUM];

        while(num > 0) {
            int d = num % 10;
            if(used[d]) return false;

            used[d] = true;
            num /= 10;
        }

        return true;
    }//isUnique
    

}//class