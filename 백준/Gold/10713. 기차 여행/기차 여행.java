import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10713
public class Main {
    private static int N, M; // N개의 도시, 여행할 도시의 개수
    private static int[] city; // 방문 횟수
    private static long[] A, B, C; // 티켓 승차 비용, 카드 승차 비용, 카드 구입 비용
    private static long[] lineUse; // 철도 사용 횟수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        long totalCost = 0; // 여행에 드는 최저 비용

        // 철도 사용 횟수 구하기
        int sum = 0;
        for(int i=1; i<N; i++) {
            sum += city[i];
            lineUse[i] = sum;
        }

        /* 여행에 드는 최저 비용 구하기
            -> 티켓 사용 or 카드 구입 중 저렴한 비용 비교 후 계산
         */
        long ticket, card;
        for(int i=1; i<N; i++) {
            ticket = A[i] * lineUse[i];      // 티켓 사용 비용
            card = B[i] * lineUse[i] + C[i]; // 카드 사용 비용
            
            totalCost += Math.min(ticket, card);
        }

        System.out.print(totalCost);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N개의 도시
        M = Integer.parseInt(st.nextToken()); // 여행할 도시의 개수

        city = new int[N+1]; // 방문 횟수
        A = new long[N]; // 티켓 승차 비용,
        B = new long[N]; // 카드 승차 비용,
        C = new long[N]; // 카드 구입 비용
        lineUse = new long[N]; // 철도 사용 횟수

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 출발
        int e; // 도착
        for(int i=1; i<M; i++) {
            e = Integer.parseInt(st.nextToken());

            if(s < e) {
                city[s]++;
                city[e]--;
            }
            else {
                city[s]--;
                city[e]++;
            }

            s = e;
        }

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init
    

}//class