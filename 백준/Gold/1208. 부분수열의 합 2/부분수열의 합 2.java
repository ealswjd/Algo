import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1208
public class Main {
    private static int N, S;
    private static List<Integer> listA, listB;


    public static void main(String[] args) throws IOException {
        init();

        long cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static long getCnt() {
        long cnt = 0;

        for(int sum : listA) {
            int target = S - sum;
            cnt += upperBound(target, listB) - lowerBound(target, listB);
        }

        if(S == 0) cnt--;
        return cnt;
    }//getCnt


    private static int lowerBound(int target, List<Integer> list) {
        int start = 0;
        int end = list.size();
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(list.get(mid) < target) start = mid + 1;
            else end = mid;
        }

        return start;
    }//lowerBound


    private static int upperBound(int target, List<Integer> list) {
        int start = 0;
        int end = list.size();
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(list.get(mid) <= target) start = mid + 1;
            else end = mid;
        }

        return start;
    }//upperBound


    private static List<Integer> getSubsets(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int len = arr.length;

        for(int i=0; i<(1 << len); i++) {
            int sum = 0;
            for(int j=0; j<len; j++) {
                if((i & (1 << j)) != 0) sum += arr[j];
            }
            list.add(sum);
        }

        return list;
    }//getSubsets


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정수의 개수
        S = Integer.parseInt(st.nextToken()); // 원소를 다 더한 값의 목표

        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 나누기
        int mid = N / 2;
        int[] left = Arrays.copyOfRange(numbers, 0, mid);
        int[] right = Arrays.copyOfRange(numbers, mid, N);

        // 부분집합 구하기
        listA = getSubsets(left);
        listB = getSubsets(right);

        Collections.sort(listB);

        br.close();
    }//init


}//class