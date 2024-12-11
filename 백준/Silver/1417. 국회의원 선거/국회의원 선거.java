import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1417
public class Main {
    private static int N; // 후보의 수
    private static int D; // 다솜이
    private static PriorityQueue<Integer> pq;


    public static void main(String[] args) throws IOException {
        init();

        int minCnt = getMinCnt();
        System.out.print(minCnt);
    }//main

    
    private static int getMinCnt() {
        int cnt = 0;

        while(!pq.isEmpty()) {
            if(pq.peek() < D) break;

            pq.offer(pq.poll() - 1);
            D++;
            cnt++;
        }

        return cnt;
    }//getMinCnt

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 후보의 수
        D = Integer.parseInt(br.readLine()); // 다솜이

        pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=1; i<N; i++) {
            int cnt = Integer.parseInt(br.readLine());
            pq.offer(cnt);
        }

        br.close();
    }//init

    
}//class