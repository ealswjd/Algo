import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1722
public class Main {
    static int N;
    static long K;
    static int[] permutation;
    static long[] factorial;
    static List<Integer> numbers;

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        permutation = new int[N];

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        if(num == 1) { // 1인 경우 k(1 ≤ k ≤ N!)를 입력받고
            K = Long.parseLong(st.nextToken());

            // k번째 순열 구하기
            findKthNumber();
        }
        else { // 2인 경우 임의의 순열을 나타내는 N개의 수를 입력받는다

            for(int i=0; i<N; i++) {
                permutation[i] = Integer.parseInt(st.nextToken());
            }

            // 순열이 몇 번째 순열인지
            findPermutationIndex();
        }

    }//main


    // 2. 순열이 몇 번째 순열인지
    private static void findPermutationIndex() {
        long order = 0;

        // 순열 순서 구하기
        for(int i = 0; i < N; i++) {
            int idx = numbers.indexOf(permutation[i]);

            order += idx * factorial[N - 1 - i];
            numbers.remove(idx);
        }

        System.out.print(order + 1);
    }//findPermutationIndex


    // 1. k번째 순열 구하기
    private static void findKthNumber() {
        K--;
        StringBuilder result = new StringBuilder();

        // k번째 순열 구하기
        for(int i = N - 1; i >= 0; i--) {
            int idx = (int)(K / factorial[i]);

            result.append(numbers.get(idx)).append(" ");
            numbers.remove(idx);
            K %= factorial[i];
        }

        System.out.print(result.toString().trim());
    }//findKthNumber


    private static void init() {
        factorial = new long[N + 1];
        numbers = new ArrayList<>();

        // 팩토리얼 미리 계산
        factorial[0] = 1;
        for(int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
    }//init

}//class