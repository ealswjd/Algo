import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1300
public class Main {
    private static int N, K; // 크기가 N×N인 배열, 1차원 배열에서 구해야 하는 인덱스 K


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 크기가 N×N인 배열 A
        K = Integer.parseInt(br.readLine()); // 1차원 배열에서 구해야 하는 인덱스
        br.close();

        long ans = getK();
        System.out.print(ans);
    }//sol


    private static long getK() {
        int start = 1;
        int end = K;
        int number = K;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(getCount(mid) >= K) {
                end = mid - 1;
                number = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return number;
    }//getK


    private static long getCount(int mid) {
        long cnt = 0; // mid 보다 작거나 같은 수의 개수

        for(int n=1; n<=N; n++) {
            cnt += Math.min(mid / n, N);
        }

        return cnt;
    }//getCount


}//class