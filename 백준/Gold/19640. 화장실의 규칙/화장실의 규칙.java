import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/19640
public class Main {
    static int N, M, K;
    static List<List<Employee>> lines;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사원의 수
        M = Integer.parseInt(st.nextToken()); // 줄의 수
        K = Integer.parseInt(st.nextToken()); // 앞에 서 있던 사원의 수

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken()); // 근무 일수
            int H = Integer.parseInt(st.nextToken()); // 급한 정도

            lines.get(i%M).add(new Employee(i%M, i, D, H));
        }

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        PriorityQueue<Employee> pq = new PriorityQueue<>();
        int cnt = 0;
        Employee employee;

        // 우선순위큐에 선두 담기
        for(int i=0; i<M; i++) {
            if(lines.get(i).isEmpty()) break;
            pq.offer(lines.get(i).remove(0));
        }

        int lineNum;
        while(!pq.isEmpty()) {
            employee = pq.poll();
            if(employee.num == K) break; // 데카 차례

            lineNum = employee.lineNum; // 줄 번호
            cnt++;

            if(lines.get(lineNum).isEmpty()) continue;

            pq.offer(lines.get(lineNum).remove(0)); // 새로운 선두
        }

        return cnt;
    }//getCnt

    
    private static void init() {
        lines = new ArrayList<>();

        for(int i=0; i<M; i++) {
            lines.add(new LinkedList<>());
        }
    }//init

    
    static class Employee implements Comparable<Employee> {
        int lineNum; // 줄 번호
        int num; // 번호
        int D; // 근무 일수
        int H; // 급한 정도

        public Employee(int lineNum, int num, int D, int H) {
            this.lineNum = lineNum;
            this.num = num;
            this.D = D;
            this.H = H;
        }

        // 근무 일수 같으면 급한 정도, 둘 다 같으면 줄 번호 낮은 순
        @Override
        public int compareTo(Employee e) {
            if(this.D == e.D) {
                if(this.H == e.H) return this.lineNum - e.lineNum;
                return e.H - this.H;
            }

            return e.D - this.D;
        }

    }//Employee

    
}//class