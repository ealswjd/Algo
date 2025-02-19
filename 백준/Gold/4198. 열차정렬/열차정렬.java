import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/4198
public class Main {
    private static int N; // 차량의 수
    private static int[] weights; // 차량의 무게


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int max = 0;
        int[] lis, lds;
        int lisCnt, ldsCnt;
        int cur;

        for(int i=0; i<N; i++) {
            cur = weights[i]; // 현재 들어오는 차량
            lisCnt = ldsCnt = 1;
            lis = new int[N+1]; // 증가
            lds = new int[N+1]; // 감소

            lis[0] = cur;
            lds[0] = -cur;

            for(int j=i+1; j<N; j++) {
                int weight = weights[j];

                // 현재 차량보다 무거울 경우
                if(weight > cur) {
                    lisCnt = getCnt(weight, lisCnt, lis);
                }
                // 현재 차량보다 가벼울 경우
                else {
                    ldsCnt = getCnt(-weight, ldsCnt, lds);
                }
            }

            // 가장 긴 열차의 길이 갱신
            max = Math.max(max, lisCnt + ldsCnt - 1);
        }

        return max;
    }//getMax


    private static int getCnt(int cur, int cnt, int[] arr) {
        int idx = lowerBound(cur, cnt, arr);
        arr[idx] = cur;

        return idx == cnt ? cnt+1 : cnt;
    }//getCnt


    private static int lowerBound(int target, int end, int[] arr) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(arr[mid] >= target) end = mid;
            else start = mid + 1;
        }

        return end;
    }//lowerBound


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 차량의 수

        weights = new int[N]; // 차량의 무게

        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init


}//class