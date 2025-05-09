import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1263
public class Main {
    private static int N; // 일의 수
    private static Job[] jobs; // 하루에 해야 할 일


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        Arrays.sort(jobs);

        int start = jobs[0].deadLine; // 최대한 늦게 일을 시작할 수 있는 시간

        for(int i=0; i<N; i++) {
            if(jobs[i].deadLine < start) {
                start = jobs[i].deadLine;
            }

            start -= jobs[i].time;
        }

        System.out.print(Math.max(start, -1));
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 일의 수

        jobs = new Job[N]; // 하루에 해야 할 일

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // i번째 일을 처리하는데 T 시간이 걸리고 S 시 내에 처리
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            jobs[i] = new Job(t, s);
        }

        br.close();
    }//init


    private static class Job implements Comparable<Job> {
        int time;
        int deadLine;
        Job(int time, int deadLine) {
            this.time = time;
            this.deadLine = deadLine;
        }

        @Override
        public int compareTo(Job o) {
            return o.deadLine - this.deadLine;
        }
    }//Job
    

}//class