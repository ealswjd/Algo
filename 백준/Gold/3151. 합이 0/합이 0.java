import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3151
public class Main {
    private static int N; // 학생의 수
    private static int[] A; // 학생들의 코딩 실력


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        long total = 0;

        for(int i=0; i<N-2; i++) {
            total += getCnt(i);
        }

        System.out.print(total);
    }//sol

    private static long getCnt(int idx) {
        long cnt = 0;
        int left = idx + 1;
        int right = N - 1;

        while(left < right) {
            long sum = A[idx] + A[left] + A[right];

            if(sum < 0) {
                left++;
            } else if(sum > 0) {
                right--;
            } else {
                if(A[left] == A[right]) {
                    cnt += comb(right - left + 1);
                    break;
                } else {
                    long leftVal = A[left];
                    long rightVal = A[right];
                    int leftCnt = 0;
                    int rightCnt = 0;

                    while(left < right && A[left] == leftVal) {
                        left++;
                        leftCnt++;
                    }
                    while(left <= right && A[right] == rightVal) {
                        right--;
                        rightCnt++;
                    }

                    cnt += (long) leftCnt * rightCnt;
                }
            }
        }

        return cnt;
    }//getCnt

    private static long comb(int n) {
        return (long) n * (n - 1) / 2;
    }//comb

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 학생의 수 N이 입력된다.
        N = Integer.parseInt(br.readLine());

        // 그녀가 가르칠 학생들의 코딩 실력인 Ai가 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        br.close();
    }//init


}//class