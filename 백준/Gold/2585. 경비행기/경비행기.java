import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2585
public class Main {
    private static final int S = 0, E = 10_000; // 시작, 끝 좌표
    private static int N, K, endIdx; // 비행장의 수, 착륙 허용 최대 횟수
    private static int[][] fuels; // 필요한 연료양


    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int start = 0;
        int end = fuels[S][endIdx];
        int result = end;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isAvailable(mid)) {
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.print(result);
    }//sol


    private static boolean isAvailable(int fuel) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[N + 2];
        Arrays.fill(visited, -1);

        q.offer(S);
        visited[S] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur] > K) continue;

            for (int next = 0; next <= endIdx; next++) {
                if (cur == next || visited[next] != -1) continue;

                if (fuels[cur][next] <= fuel) {
                    visited[next] = visited[cur] + 1;
                    q.offer(next);

                    if(next == endIdx && visited[next] - 1 <= K) {
                        return true;
                    }
                }
            }
        }

        return false;
    }//isAvailable

    private static int requiredFuel(Point p1, Point p2) {
        int x = Math.abs(p1.x - p2.x);
        int y = Math.abs(p1.y - p2.y);

        double dist = Math.sqrt(x * x + y * y);
        return (int) Math.ceil(dist / 10.0);
    }//requiredFuel


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n과 k가 하나의 공백을 사이에 두고 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        endIdx = N + 1;
        Point[] points = new Point[N+2]; // 각 비행장 좌표 + 출발, 도착 좌표
        fuels = new int[N+2][N+2];

        for(int i=1; i<=N; i++) {
            // 각 비행장 (급유지)의 정수좌표가 x y 형식으로 주어진다.
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(x, y);
        }

        points[0] = new Point(S, S);
        points[endIdx] = new Point(E, E);

        for(int i=0; i<=N+1; i++) {
            for(int j=i+1; j<=N+1; j++) {
                int f = requiredFuel(points[i], points[j]);
                fuels[i][j] = fuels[j][i] = f;
            }
        }

        br.close();
    }//init


}//main