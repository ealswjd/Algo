import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/19584
public class Main {
    private static Map<Integer, Long> yMap; // y좌표 기준 통행량

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        long max = 0; // 최대로 파괴되는 통행량
        // 누적합을 위해 0 넣어주기
        int prev = -1;
        yMap.put(prev, 0L);

        // y 키값 기준으로 정렬
        List<Integer> keyList = new ArrayList<>(yMap.keySet());
        Collections.sort(keyList);

        for(int y : keyList) {
            // 이전 y좌표 통행량 + 현재 y좌료 통행량
            yMap.put(y, yMap.get(prev) + yMap.get(y));

            // 최대 통행량 갱신
            max = Math.max(max, yMap.get(y));
            prev = y;
        }

        // 최대로 파괴되는 통행량을 출력한다.
        System.out.print(max);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N과 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 주요 장소 N개
        int M = Integer.parseInt(st.nextToken()); // 도로 정보 개수

        yMap = new HashMap<>(); // y좌표 기준 통행량
        int[] y = new int[N+1]; // 주요 장소 위치 y 좌표

        // 주요 장소들의 x좌표와 y좌표가 공백으로 구분되어 주어진다
        // 좌표는 정수이며 그 절댓값은 10^9를 넘지 않는다
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        while(M-- > 0) {
            // ui번째 장소와 vi번째 장소를 잇는 통행량 ci의 도로가 있음을 의미
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int from = Math.min(y[u], y[v]); // 시점
            int to = Math.max(y[u], y[v]) + 1; // 종점

            yMap.put(from, yMap.getOrDefault(from, 0L) + c);
            yMap.put(to, yMap.getOrDefault(to, 0L) - c);
        }

        br.close();
    }//init

}//class