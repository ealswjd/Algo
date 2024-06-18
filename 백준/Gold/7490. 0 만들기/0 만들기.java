import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/7490
public class Main {
    static final int EMPTY = ' ', PLUS = '+', MINUS = '-';
    static int N, M;
    static StringBuilder ans;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        ans = new StringBuilder();
        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            M = N+N-1;
            result = new int[M];
            result[M-1] = N;

            getResult(1, 0);
            ans.append('\n');
        }

        System.out.print(ans);
    }//main

    private static void appendResult() {
        for(int i=0; i<M; i++) {
            if(i%2 == 0) ans.append(result[i]);
            else ans.append((char) result[i]);
        }
        ans.append('\n');
    }//appendResult

    private static void getResult(int cur, int idx) {
        if(cur == N) {
            if(getSum() == 0) {
                appendResult();
            }

            return;
        }

        result[idx] = cur;

        result[idx+1] = ' ';
        getResult(cur+1, idx+2);

        result[idx+1] = '+';
        getResult(cur+1, idx+2);

        result[idx+1] = '-';
        getResult(cur+1, idx+2);

    }//getResult

    private static int getSum() {
        int sum = 0;
        int prevOp = '.';
        int nextOp = '.';
        int tmp = 0;

        for(int i=0; i<M-1; i += 2) {

            if(result[i+1] == EMPTY) {
                while(i<M-1 && result[i+1] == ' ') {
                    tmp = (tmp + result[i]) * 10;
                    i += 2;
                }

                nextOp = i+1 < M ? result[i+1] : '.';
            }
            else nextOp = result[i+1];

            tmp += result[i];
            if(prevOp == MINUS) sum -= tmp;
            else sum += tmp;

            tmp = 0;
            prevOp = nextOp;
        }

        if(prevOp == PLUS) sum += N;
        else if(prevOp == MINUS) sum -= N;

        return sum;
    }//getSum

}//class