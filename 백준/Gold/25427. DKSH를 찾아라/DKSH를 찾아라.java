import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 https://www.acmicpc.net/problem/25427
 DKSH를 찾아라
 */
public class Main {
    private static final int D = 1, K = 2, S = 3, H = 4;
    private static int N; // 알파벳 개수
    private static int[] str; // N개의 알파벳 대문자가 써있는 종이


    public static void main(String[] args) throws IOException {
        init();

        long cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static long getCnt() {
        long[] count = new long[H+1];
        int cur;

        for(int i=0; i<N; i++) {
            cur = str[i]; // 현재 알파벳
            if(cur <= D) {
                if(cur == D) count[cur]++;
                continue;
            }

            count[cur] += count[cur-1];
        }

        return count[H];
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 알파벳 개수
        str = new int[N];
        Map<Character, Integer> dksh = new HashMap<>();

        dksh.put('D', D);
        dksh.put('K', K);
        dksh.put('S', S);
        dksh.put('H', H);

        char[] input = br.readLine().toCharArray();
        for(int i=0; i<N; i++) {
            if(!dksh.containsKey(input[i])) continue;
            str[i] = dksh.get(input[i]);
        }

        br.close();
    }//init


}//class