import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2461
public class Main {
    private static final int MAX = 1_000_000_000, IDX = 0, POWER = 1;
    private static int N, M; // 학급 개수, 학생 수
    private static List<PriorityQueue<Integer>> students; // 학생들 능력치


    public static void main(String[] args) throws IOException {
        init();
        int result = getMin();

        System.out.print(result);
    }//main


    private static int getMin() {
        PriorityQueue<int[]> leader = new PriorityQueue<>(
                Comparator.comparingInt(o -> o[POWER])
        ); // 반 대표
        int min = MAX; // 최솟값
        int max = 0; // 최댓값

        // 맨 처음 반 대표들 뽑기
        for(int i=0; i<N; i++) {
            int power = students.get(i).poll(); // 능력치
            min = Math.min(min, power);
            max = Math.max(max, power);
            leader.add(new int[] {i, power});
        }

        int result = max - min; // 능력치 최댓값과 최솟값 차이 최솟값
        int[] cur;
        int idx, power;
        while(!leader.isEmpty()) {
            cur = leader.poll();
            idx = cur[IDX];

            // 남은 학생이 없음
            if(students.get(idx).isEmpty()) {
                break;
            }

            // idx반에서 새로운 대표 뽑기
            power = students.get(idx).poll();
            leader.add(new int[] {idx, power});

            // 최댓값, 최솟값 갱신
            min = leader.peek()[POWER];
            max = Math.max(max, power);
            result = Math.min(result, max - min);
        }

        return result;
    }//getMin


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 학급의 수를 나타내는 N과 각 학급의 학생의 수를 나타내는 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new ArrayList<>(N); // 학생들 능력치
        for(int i=0; i<N; i++) {
            students.add(new PriorityQueue<>());
        }

        // 한 학급 학생들의 능력치를 나타내는 M개의 양의 정수가 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> pq = students.get(i);
            for(int j=0; j<M; j++) {
                int power = Integer.parseInt(st.nextToken());
                pq.add(power);
            }
        }

        br.close();
    }//init


}//class