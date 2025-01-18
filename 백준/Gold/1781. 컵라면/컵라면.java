import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1781
public class Main {
    private static int N; // 숙제의 개수
    private static PriorityQueue<Task> taskList;


    public static void main(String[] args) throws IOException {
        init();

        int total = getTotal();
        System.out.print(total);
    }//main


    private static int getTotal() {
        int total = 0;
        boolean[] success = new boolean[N + 1];
        PriorityQueue<Task> temp = new PriorityQueue<>((o1, o2) -> o1.reward - o2.reward);
        Task task;
        int deadline, reward;

        for(int time=1; time<=N; time++) {
            while(!taskList.isEmpty() && taskList.peek().deadline == time) {
                task = taskList.poll();

                if(!success[time]) {
                    success[time] = true;
                    total += task.reward;
                    temp.add(task);
                }
                else if(task.reward > temp.peek().reward) {
                    total -= temp.poll().reward;
                    total += task.reward;
                    temp.add(task);
                }
            }

            if(!taskList.isEmpty() && !success[time]) {
                task = taskList.poll();
                total += task.reward;
                temp.add(task);
            }
        }

        return total;
    }//getTotal


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 숙제의 개수

        taskList = new PriorityQueue<>();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());

            taskList.add(new Task(deadline, reward));
        }

        br.close();
    }//init


    private static class Task implements Comparable<Task> {
        int deadline;
        int reward;

        public Task(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }

        @Override
        public int compareTo(Task o) {
            return this.deadline - o.deadline;
        }
    }//Task


}//class