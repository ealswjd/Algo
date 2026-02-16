import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16987
public class Main {
    private static int N; // 계란 개수
    private static int max; // 깰 수 있는 계란의 최대 개수
    private static int[] durability, weight; // 각 계란의 내구도, 무게 정보

    public static void main(String[] args) throws IOException {
        init();
        sol(0);
        // 인범이가 깰 수 있는 계란의 최대 개수를 출력한다.
        System.out.print(max);
    }//main

    private static void sol(int cur) {
        // 마지막 계란까지 다 해봄
        if (cur == N) {
            max = Math.max(max, getBroken());
            return;
        }

        // 현재 계란이 이미 깨졌거나 모든 계란이 이미 깨짐
        if (durability[cur] <= 0 || isAllBroken(cur)) {
            sol(cur + 1);
            return;
        }

        for(int i=0; i<N; i++) {
            // 현재 계란이거나 이미 깨진 계란이면 패스
            if (i == cur || durability[i] <= 0) continue;

            // 계란 치기 - 상대 무게만큼 내구도 감소
            durability[cur] -= weight[i];
            durability[i] -= weight[cur];

            // 다음 계란으로 
            sol(cur + 1);

            // 복구
            durability[cur] += weight[i];
            durability[i] += weight[cur];
        }

    }//sol

    private static boolean isAllBroken(int cur) {
        for(int i=0; i<N; i++) {
            if (i != cur && durability[i] > 0) {
                return false; // 아직 안 깨진 계란 존재
            }
        }

        return true;
    }//isAllBroken

    private static int getBroken() {
        int cnt = 0;

        for(int i=0; i<N; i++) {
            // 깨진 계란 개수 증가
            if (durability[i] <= 0) cnt++;
        }

        return cnt;
    }//getBroken

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 계란 개수

        durability = new int[N]; // 각 계란의 내구도
        weight = new int[N]; // 각 계란의 무게 정보

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

}//class