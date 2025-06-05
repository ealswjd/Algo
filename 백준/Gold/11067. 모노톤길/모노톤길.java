import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11067
public class Main {
    private static int N; // 카페의 수
    private static int[] numbers; // 좌표를 알고싶은 카페의 번호
    private static Position[] positions; // i번 카페의 좌표
    private static Map<Integer, List<Integer>> xMap; // x기준 y좌표


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            init(br);
            setCafeNumber(); // 카페 번호 설정
            print(ans); // 원하는 번호의 카페 좌표 출력
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static void setCafeNumber() {
        int x = 0; // 현재 x
        int prevY = 0; // 이전 y
        int idx = 0; // 현재 카페 번호
        int size;
        List<Integer> yList;

        while(true) {
            // 해당 좌표에 카페 없음. x 이동
            while(!xMap.containsKey(x)) {
                x++;
            }

            yList = xMap.get(x);
            size = yList.size();

            // 정렬
            Collections.sort(yList);

            if(yList.get(0) == prevY) {
                for(int y : yList) {
                    positions[idx++] = new Position(x, y);
                }
                prevY = yList.get(size-1);
            }
            else {
                for(int i=size-1; i>=0; i--) {
                    positions[idx++] = new Position(x, yList.get(i));
                }
                prevY = yList.get(0);
            }

            if(idx == N) break;
            x++;
        }
    }//setCafeNumber


    private static void print(StringBuilder ans) {
        // 정수 k에 대한 답은 번호가 k인 카페의 좌표 (x,y)를 나타내는 두 개의 정수 x와 y이다.
        for(int k : numbers) {
            ans.append(positions[k].x).append(' ');
            ans.append(positions[k].y).append('\n');
        }
    }//print


    private static void init(BufferedReader br) throws IOException{
        N = Integer.parseInt(br.readLine()); // 카페의 수
        positions = new Position[N]; // i번 카페의 좌표
        xMap = new HashMap<>(); // x 기준 좌표

        StringTokenizer st;
        int x, y;

        // 각 카페의 좌표 (x,y)를 나타내는 두 개의 정수 x와 y가 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            xMap.putIfAbsent(x, new ArrayList<>());
            xMap.get(x).add(y);
        }

        // 마지막 줄에는 정수 m (1 ≤ m ≤ 10)과 m개의 정수가 주어진다.
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        numbers = new int[m];

        for(int i=0; i<m; i++) {
            numbers[i] = Integer.parseInt(st.nextToken()) - 1;
        }

    }//init


    private static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }//Position


}//class