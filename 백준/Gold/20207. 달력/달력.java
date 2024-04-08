import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 달력 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/20207
 * */
public class Main {
    static final int S=0, E=1, D=365;
    static int N;
    static int[] calendar;
    static PriorityQueue<int[]> schedule;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 일정의 개수

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            schedule.offer(new int[] {s, e});
        }
        br.close();

        int area = getArea();
        System.out.print(area);
    }//main

    private static int getArea() {
        int sum = 0;
        int r = 1;
        int start = schedule.peek()[S], end = schedule.peek()[E];
        int[] cur;

        while(!schedule.isEmpty()) {
            cur = schedule.poll();

            // 연속 일정
            if(cur[S] <= end || (cur[S] >= end && cur[S] - end <= 1)) {
                end = Math.max(end, cur[E]);
                r = paste(cur[S], cur[E], r);
            }
            else { // 연속 일정 아님
                sum += r * (end - start + 1);
                start = cur[S];
                end = cur[E];

                r = (paste(start, end, 1));
            }

        }//while

        sum += r * (end - start + 1);
        return sum;
    }//getArea

    private static int paste(int start, int end, int r) {
        for(int i=start; i<=end; i++) {
            calendar[i]++;
            r = Math.max(r, calendar[i]);
        }

        return r;
    }//paste

    private static void init() {
        calendar = new int[D+1]; // (1 ≤ S ≤ E ≤ 365)

        // 시작일이 가장 앞선 일정부터
        // 시작일이 같을 경우 일정의 기간이 긴 것이 먼저
        schedule = new PriorityQueue<>((o1, o2) -> {
            if(o1[S] == o2[S]) return o2[E] - o1[E];
            return o1[S] - o2[S];
        });
    }//init

}//class