import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2819
public class Main {
    private static int N; // 조사점의 수
    private static char[] commands; // 명령
    private static int curX, curY; // 로봇의 현재 좌표
    private static long dist; // 거리의 합
    private static int[] xList; // x 좌표
    private static int[] yList; // y 좌표


    public static void main(String[] args) throws IOException {
        init();
        move();

    }//main


    private static void move() {
        StringBuilder ans = new StringBuilder();
        int upperIdx, lowerIdx;

        for(char c : commands) {

            if(c == 'S') { // 상 : x, y+1
                upperIdx = upperBound(yList, curY);
                dist += 2L * upperIdx - N;

                curY++;
            }
            else if(c == 'J') { // 하 : x, y-1
                lowerIdx = N - lowerBound(yList, curY);
                dist += 2L * lowerIdx - N;

                curY--;
            }
            else if(c == 'I') { // 우 : x+1, y
                upperIdx = upperBound(xList, curX);
                dist += 2L * upperIdx - N;

                curX++;
            }
            else { // 좌 : x-1, y
                lowerIdx = N - lowerBound(xList, curX);
                dist += 2L * lowerIdx - N;

                curX--;
            }

            ans.append(dist).append('\n');
        }

        System.out.print(ans);
    }//move


    private static int upperBound(int[] list, int target) {
        int start = 0;
        int end = list.length;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(list[mid] > target) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return start;
    }//upperBound


    private static int lowerBound(int[] list, int target) {
        int start = 0;
        int end = list.length;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(list[mid] >= target) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return start;
    }//lowerBound


    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 조사점의 수
        int M = Integer.parseInt(st.nextToken()); // 명령의 수

        xList = new int[N];
        yList = new int[N];

        // N개 줄에는 조사점의 x좌표와 y좌표가 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            xList[i] = x;
            yList[i] = y;

            dist += Math.abs(x - curX) + Math.abs(y - curY);
        }

        // 상근이가 로봇에게 전송한 명령이 순서대로 주어진다.
        commands = br.readLine().toCharArray(); // 명령

        br.close();

        // 정렬
        Arrays.sort(xList);
        Arrays.sort(yList);

    }//init


}//class