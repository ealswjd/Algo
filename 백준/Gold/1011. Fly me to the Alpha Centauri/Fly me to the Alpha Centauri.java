import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1011
public class Main {

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;


        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int cnt = getCnt(y - x);
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main

    
    private static int getCnt(int total) {
        if(total <= 3) return total;

        int cnt = 0;
        int max = (int) Math.sqrt(total);

        if(max == Math.sqrt(total)) cnt = max * 2 - 1;
        else if(total <= max * max + max) cnt = max * 2;
        else cnt = max * 2 + 1;

        return cnt;
    }//getCnt

    
}//class