import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22234
public class Main {
    private static int T; // 업무 시간
    private static int W; // 영업 시간
    private static Queue<Customer> wait; // 대기 줄 손님
    private static PriorityQueue<Customer> after; // 영업 시작 이후 손님


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int curTime = 0; // 현재 시간
        int time;
        Customer customer;

        while(!wait.isEmpty() && curTime < W) {
            customer = wait.poll();
            time = Math.min(customer.time, T);

            // 현재 고객 업무 처리
            while(time-- > 0 && curTime < W) {
                ans.append(customer.id).append('\n');
                curTime++;
            }

            // 나중에 온 손님 대기
            while(!after.isEmpty() && after.peek().c <= curTime) {
                wait.offer(after.poll());
            }

            // 업무 안 끝났으면 다시 대기
            if(customer.time > T) {
                customer.setTime(customer.time - T);
                wait.offer(customer);
            }
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 대기 줄 손님
        T = Integer.parseInt(st.nextToken()); // 업무 시간
        W = Integer.parseInt(st.nextToken()); // 영업 시간

        wait = new LinkedList<>(); // 대기 줄 손님
        after = new PriorityQueue<>(); // 영업 시작 이후 손님

        // 대기 손님
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            wait.offer(new Customer(id, time));
        }

        // 나중에 들어온 손님
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            after.offer(new Customer(id, time, c));
        }

        br.close();
    }//init


    private static class Customer implements Comparable<Customer> {
        int id;
        int time;
        int c;

        public Customer(int id, int time) {
            this.id = id;
            this.time = time;
        }

        public Customer(int id, int time, int c) {
            this(id, time);
            this.c = c;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public int compareTo(Customer o) {
            return this.c - o.c;
        }
    }//Customer


}//class