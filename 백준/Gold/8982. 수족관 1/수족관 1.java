import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N; // 꼭짓점의 개수
    private static int L; // 라인 개수
    private static Line[] lines; // 선 정보
    private static int[][] yPositions; // y좌표
    private static boolean[] isHole; // 구멍


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int water = 0;

        for (int i = L - 1; i >= 0; i--) {
            if (isHole[i]) {
                water = yPositions[i][0];
            } else {
                water = Math.max(Math.min(water, yPositions[i][0]), yPositions[i][1]);
            }
            yPositions[i][1] = water;
        }

        water = 0;
        for (int i = 0; i < L; i++) {
            if (isHole[i]) {
                water = yPositions[i][0];
            } else {
                water = Math.max(Math.min(water, yPositions[i][0]), yPositions[i][1]);
            }
            yPositions[i][1] = water;
        }

        int answer = 0;
        for (int i = 0; i < L; i++) {
            int width = lines[i].ex - lines[i].sx;
            int height = yPositions[i][0] - yPositions[i][1];
            answer += width * height;
        }

        System.out.print(answer);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 꼭짓점의 개수 N(1 ≤ N ≤ 5,000)이 주어진다. N은 짝수이다.
        N = Integer.parseInt(br.readLine()); // 꼭짓점의 개수
        int[][] points = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        L = (N - 2) / 2;
        lines = new Line[L];
        yPositions = new int[L][2]; // [i][0] = 수평선 y좌표, [i][1] = 물 y좌표
        isHole = new boolean[L]; // 구멍

        int idx = 0;
        for (int i = 1; i < N - 1; i += 2) {
            int x1 = points[i][0];
            int y = points[i][1];
            int x2 = points[i + 1][0];
            lines[idx] = new Line(x1, x2, y);
            yPositions[idx][0] = y;
            idx++;
        }

        Arrays.sort(lines);

        // 구멍의 개수 K (1 ≤ K ≤ N/2)가 자연수로 주어진다.
        int K = Integer.parseInt(br.readLine()); // 구멍의 개수
        /* K개의 줄에는 각 구멍이 존재하는 수평선분의 양 끝 꼭짓점 위치를 나타내는
            네 개의 값이 빈 칸을 사이에 두고 차례로 주어진다. (항상 a < c 이다.) */
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            st.nextToken(); // y는 동일해서 무시

            idx = getIdx(a, b, c, L-1);

            if(idx != -1) isHole[idx] = true;
        }

        br.close();
    }//init


    private static int getIdx(int x1, int y, int x2, int n) {
        int start = 0;
        int end = n;
        int idx = -1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (lines[mid].sx == x1 && lines[mid].ex == x2 && lines[mid].y == y) {
                idx = mid;
                break;
            } else if (lines[mid].sx < x1) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return idx;
    }//getIdx


    private static class Line implements Comparable<Line>{
        int sx;
        int ex;
        int y;
        Line(int sx, int ex, int y) {
            this.sx = sx;
            this.ex = ex;
            this.y = y;
        }

        @Override
        public int compareTo(Line other) {
            return Integer.compare(this.sx, other.sx);
        }

        @Override
        public String toString() {
            return "Line{" +
                    "sx=" + sx +
                    ", ex=" + ex +
                    ", y=" + y +
                    '}';
        }
    }//Line


}//class