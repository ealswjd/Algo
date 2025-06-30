import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/8911
public class Main {
    private static final int[] dx = {0, 1, 0, -1}; // 상우하좌
    private static final int[] dy = {1, 0, -1, 0};
    private static int x, y, d; // x y 좌표, 방향
    private static int minX, minY, maxX, maxY;


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            // 컨트롤 프로그램이 주어진다
            char[] control = br.readLine().toCharArray();

            init(); // 위치, 방향 등 초기화
            ans.append(move(control)).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static int move(char[] control) {

        for(char c : control) {
            if(c == 'F') { // 한 눈금 앞으로
                move(x + dx[d], y + dy[d]);
            }
            else if(c == 'B') { // 한 눈금 뒤로
                move(x - dx[d], y - dy[d]);
            }
            else if(c == 'R') { // 오른쪽으로 90도 회전
                d = (d + 1) % 4;
            }
            else { // 왼쪽으로 90도 회전
                d = (d + 3) % 4;
            }
        }

        return Math.abs(maxX - minX) * Math.abs(maxY - minY);
    }//move


    private static void move(int nx, int ny) {
        // 좌표 이동
        x = nx;
        y = ny;

        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
    }//move


    private static void init() {
        // 가장 처음에 (0, 0)에 있고, 북쪽을 쳐다보고 있다.
        x = 0;
        y = 0;
        d = 0;

        minX = minY = maxX = maxY = 0;
    }//init


}//class