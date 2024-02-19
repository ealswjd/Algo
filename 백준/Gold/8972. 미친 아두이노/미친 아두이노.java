import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    제목 : 미친 아두이노 (골드 3)
    링크 : https://www.acmicpc.net/problem/8972
 */
public class Main {
    static final char USER='I', ARDUINO='R', EMPTY='.';
    static int R, C, ur, uc;
    static int[][] cnts;
    static Queue<int[]> arduino;
    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cnts = new int[R][C];
        arduino = new LinkedList<>();

        for(int r=0; r<R; r++) {
            char[] tmp = br.readLine().toCharArray();
            for(int c=0; c<C; c++) {
                if(tmp[c] == ARDUINO) {
                    arduino.add(new int[] {r, c});
                    cnts[r][c]++;
                }else if (tmp[c] == USER) {
                    ur = r;
                    uc = c;
                }
            }//for
        }//for

        int cnt = 1;
        boolean isLose = false;
        char[] order = br.readLine().toCharArray();
        out:for(int d : order) {
            d -= '0';
            // 먼저, 종수가 아두이노를 이동
            ur += dr[d];
            uc += dc[d];

            // 미친 아두이노가 있는 칸으로 이동 -> 종수는 게임을 지게 된다.
            if(cnts[ur][uc] > 0) {
                isLose = true;
                break;
            }//if

            // 아두이노는 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동
            int[] robot;
            while(!arduino.isEmpty()) {
                robot = arduino.poll();
                cnts[robot[0]][robot[1]]--;
                getNext(robot);
                cnts[robot[0]][robot[1]]++;

                // 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 종수는 게임을 지게 된다.
                if(robot[0] == ur && robot[1] == uc) {
                    isLose = true;
                    break out;
                }//if
            }//while

            // 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발, 아두이노는 모두 파괴
            for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    if(cnts[r][c] == 1) arduino.add(new int[] {r, c});
                    else cnts[r][c] = 0;
                }//for
            }//for

            cnt++;
        }//for

        if(isLose) System.out.printf("kraj %d", cnt);
        else printMap();
    }//main

    private static void getNext(int[] robot) {
        int r = robot[0];
        int c = robot[1];

        int min = 987654321; // 최소 거리
        int dist;
        for(int i=1; i<10; i++) {
            if(i==5) continue;
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(rangeCheck(nr, nc)) continue;

            dist = Math.abs(nr - ur) + Math.abs(nc - uc);
            if(dist < min) {
                min = dist;
                robot[0] = nr;
                robot[1] = nc;
            }//if
        }//for

    }//getNext

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }//rangeCheck

    private static void printMap() {
        char[][] map = new char[R][C];

        for(int i=0; i<R; i++) {
            Arrays.fill(map[i], EMPTY);
        }
        map[ur][uc] = USER;

        for(int[] robot : arduino) {
            map[robot[0]][robot[1]] = ARDUINO;
        }//for

        StringBuilder result = new StringBuilder();
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                result.append(map[r][c]);
            }//for c
            result.append('\n');
        }//for r

        System.out.print(result);
    }//printMap


}//class