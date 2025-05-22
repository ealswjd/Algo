import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11663
public class Main {
    private static final int S = 0, E = 1;
    private static int N, M; // 점의 개수 N과 선분의 개수 M
    private static int[] points; // 점의 좌표
    private static int[][] lines; // 선분의 시작점과 끝점


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int start, end, cnt;

        for(int[] line : lines) {
            start = getStart(line[S]);
            end = getEnd(line[E]);
            cnt = 0;

            if(start != -1 && end != -1 && start <= end) {
                cnt = end - start + 1;
            }

            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static int getStart(int target) {
        int idx = -1;
        int start = 0;
        int end = N-1;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(points[mid] >= target) {
                end = mid - 1;
                idx = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return idx;
    }//getStart


    private static int getEnd(int target) {
        int idx = -1;
        int start = 0;
        int end = N-1;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(points[mid] <= target) {
                start = mid + 1;
                idx = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return idx;
    }//getEnd


    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 점의 개수 N과 선분의 개수 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 점의 개수 N
        M = Integer.parseInt(st.nextToken()); // 선분의 개수 M

        points = new int[N]; // 점의 좌표
        lines = new int[M][2]; // 선분의 시작점과 끝점

        // 점의 좌표가 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        // M개의 줄에는 선분의 시작점과 끝점이 주어진다.
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][S] = Integer.parseInt(st.nextToken());
            lines[i][E] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(points);
        br.close();
    }//init


}//class