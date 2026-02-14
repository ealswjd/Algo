import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/5052
public class Main {
    private static int N;
    private static String[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            init(br);
            ans.append(getResult()).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static String getResult() {
        int cur = 0;

        while(cur < N-1) {
            if (numbers[cur+1].startsWith(numbers[cur])) {
                return "NO";
            }
            cur++;
        }

        return "YES";
    }//getResult

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new String[N];

        for(int i=0; i<N; i++) {
            numbers[i] = br.readLine();
        }

        Arrays.sort(numbers);
    }//init

}//class