import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1946
public class Main {
    static int N;
    static int[][] score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            score = new int[N][2];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                score[i][0] = a;
                score[i][1] = b;
            }//for

            ans.append(getCnt()).append('\n');
        }//while
        br.close();

        System.out.print(ans);
    }//main

    private static int getCnt() {
        Arrays.sort(score, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int cnt = 1, max = score[0][1];
        for(int i=1; i<N; i++) {
            if(score[i][1] > max) continue;
            cnt++;
            max = score[i][1];
        }//for

        return cnt;
    }//getCnt

}//class