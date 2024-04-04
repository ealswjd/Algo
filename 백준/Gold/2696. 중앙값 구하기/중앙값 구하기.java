import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2696
public class Main {
    static int M, N;
    static PriorityQueue<Integer> minHeap, maxHeap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int val, mid;
        
        while(T-->0) {
            M = Integer.parseInt(br.readLine());
            init();

            ans.append(N).append('\n');

            st = new StringTokenizer(br.readLine());
            for(int i=0, cnt=0; i<M; i++) {
                if(i != 0 && i%10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }

                val = Integer.parseInt(st.nextToken());
                mid = getMid(val);

                if((i+1) % 2 != 0) {
                    cnt++;
                    ans.append(mid).append(' ');
                    
                    if(cnt % 10 == 0) ans.append('\n');
                }
            }

            ans.append('\n');
        }//while

        System.out.print(ans);
    }//main


    private static int getMid(int n) {
        if(minHeap.isEmpty()) {
            minHeap.offer(n);

            return n;
        }else if(maxHeap.isEmpty()) {
            if(minHeap.peek() <= n) maxHeap.offer(n);
            else {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(n);
            }

            return minHeap.peek();
        }

        int min = minHeap.peek();
        int max = maxHeap.peek();

        if(max < n) {
            if(minHeap.size() <= maxHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }

            maxHeap.offer(n);

        }else if(min <= n && n <= max) {
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(n);
            }else{
                minHeap.offer(n);
            }

        }else {
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            minHeap.offer(n);
        }

        return minHeap.peek();
    }//getMid

    
    private static void init() {
        N = M/2 + M%2;
        minHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap = new PriorityQueue<>();
    }//init

}//class