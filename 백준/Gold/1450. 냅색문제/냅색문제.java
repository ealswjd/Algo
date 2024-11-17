import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1450
public class Main {
    private static int N, C; // 물건 개수, 최대 무게
    private static List<Long> listA, listB;

    
    public static void main(String[] args) throws IOException {
        init();

        long cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static long getCnt() {
        long cnt = 0;

        for(long sum : listA) {
            cnt += upperBound(C - sum, listB);
        }

        return cnt;
    }//getCnt

    
    private static int upperBound(long target, List<Long> list) {
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

    
    private static List<Long> getSubset(int[] arr) {
        List<Long> list = new ArrayList<>();
        int len = arr.length;

        for(int i=0; i<(1 << len); i++) { // 2^n개의 부분 집합
            long sum = 0;
            for(int j=0; j<len; j++) {
                // j번째 원소가 포함된 경우
                if((i & (1 << j)) != 0) sum += arr[j];
            }
            list.add(sum);
        }

        return list;
    }//getSubset

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물건 개수
        C = Integer.parseInt(st.nextToken()); // 최대 무게

        int[] weights = new int[N]; // 물건 무게
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int mid = N / 2;
        int[] left = Arrays.copyOfRange(weights, 0, mid);
        int[] right = Arrays.copyOfRange(weights, mid, N);

        listA = getSubset(left);
        listB = getSubset(right);

        Collections.sort(listB);

        br.close();
    }//init

    
}//class