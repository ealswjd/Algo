import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23035
public class Main {
    private static int N, M; // 가톨릭대 크기
    private static List<Position> positions; // 고양이의 위치


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int total = positions.size(); // 가톨릭대 안에 있는 고양이들 수
        int cnt = 0; // 밥을 챙겨줄 수 있는 고양이의 최대 마릿수
        int idx, curC;
        int[] dp = new int[total + 1]; // c 좌표 저장

        for(Position position : positions) {
            curC = position.c; // 현재 고양이 위치

            // 수업에 늦지 않고 갈 수 있는 위치에 고양이가 있다면
            if (curC >= dp[cnt]) {
                dp[++cnt] = curC;
                continue;
            }

            idx = getIndex(curC, cnt, dp);
            dp[idx] = curC;
        }

        return cnt;
    }//getMax


    private static int getIndex(int cur, int end, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] > cur) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 가톨릭대 크기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 고양이의 수를 나타내는 정수가 주어진다.
        int K = Integer.parseInt(br.readLine());
        positions = new ArrayList<>(K); // 고양이의 위치

        // 고양이의 위치를 나타내는 정수 r, c가 주어진다.
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 가톨릭대 밖에 고양이가 존재할 수 있다.
            if(r > N || c > M) continue;

            positions.add(new Position(r, c));
        }

        // 정렬
        Collections.sort(positions);
        br.close();
    }//init


    private static class Position implements Comparable<Position> {
        int r;
        int c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Position o) {
            if(this.r == o.r) return this.c - o.c;
            return this.r - o.r;
        }

    }//Position


}//class