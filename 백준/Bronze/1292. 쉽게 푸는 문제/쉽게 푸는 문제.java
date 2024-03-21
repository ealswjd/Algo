import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1292
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        br.close();

        int[] sum = new int[B+1];
        int next = 2;
        for(int i=1, n=1; i<=B; i++) {
            if(i == next) next = ++n + i;
            sum[i] = sum[i-1] + n;
        }

        System.out.print(sum[B] - sum[A-1]);
    }//main

}//class