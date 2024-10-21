import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    https://www.acmicpc.net/problem/1508
    심판을 배치할 때, 가장 가까운 두 심판의 거리를 최대로 하려고 한다.
    심판을 어디에 배치시켜야 할지 구하는 프로그램
*/
public class Main {
    private static int N, M, K; // 트랙 길이 N, 심판 M명, 위치 개수 K   
    private static int[] position; // 심판 배치 가능 위치

    public static void main(String[] args) throws IOException {
        init();

        int len = getLength(); // 가장 가까운 두 심판의 최대거리
        print(len);
    }//main

    private static void print(int len) {
        StringBuilder ans = new StringBuilder();
        boolean[] checked = new boolean[K];
        int prev = position[0];
        int cnt = 0;
        
        checked[0] = true;
        for(int i=1; i<K; i++) {
            if(position[i] - prev >= len) {
                if(++cnt == M) break;

                checked[i] = true;
                prev = position[i];
            }
        }

        for(boolean check : checked) {
            if(check) ans.append("1");
            else ans.append("0");
        }

        System.out.print(ans);
    }//print


    private static int getLength() {
        int len = 0;
        int start = 0;
        int end = N + 1;
        int mid;

        while (start < end) {
            mid = (start + end) / 2;

            if(getCnt(mid) >= M) {
                start = mid + 1;
                len = mid;
            }
            else end = mid;
        }

        return len;
    }//getLength


    private static int getCnt(int len) {
        int cnt = 1;
        int prev = position[0];

        for(int i=1; i<K; i++) {
            if(position[i] - prev >= len) {
                if(++cnt >= M) return cnt;

                prev = position[i];
            }
        }

        return cnt;
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 트랙 길이
        M = Integer.parseInt(st.nextToken()); // 심판 M명
        K = Integer.parseInt(st.nextToken()); // 심판 배치 가능 위치 개수

        position = new int[K]; // 심판 배치 가능 위치

        // K개 위치 오름차순으로 주어짐.(N <= position[i], position[i] >= 0)
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            position[i] = Integer.parseInt(st.nextToken());
        }

    }//init

}//class

/*
20 3 4
0 5 10 15 
ans : 1110
 */