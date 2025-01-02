import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6209
public class Main {
    private static int D; // 탈출구까지의 거리
    private static int N; // 작은 돌섬의 수
    private static int M; // 제거할 수 있는 작은 돌섬의 수
    private static int[] distance; // 각 작은 돌섬 거리


    public static void main(String[] args) throws IOException {
        init();

        int result = getMax();
        System.out.print(result);
    }//main


    private static int getMax() {
        int max = 0;
        int start = 0;
        int end = D;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                start = mid + 1;
                max = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return max;
    }//getMax


    private static boolean isPossible(int diff) {
        int cnt = 0;
        int prev = 0;

        for(int i=1; i<=N+1; i++) {
            if(distance[i] - prev < diff) {
                if(++cnt > M) return false;
            }
            else {
                prev = distance[i];
            }
        }

        return true;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken()); // 탈출구까지의 거리
        N = Integer.parseInt(st.nextToken()); // 작은 돌섬의 수
        M = Integer.parseInt(st.nextToken()); // 제거할 수 있는 작은 돌섬의 수

        distance = new int[N+2]; // 각 작은 돌섬 거리
        distance[N+1] = D;

        for(int i=1; i<=N; i++) {
            distance[i] = Integer.parseInt(br.readLine());
        }

        br.close();
        Arrays.sort(distance);
    }//init


}//class