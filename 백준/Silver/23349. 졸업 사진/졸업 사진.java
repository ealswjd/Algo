import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23349
public class Main {
    static class Place implements Comparable<Place> {
        String place; // 장소 이름
        int start; // 시작 시간
        int end; // 종료 시간
        int cnt; // 제출 횟수

        public Place(String place, int start, int end, int cnt) {
            this.place = place;
            this.start = start;
            this.end = end;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Place o) {
            // 가장 빠른 시간대
            if(this.place.equals(o.place)) return this.start - o.start;
            // 사전 순으로 가장 앞에 오는 장소
            if(this.cnt == o.cnt) return this.place.compareTo(o.place);
            // 가장 많은 사람이 제출
            return o.cnt - this.cnt;
        }

        @Override
        public String toString() {
            return String.format("%s %d %d", place, start, end);
        }
    }

    
    private static final int TIME = 50_000; // 최대 시간
    private static Map<String, int[]> placeInfo; // 장소 신청 정보
    private static Map<String, Integer> placeMax; // 장소별 신청 횟수 최댓값
    

    public static void main(String[] args) throws IOException {
        init();
        getResult();
    }//main

    
    private static void getResult() {
        PriorityQueue<Place> pq = new PriorityQueue<>();

        for(String place : placeInfo.keySet()) {
            int max = placeMax.get(place); // 해당 장소 신청 최댓값
            int start = 0;

            // 가장 혼잡한 구간 시작 시간 찾기
            for(int i=1; i<=TIME; i++) {
                if(placeInfo.get(place)[i] == max) {
                    start = i;
                    break;
                }
            }

            // 가장 혼잡한 구간 종료 시간 찾기
            int end = start + 1;
            while(end <= TIME && placeInfo.get(place)[end] == max) {
                end++;
            }

            pq.offer(new Place(place, start, end, max));
        }

        System.out.print(pq.poll());
    }//getResult

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        placeInfo = new HashMap<>();
        placeMax = new HashMap<>();
        Set<String> student = new HashSet<>();

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken(); // 학생 이름

            // 한 학생이 여러 번 제출한 경우 무시
            if(student.contains(name)) continue;
            // 처음이면 접수
            student.add(name);

            String place = st.nextToken(); // 장소 이름
            int start = Integer.parseInt(st.nextToken()); // 시작 시간
            int end = Integer.parseInt(st.nextToken()); // 종료 시간

            placeInfo.putIfAbsent(place, new int[TIME + 1]);
            placeInfo.get(place)[start]++;
            placeInfo.get(place)[end]--;
        }

        br.close();

        for(String place : placeInfo.keySet()) {
            int[] count = placeInfo.get(place);
            int max = 0;

            for(int i=1; i<=TIME; i++) {
                count[i] += count[i-1];
                max = Math.max(max, count[i]);
            }

            placeMax.put(place, max);
        }
    }//init

    
}//class