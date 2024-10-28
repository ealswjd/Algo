import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1062
public class Main {
    private static final int M = 5, TOTAL = 26;
    private static final char[] C = {'a', 'n', 't', 'i', 'c'};
    private static int N, K;
    private static int[] words;

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        if(K < M) return 0;
        else if(K >= TOTAL) return N;

        int used = 0;
        for(char c : C) {
            used |= 1 << (c - 'a');
        }

        return comb(1, 0, 0, used);
    }//getMax

    
    private static int comb(int cur, int cnt, int max, int used) {
        if(cur > TOTAL) return max;
        if(cnt == K - M) return Math.max(max, getCnt(used));

        max = comb(cur+1, cnt, max, used);

        if((used & 1 << cur) == 0) {
            max = comb(cur+1, cnt+1, max, (used | 1 << cur));
        }

        return max;
    }//comb

    
    private static int getCnt(int used) {
        int cnt = 0;

        for(int word : words) {
            if((word & used) == word) cnt++;
        }

        return cnt;
    }//getCnt

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 단어 개수
        K = Integer.parseInt(st.nextToken()); // 가르칠 수 있는 글자 개수

        words = new int[N];

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            int word = 0;

            for(char c : tmp) {
                word |= 1 << (c - 'a');
            }

            words[i] = word;
        }

        br.close();
    }//init

    
}//class