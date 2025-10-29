import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.acmicpc.net/problem/2002
public class Main {
    private static int N; // 차의 대수
    private static String[] in, out; // 차 순서

    public static void main(String[] args) throws IOException {
        init();
        int cnt = getCnt();

        System.out.print(cnt);
    }//main
   
    private static int getCnt() {
        Set<String> isOut = new HashSet<>();
        int cnt = 0;
        int inIdx = 0;
        int outIdx = 0;

        while(inIdx < N) {
            if(isOut.contains(in[inIdx])) {
                inIdx++;
                continue;
            }

            if(in[inIdx].equals(out[outIdx])) {
                // 순서대로 나왔음
                isOut.add(in[inIdx]);
                inIdx++;
            } else {
                // 추월 차량임
                isOut.add(out[outIdx]);
                cnt++;
            }

            outIdx++;
        }

        return cnt;
    }//getCnt
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 차의 대수
        in = new String[N]; // 들어간 순서
        out = new String[N]; // 나간 순서

        for(int i=0; i<N*2; i++) {
            int cur = i % N;
            String number = br.readLine();

            if(i < N) {
                in[cur] = number;
            } else {
                out[cur] = number;
            }
        }

        br.close();
    }//init

}//class