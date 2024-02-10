import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12904
public class Main {
    static String S, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine(); //.toCharArray();
        T = br.readLine(); //.toCharArray();
        br.close();

        System.out.print(changeSToT());
    }//main

    private static int changeSToT() {
        if(String.valueOf(S).equals(String.valueOf(T))) return 1;

        int sLen = S.length();
        int tLen = T.length();
        int start=0, end = tLen-1, tmp;
        int reverse = 0;

        while(end-start+1 != sLen) {
            if(reverse == 0) { // 정방향
                if(T.charAt(end) == 'B') reverse = 1 - reverse;
                end--;
            }else{ // 반대방향
                if(T.charAt(start) == 'B') reverse = 1 - reverse;
                start++;
            }
        }//while

        if(reverse == 1) {
            StringBuilder s = new StringBuilder();
            for(int i=end; i>=start; i--) s.append(T.charAt(i));
            if(S.equals(String.valueOf(s))) return 1;
        }else {
            if(S.equals(T.substring(start, end+1))) return 1;
        }

        return 0;
    }//changeSToT

}//class