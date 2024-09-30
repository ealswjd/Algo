import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15553
public class Main {
    static int N, K; // 친구의 수 N, 성냥의 개수 K
    static int[] times; // 친구의 도착 시간

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 친구의 수
        K = Integer.parseInt(st.nextToken()); // 성냥의 개수

        times = new int[N];

        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        int minTime = getMinTime();
        System.out.print(minTime);
    }//main

    private static int getMinTime() {
        List<Integer> list = new ArrayList<>(); // 방문 간격 시간차 리스트

        for(int i=1; i<N; i++) {
            list.add(times[i] - times[i-1] - 1);
        }
        list.sort(Collections.reverseOrder()); // 내림차순 정렬

        // 난로가 켜져 있는 시간
        int total = times[N-1] - times[0] + 1;

        // 가장 큰 간격 제거
        for(int i=0; i<K-1; i++) {
            total -= list.get(i);
        }

        return total;
    }//getMinTime


}//class