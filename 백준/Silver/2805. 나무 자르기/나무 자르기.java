import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2805
public class Main {
    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 나무의 수
        M = Integer.parseInt(st.nextToken()); // 집으로 가져가려고 하는 나무의 길이

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        int end = 0;
        for(int i=0; i<N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, trees[i]);
        }

        int max = getMax(end);
        System.out.print(max);
    }//main

    private static int getMax(int end) {
        int max = 0;
        int start = 0, mid=(start+end)/2;
        long sum;

        while(start < end) {
            mid = (start+end)/2;
            sum = getSum(mid);
            if(sum >= M) max = mid;

            if(sum > M) start = mid+1;
            else if(sum < M ) end = mid;
            else break;
        }//while

        return max;
    }//getMax

    private static long getSum(int mid) {
        long sum = 0;
        for(int tree : trees) {
            if(tree - mid > 0) sum += tree - mid;
        }

        return sum;
    }//getSum

}//class