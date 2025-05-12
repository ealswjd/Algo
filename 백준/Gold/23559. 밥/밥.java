import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23559
public class Main {
    private static final int A = 0, B = 1, G = 2; // A(5000원), B(1,000원)
    private static final int[] COST = {5_000, 1_000};
    private static int N, X; // N일간 학식에 총 X원 이하를 사용
    private static int[][] menu; // 각 날에 먹을 수 있는 맛 A(5,000), B(1,000)


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int total = 0;
        int budget = X;
        int day;

        for(int i=0; i<N; i++) {
            day = N - i - 1;

            // 5천원 메뉴 사먹을 수 있는지
            if(menu[i][A] > menu[i][B] && (budget - COST[A]) >= (day * COST[B])) {
                total += menu[i][A];
                budget -= COST[A];
            }
            // 천원 메뉴가 더 이득
            else {
                total += menu[i][B];
                budget -= COST[B];
            }
        }

        System.out.print(total);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N일간
        X = Integer.parseInt(st.nextToken()); // 학식에 총 X원 이하를 사용

        menu = new int[N][3]; // 각 날에 먹을 수 있는 맛 A(5,000), B(1,000)

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            menu[i][A] = Integer.parseInt(st.nextToken());
            menu[i][B] = Integer.parseInt(st.nextToken());
            menu[i][G] = menu[i][A] - menu[i][B];
        }

        Arrays.sort(menu, ((o1, o2) -> o2[G] - o1[G]));

        br.close();
    }//init


}//class