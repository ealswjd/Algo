import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18870
public class Main {
    private static int N; // 좌표 개수
    private static Position[] positions; // 좌표

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int[] result = new int[N];

        Arrays.sort(positions);
        result[positions[0].idx] = 0;

        for(int i=1; i<N; i++) {
            Position prev = positions[i-1];
            Position cur = positions[i];
            if (prev.x < cur.x) {
                result[cur.idx] = result[prev.idx] + 1;
            } else {
                result[cur.idx] = result[prev.idx];
            }
        }

        for(int i=0; i<N; i++) {
            ans.append(result[i]).append(' ');
        }

        System.out.print(ans);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 좌표 개수

        positions = new Position[N]; // 좌표

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            positions[i] = new Position(i, Integer.parseInt(st.nextToken()));
        }

        br.close();
    }//init

    private static class Position implements Comparable<Position> {
        int idx;
        int x;
        Position(int idx, int x) {
            this.idx = idx;
            this.x = x;
        }

        @Override
        public int compareTo(Position p) {
            return this.x - p.x;
        }
    }//Position

}//class