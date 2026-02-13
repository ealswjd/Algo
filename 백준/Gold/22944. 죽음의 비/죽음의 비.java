import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22944
public class Main {
    private static final int MAX = 2_500_025;
    
    private static int H, D, U; // 현재 체력, 우산의 내구도, 우산 개수
    private static Position start, end; // 시작, 종료 위치
    private static List<Position> umbrellas;  // 우산 위치
    private static boolean[] visited; // 방문체크
    private static int minDist; // 최소 이동 횟수

    public static void main(String[] args) throws IOException {
        init();
        sol(start.r, start.c, H, 0, 0);

        int result = minDist == MAX ? -1 : minDist;
        System.out.print(result);
    }//main

    private static void sol(int r, int c, int hp, int uHp, int dist) {
        // 현재 위치에서 안전지대까지 거리
        int distToEnd = Math.abs(r - end.r) + Math.abs(c - end.c);

        if (minDist < dist + distToEnd) {
            return;
        }
        // 현재 체력 + 우산 내구도로 안전지대 도착 가능
        if (hp + uHp >= distToEnd) {
            minDist = Math.min(minDist, dist + distToEnd);
        }

        for(int i=0; i<U; i++) {
            if (visited[i]) continue;

            Position next = umbrellas.get(i);
            int nextDist = Math.abs(r - next.r) + Math.abs(c - next.c);

            // 현재 우산까지 도달 가능한지 확인
            if (hp + uHp >= nextDist) {
                visited[i] = true;
                // 우산에 도착한 직후 체력
                int nextHp = hp;
                if (nextDist > uHp) {
                    nextHp -= (nextDist - uHp);
                }
                // 새 우산으로 교체
                sol(next.r, next.c, nextHp, D, dist + nextDist);

                visited[i] = false;
            }
        }
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 한변의 길이인 N, 현재 체력 H, 우산의 내구도 D가 공백으로 구분되어 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        umbrellas = new ArrayList<>(); // 우산 위치
        minDist = MAX;

        for(int i=0; i<N; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if (line[j] == 'S') {
                    start = new Position(i, j);
                } else if (line[j] == 'E') {
                    end = new Position(i, j);
                } else if (line[j] == 'U'){
                    umbrellas.add(new Position(i, j));
                }
            }
        }

        U = umbrellas.size();
        visited = new boolean[U];

        br.close();
    }//init

    private static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }//Position

}//class