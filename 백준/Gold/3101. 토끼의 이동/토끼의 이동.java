import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3101
public class Main {
    private static long N;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행렬의 크기
        int K = Integer.parseInt(st.nextToken()); // 토끼가 점프한 횟수

        int r = 0;
        int c = 0;
        long sum = 1;
        char[] jump = br.readLine().toCharArray();

        for(int i=0; i<K; i++) {
            if(jump[i] == 'U') r--;
            else if(jump[i] == 'D') r++;
            else if(jump[i] == 'L') c--;
            else c++;

            long val = getValue(r, c);
            sum += val;
        }

        System.out.print(sum);
    }//main


    private static long getValue(int r, int c) {
        long n = r + c;
        long value;

        if(n < N) {
            if(n % 2 == 0) value = 1 + n * (n + 1) / 2 + c;
            else value = 1 + n * (n + 1) / 2 + r;
        }
        else {
            if(n % 2 == 0) {
                value = N * N - ((N - 1) * 2 - n) * ((N - 1) * 2 + 1 - n) / 2 - (N - 1) + c;
            }
            else {
                value = N * N - ((N - 1) * 2 - n) * ((N - 1) * 2 + 1 - n) / 2 - (N - 1) + r;
            }
        }

        return value;
    }//getValue


}//class