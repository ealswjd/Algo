import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1655
public class Main {
    static int N;
    static PriorityQueue<Integer> minHeap, maxHeap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 백준이가 외치는 정수의 개수
        minHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap = new PriorityQueue<>();

        StringBuilder ans = new StringBuilder();

        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            ans.append(getMidValue(n)).append('\n');
        }//for

        System.out.print(ans);
    }//main

    private static int getMidValue(int n) {
        if(minHeap.isEmpty()) {
            minHeap.offer(n);

            return n;
            
        }else if(maxHeap.isEmpty()) {
            if(minHeap.peek() > n) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(n);
            }else maxHeap.offer(n);

            return minHeap.peek();
            
        }

        int min = minHeap.peek();
        int max = maxHeap.peek();

        if(max < n) {
            if(minHeap.size() <= maxHeap.size()) {
                maxHeap.poll();
                minHeap.offer(max);
            }

            maxHeap.offer(n);

        }else if(min <= n && n <= max) {
            if(minHeap.size() > maxHeap.size()) maxHeap.offer(n);
            else minHeap.offer(n);

        }else {
            if(minHeap.size() > maxHeap.size()) {
                minHeap.poll();
                maxHeap.offer(min);
            }
            minHeap.offer(n);

        }

        return minHeap.peek();
    }//getMidValue


}//class