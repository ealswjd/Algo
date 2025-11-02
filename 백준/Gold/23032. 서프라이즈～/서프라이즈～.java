import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23032
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N; // 학생 수
    private static int[] W; // 스테이크의 무게 누적합


    public static void main(String[] args) throws IOException {
        init();
        int sum = getSum();

        System.out.print(sum);
    }//main
    

    private static int getSum() {
        int result = 0;
        int minDiff = MAX;
        int diff, sum, leftSum, rightSum;

        // 임의로 대상 학생들을 두 그룹으로 나눈다
        for(int mid=1; mid<N; mid++) {
            int start = mid;
            int end = mid + 1;

            while(start >= 1 && end <= N) {
                leftSum = W[mid] - W[start-1]; // mid를 기준으로 왼쪽 그룹
                rightSum = W[end] - W[mid];    // 오른쪽 그룹
                
                sum = leftSum + rightSum; // 두 그룹의 무게 합
                diff = Math.abs(leftSum - rightSum); // 두 그룹의 스테이크 무게 합의 차

                if(diff < minDiff) {
                    // 무게 합의 차가 최솟값인 두 그룹의 학생들에게 스테이크를 줌
                    minDiff = diff;
                    result = sum;
                } else if(diff == minDiff && result < sum) {
                    // 최솟값이 같은 경우가 여러 가지라면 두 그룹의 무게 합이 가장 큰 두 그룹
                    result = sum;
                }

                if(leftSum < rightSum) {
                    start--; // 왼쪽 그룹 늘림
                } else {
                    end++; // 오른쪽 그룹 늘림
                }
            }
        }

        return result;
    }//getSum
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 학생수
        W = new int[N+1]; // 스테이크의 무게 누적합

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            W[i] = Integer.parseInt(st.nextToken()) + W[i-1];
        }

        br.close();
    }//init
    

}//class