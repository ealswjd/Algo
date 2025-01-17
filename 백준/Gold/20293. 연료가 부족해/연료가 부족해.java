import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20293
public class Main {
    private static int R, C; // 지도의 세로 길이와 가로 길이
    private static int N; // 연료 보관소의 개수
    private static FuelStation[] stations; // 연료 보관소


    public static void main(String[] args) throws IOException {
        init();

        int result = getResult();
        System.out.print(result);
    }//main


    private static int getResult() {
        int start = 0;
        int end = R + C;
        int mid;
        int result = 0;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                end = mid - 1;
                result = mid;
            }
            else start = mid + 1;
        }

        return result;
    }//getResult


    private static boolean isPossible(int mid) {
        int distance;
        int[] dp = new int[N];

        Arrays.fill(dp, -1);
        dp[1] = mid; // 출발 전 연료 충전

        for(int to=2; to<N; to++) {
            for(int from=1; from<to; from++) {
                distance = Math.abs(stations[to].r - stations[from].r)
                        + Math.abs(stations[to].c - stations[from].c);

                // 자동차는 오직 오른쪽이나 아래쪽으로만 이동
                if(checkDirection(stations[from], stations[to])) continue;
                // 다음 장소로 이동할 수 있는지 확인
                if(dp[from] < distance) continue;

                dp[to] = Math.max(dp[to], dp[from] - distance + stations[to].fuel);
            }
        }

        // mid 만큼 충천해서 갈 수 있는지 확인
        return dp[N-1] != -1;
    }//isPossible


    private static boolean checkDirection(FuelStation from, FuelStation to) {
        return from.r > to.r || from.c > to.c;
    }//checkDirection


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 지도의 세로 길이와 가로 길이
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 연료 보관소의 개수
        N = Integer.parseInt(br.readLine()) + 3;

        stations = new FuelStation[N];
        stations[0] = new FuelStation(0, 0, 0);
        stations[1] = new FuelStation(1, 1, 0);
        stations[2] = new FuelStation(R, C, 0);

        for(int i=3; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            stations[i] = new FuelStation(r, c, f);
        }

        Arrays.sort(stations);

        br.close();
    }//init


    private static class FuelStation implements Comparable<FuelStation> {
        int r;
        int c;
        int fuel;
        
        public FuelStation(int r, int c, int fuel) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
        }
        
        @Override
        public int compareTo(FuelStation o) {
            return (this.r + this.c) - (o.r + o.c);
        }
    }//FuelStation


}//class