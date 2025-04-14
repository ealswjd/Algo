import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 100_002;
    private static int N, T; // 참가자 수, 스터디 시간
    private static int[] count; // 참가


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int start = 0;
        int end = T-1;
        long sum = 0;
        long max = 0;

        for(int i=1; i<T; i++) {
            sum += count[i];
        }

        for(int e=T; e<MAX; e++) {
            sum += count[e];
            sum -= count[e-T];

            if(sum > max) {
                max = sum;
                start = e - T;
                end = e;
            }
        }

        System.out.printf("%d %d", start, end);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 참가자 수
        T = Integer.parseInt(st.nextToken()); // 스터디 시간

        count = new int[MAX];

        for(int i=0; i<N; i++) {
            int K = Integer.parseInt(br.readLine());
            while(K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) + 1;
                int e = Integer.parseInt(st.nextToken()) + 1;

                count[s]++;
                count[e]--;
            }
        }

        for(int i=1; i<MAX; i++) {
            count[i] += count[i-1];
        }

        br.close();
    }//init


}//class