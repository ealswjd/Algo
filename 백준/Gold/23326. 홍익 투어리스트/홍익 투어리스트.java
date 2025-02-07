import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 https://www.acmicpc.net/problem/23326
 도현이가 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는 지 출력
 */
public class Main {
    private static int N, Q; // 구역의 개수, 쿼리의 개수
    private static boolean[] isHotSpot; // 명소 여부
    private static TreeSet<Integer> hotSpot; // 명소


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);
        solution(br);
        
        br.close();
    }//main


    private static void solution(BufferedReader br) throws IOException {
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int cur = 1; // 도현이 현재 위치
        int q, x;

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            q = Integer.parseInt(st.nextToken());

            switch (q) {
                case 1 : // x가 명소가 아니면 명소로 지정, 명소면 지정 풀림
                    x = Integer.parseInt(st.nextToken());
                    toggleSpotStatus(x);

                    break;
                case 2 : // 시계방향으로 x만큼 이동
                    x = Integer.parseInt(st.nextToken());
                    cur = move(cur, x);

                    break;
                case 3 : // 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는 지 출력
                    int dist = getDistance(cur);
                    ans.append(dist).append('\n');

                    break;
            }

        }

        System.out.print(ans);
    }//solution


    // 명소 상태 변경
    private static void toggleSpotStatus(int x) {
        if(isHotSpot[x]) hotSpot.remove(x);
        else hotSpot.add(x);

        isHotSpot[x] = !isHotSpot[x];
    }//toggleSpotStatus


    // 시계 방향으로 x 만큼 이동
    private static int move(int cur, int x) {
        cur = (cur + x) % N;
        if(cur == 0) cur = N;

        return cur;
    }//move


    // 가장 가까운 명소 거리 찾기
    private static int getDistance(int cur) {
        int dist = 0;

        if(hotSpot.isEmpty()) dist = -1;
        else if(!isHotSpot[cur]) {
            // 현재 위치(cur)에서 갈 수 있는 가장 가까운 명소
            Integer spot = hotSpot.ceiling(cur);
            if(spot == null) spot = hotSpot.first() + N;

            dist = spot - cur;
        }

        return dist;
    }//getDist


    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 구역의 개수
        Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수

        isHotSpot = new boolean[N + 1]; // 명소 여부
        hotSpot = new TreeSet<>();      // 명소

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            // i번째 구역이 명소임
            if(st.nextToken().charAt(0) == '1') {
                isHotSpot[i] = true;
                hotSpot.add(i);
            }
        }

    }//init


}//class