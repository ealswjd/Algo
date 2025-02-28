import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/30090
public class Main {
    private static final int MAX = 100;
    private static int N; // 문자열의 수
    private static String[] arr; // 문자열
    private static int minCnt; // 백신이 되는 문자열의 길이


    public static void main(String[] args) throws IOException {
        init();

        sol(0, 0, "");
        System.out.print(minCnt);
    }//main


    private static void sol(int cnt, int checked, String result) {
        if(cnt == N) {
            minCnt = Math.min(minCnt, result.length());
            return;
        }

        int idx;
        for(int i=0; i<N; i++) {
            if((checked & 1 << i) != 0) continue;

            idx = getEndIndex(arr[i], result);
            sol(cnt + 1, (checked | 1 << i), result + arr[i].substring(idx));
        }
    }//sol


    private static int getEndIndex(String str, String result) {
        int i = Math.min(str.length(), result.length());

        for(; i>=1; i--) {
            if(result.endsWith(str.substring(0, i))) {
                return i;
            }
        }

        return 0;
    }//getEndIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 문자열의 수

        minCnt = MAX; // 백신이 되는 문자열의 길이
        arr = new String[N]; // 문자열

        for(int i=0; i<N; i++) {
            arr[i] = br.readLine();
        }

        br.close();
    }//init


}//class