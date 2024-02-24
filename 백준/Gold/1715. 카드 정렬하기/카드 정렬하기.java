import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    제목 : 카드 정렬하기 (골드 4)
    링크 : https://www.acmicpc.net/problem/1715
 */
public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(N-->0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }//while
        br.close();

        int sum = 0;
        int total = 0;
        while(pq.size() > 1) {
            sum = pq.poll() + pq.poll();
            total += sum;
            if(!pq.isEmpty()) pq.offer(sum);
        }//while

        System.out.print(total);
    }//main

}//class