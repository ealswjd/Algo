import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15922
public class Main {
    private static final int MAX = 1_000_000_000;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 선분 개수
        int length = 0; // 선분 길이의 총합
        int start = -MAX;
        int end = MAX;
        int maxEnd = start;

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x > maxEnd) {
                length += Math.abs(maxEnd - start);
                start = x;
                maxEnd = y;
                continue;
            }

            start = Math.min(start, x);
            maxEnd = Math.max(maxEnd, y);
        }

        length += Math.abs(maxEnd - start);
        System.out.print(length);
    }//main

    
}//class