import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14907
public class Main {
    private static final int N = 26;
    private static int[] prev, times; // 이전 작업 개수, 작업을 완료하는데 요구되는 시간
    private static List<List<Integer>> workList; // 작업


    public static void main(String[] args) throws IOException {
        init();

        int totalTime = getTime();
        System.out.print(totalTime);
    }//main


    private static int getTime() {
        int totalTime = 0; // 모든 작업을 완료하는데 걸리는 시간
        int[] prevTimes = new int[N]; // 이전 작업에 걸린 시간
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            // 이전 작업이 없음
            if(prev[i] == 0 && times[i] != -1) {
                q.offer(i);
            }
        }

        int cur, time;
        while(!q.isEmpty()) {
            cur = q.poll(); // 현재 작업
            time = prevTimes[cur] + times[cur]; // 걸린 시간
            totalTime = Math.max(totalTime, time); // 총 완료 시간

            // 다음 작업 가능한지 확인
            for(int next : workList.get(cur)) {
                prevTimes[next] = Math.max(prevTimes[next], time);
                if(--prev[next] == 0) {
                    q.offer(next);
                }
            }
        }

        return totalTime;
    }//getTime


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        prev = new int[N]; // 이전 작업 개수
        times = new int[N]; // 작업을 완료하는데 요구되는 시간
        workList = new ArrayList<>(N); // 작업

        for(int i=0; i<N; i++) {
            workList.add(new ArrayList<>());
            times[i] = -1;
        }

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            if(input == null || input.isEmpty()) break;

            st = new StringTokenizer(input);
            int name = st.nextToken().charAt(0) - 'A'; // 작업 이름을 나타내는 영문 대문자
            int time = Integer.parseInt(st.nextToken()); // 작업을 완료하는데 요구되는 시간
            times[name] = time;

            // 이 작업을 시작하기 전에 완료해야만 하는 작업이 있음
            if(st.hasMoreTokens()) {
                char[] prevWorks = st.nextToken().toCharArray();
                for(char work : prevWorks) {
                    prev[name]++;
                    workList.get(work - 'A').add(name);
                }
            }

        }

        br.close();
    }//init


}//class