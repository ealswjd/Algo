import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2473
public class Main {
    static final long INF = 3_000_000_000L;
    static int N; // 용액의 수
    static long[] values; // 용액의 특성값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 용액의 수

        values = new long[N]; // 용액의 특성값
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }

        find();
    }//main

    private static void find() {
        Arrays.sort(values);

        int mid, end;
        long one = 0;
        long two = 0;
        long three = 0;
        long min = INF+1;

        // start 고정, mid end만 조정
        for(int start=0; start<N-2; start++) {
            mid = start + 1;
            end = N-1;

            while(mid < end) {
                long sum = values[start] + values[mid] + values[end];

                if(min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    one = values[start];
                    two = values[mid];
                    three = values[end];
                }
                if(sum < 0) mid++;
                else end--;
            }
        }

        // 세 용액 특성값의 오름차순으로 출력
        System.out.print(one + " " + two + " " + three);
    }//find

}//class