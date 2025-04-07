import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11568
public class Main {
    private static int N; // 카드의 개수
    private static int[] numbers; // 카드 N개에 들어있는 정수

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[] lis = new int[N+1]; // 증가 순열
        int cnt = 0; // 원소의 최대 개수
        int idx;

        for(int i=0; i<N; i++) {
            if(numbers[i] > lis[cnt]) {
                lis[++cnt] = numbers[i];
                continue;
            }

            idx = getIndex(numbers[i], cnt, lis);
            lis[idx] = numbers[i];
        }

        return cnt;
    }//getMax


    private static int getIndex(int target, int end, int[] lis) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(lis[mid] >= target) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 카드의 개수
        numbers = new int[N]; // 카드 N개에 들어있는 정수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

    
}//class