import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20440
public class Main {
    private static int N; // 방에 출입한 모기의 마릿수
    private static List<Mosquito> mosquitoList; // 시간대별 모기 마릿수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int len = mosquitoList.size();
        int total = 0; // 모기 마릿수
        int max = 0; // 가장 많이 있는 모기 마릿수
        int start = -1; // 시작 시간
        int end = -1;   // 종료 시간

        for(int i=0; i<len; i++) {
            Mosquito cur = mosquitoList.get(i);
            total += cur.count;

            if(max < total) {
                max = total;
                start = cur.time;
                end = mosquitoList.get(i+1).time;
            }
        }

        // 모기가 가장 많이 있는 시간대의 모기 마릿수를 출력
        ans.append(max).append('\n');
        // 모기가 가장 많이 있는 시간대의 연속 구간 전체
        ans.append(start).append(' ').append(end);

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 방에 출입한 모기의 마릿수

        List<Mosquito> tmp = new ArrayList<>();
        mosquitoList = new ArrayList<>(); // 시간대별 모기 마릿수 저장

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 입장 시각
            int e = Integer.parseInt(st.nextToken()); // 퇴장 시각

            tmp.add(new Mosquito(s, 1)); // 모기 입장
            tmp.add(new Mosquito(e, -1)); // 모기 퇴장
        }

        // 정렬
        Collections.sort(tmp);

        // 동시간대 출입 제거
        int len = tmp.size();
        mosquitoList.add(tmp.get(0));
        int size;
        for(int i=1; i<len; i++) {
            size = mosquitoList.size();
            Mosquito prev = mosquitoList.get(size - 1);
            Mosquito cur = tmp.get(i);

            if(prev.time == cur.time && prev.count + cur.count == 0) {
                mosquitoList.remove(size - 1);
            }
            else {
                mosquitoList.add(cur);
            }
        }


        br.close();
    }//init


    private static class Mosquito implements Comparable<Mosquito>{
        int time;  // 시각
        int count; // 모기 마릿수

        Mosquito(int time, int count) {
            this.time = time;
            this.count = count;
        }

        @Override
        public int compareTo(Mosquito m) {
            return this.time - m.time;
        }

        @Override
        public String toString() {
            return this.time + " : " + this.count;
        }
    }//Mosquito


}//class