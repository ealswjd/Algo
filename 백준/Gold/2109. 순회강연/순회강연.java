import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2109
public class Main {
    static final int P = 0, D = 1;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 대학 개수

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[P] == o2[P]) return o1[D] - o2[D];
            return o2[P] - o1[P];
        });

        StringTokenizer st;
        int maxDay = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int pay = Integer.parseInt(st.nextToken()); // 강연료
            int day = Integer.parseInt(st.nextToken()); // 강연일

            pq.offer(new int[] {pay, day});
            maxDay = Math.max(maxDay, day);
        }

        int total = getTotal(maxDay, pq);
        System.out.print(total);
    }//main

    
    private static int getTotal(int maxDay, PriorityQueue<int[]> pq) {
        int sum = 0;
        int day, pay;
        int[] cur;
        boolean[] checked = new boolean[maxDay+1];

        while(!pq.isEmpty()) {
            cur = pq.poll();
            pay = cur[P];
            day = cur[D];

            for(int d=day; d>0; d--) {
                if(!checked[d]) {
                    sum += pay;
                    checked[d] = true;
                    
                    break;
                }
            }
        }

        return sum;
    }//getTotal

    
}//class