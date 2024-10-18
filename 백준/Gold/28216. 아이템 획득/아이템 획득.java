import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/28216
public class Main {
    private static final int IDX=0, CNT=1;
    private static int Q;
    private static int x = 1, y = 1;
    private static Map<Integer, List<long[]>> xMap;
    private static Map<Integer, List<long[]>> yMap;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);
        move(br);
    }//main


    private static void move(BufferedReader br) throws IOException {
        StringTokenizer st;
        long total = 0;

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 자동차가 d 방향으로 v만큼 이동함
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            total += getItem(d, v);
        }

        System.out.print(total);
    }//move


    private static long getItem(int d, int v) {
        /* 이동이 시작되는 위치에 있는 상자의 아이템은 얻을 수 없다. */
        long item = 0;
        int start, end;
        int nx = x;
        int ny = y;

        // 우 : x 좌표가 증가하는 방향으로 v 만큼 이동
        if(d == 0) {
            nx = x + v;
            start = getStartIndex(x+1, y, yMap);
            end = getEndIndex(nx, y, yMap);
        }
        // 상 : y 좌표가 증가하는 방향으로 v 만큼 이동
        else if(d == 1) {
            ny = y + v;
            start = getStartIndex(y+1, x, xMap);
            end = getEndIndex(ny, x, xMap);
        }
        // 좌 : x 좌표가 감소하는 방향으로 v 만큼 이동
        else if(d == 2) {
            nx = x - v;
            start = getStartIndex(nx, y, yMap);
            end = getEndIndex(x-1, y, yMap);
        }
        // 하 : y 좌표가 감소하는 방향으로 v 만큼 이동
        else {
            ny = y - v;
            start = getStartIndex(ny, x, xMap);
            end = getEndIndex(y-1, x, xMap);
        }


        // 아이템 획득
        if(start != -1 && end != -1) {
            if(d % 2 == 0) {
                item = yMap.get(y).get(end)[CNT];
                if(start > 0) item -=  yMap.get(y).get(start-1)[CNT];
            }
            else {
                item = xMap.get(x).get(end)[CNT];
                if(start > 0) item -=  xMap.get(x).get(start-1)[CNT];
            }
        }

        // x, y 변경
        x = nx;
        y = ny;

        return item;
    }//getItem


    private static int getStartIndex(int target, int key, Map<Integer, List<long[]>> map) {
        if(!map.containsKey(key)) return -1;

        List<long[]> list = map.get(key);
        int start = 0;
        int end = list.size() - 1;
        int mid;
        int idx = -1;

        while(start <= end) {
            mid = (start + end) / 2;

            if(list.get(mid)[IDX] < target) start = mid + 1;
            else {
                end = mid - 1;
                idx = mid;
            }
        }

        return idx;
    }//getStartIndex


    private static int getEndIndex(int target, int key, Map<Integer, List<long[]>> map) {
        if(!map.containsKey(key)) return -1;

        List<long[]> list = map.get(key);
        int start = 0;
        int end = list.size() - 1;
        int mid;
        int idx = -1;

        while(start <= end) {
            mid = (start + end) / 2;

            if(list.get(mid)[IDX] <= target) {
                start = mid + 1;
                idx = mid;
            }
            else end = mid - 1;
        }

        return idx;
    }//getEndIndex


    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 상자 개수
        Q = Integer.parseInt(st.nextToken()); // 이동 횟수

        xMap = new HashMap<>(); // x 좌표 기준
        yMap = new HashMap<>(); // y 좌표 기준

        // 아이템 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken()); // 아이템 개수

            xMap.putIfAbsent(x, new ArrayList<>());
            yMap.putIfAbsent(y, new ArrayList<>());

            xMap.get(x).add(new long[] {y, w});
            yMap.get(y).add(new long[] {x, w});
        }

        // 정렬
        for(int x : xMap.keySet()) {
            xMap.get(x).sort((o1, o2) -> (int) (o1[IDX] - o2[IDX]));
        }
        for(int y : yMap.keySet()) {
            yMap.get(y).sort((o1, o2) -> (int) (o1[IDX] - o2[IDX]));
        }

        // 누적합
        for(int x : xMap.keySet()) {
            for(int i=1, size=xMap.get(x).size(); i<size; i++) {
                xMap.get(x).get(i)[CNT] += xMap.get(x).get(i-1)[CNT];
            }
        }
        for(int y : yMap.keySet()) {
            for(int i=1, size=yMap.get(y).size(); i<size; i++) {
                yMap.get(y).get(i)[CNT] += yMap.get(y).get(i-1)[CNT];
            }
        }

    }//init


}//class

/*
3 3
2 1 1
5 3 2
5 4 3
0 1
0 3
1 4
---
6
 */