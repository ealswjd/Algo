import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1094
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine()); // 지민이가 갖고 싶은 막대 길이

        br.close();

        int cnt = getCnt(X);
        System.out.println(cnt);
    }//main

    private static int getCnt(int X) {
        int cnt = 0;

        while(X > 0) {
            if((X & 1) != 0) cnt++;
            X = X >> 1;
        }

        return cnt;
    }//getCnt

}//class