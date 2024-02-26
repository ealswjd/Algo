import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16953
public class Main {
    static int A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int cnt = getCnt();
        System.out.println(cnt);
    }//main

    private static int getCnt() {
        int cnt = 1;

        while(A != B) {
            if(B < A) return -1;
            if(B % 2 == 0) B /= 2;
            else if((B % 10) == 1) B /= 10;
            else return -1;
            cnt++;
        }//while

        return cnt;
    }//getCnt

}//class