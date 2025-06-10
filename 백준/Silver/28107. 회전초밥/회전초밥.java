import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/28107
public class Main {
    private static final int SUSHI = 0, NUM = 1; // 초밥 번호, 손님 번호
    private static int N, M; // 손님의 수, 초밥의 수
    private static int[] B; // 요리되는 초밥의 종류
    private static int[] count; // i번 손님이 먹은 초밥의 개수
    private static PriorityQueue<int[]> orderList; // 각 손님에 대한 주문 목록


    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }//main


    private static void sol() {

        for(int sushi : B) {
            // 손님 주문 목록이 남아있고, 손님이 원하는 초밥이 없을 때
            while(!orderList.isEmpty() && orderList.peek()[SUSHI] < sushi) {
                orderList.poll();
            }

            // 손님 주문 목록이 남아있고, 손님이 원하는 초밥이 있을 때
            if(!orderList.isEmpty() && orderList.peek()[SUSHI] == sushi) {
                int n = orderList.poll()[NUM]; // 손님 번호
                count[n]++; // n번 손님이 먹은 초밥 개수 증가
            }
        }

    }//sol


    private static void print() {
        StringBuilder ans = new StringBuilder();

        // 손님이 먹은 초밥의 개수를 공백으로 구분하여 출력한다.
        for(int c : count) {
            ans.append(c).append(' ');
        }

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 손님의 수, 초밥의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        B = new int[M];           // 요리되는 초밥의 종류
        count = new int[N];       // i번 손님이 먹은 초밥의 개수
        // 손님 주문 목록 : 초밥 번호 오름차순, 초밥 번호가 같다면 손님 번호 오름차순 정렬
        orderList = new PriorityQueue<>((o1, o2) -> {
            if(o1[SUSHI] == o2[SUSHI]) return o1[NUM] - o2[NUM];
            return o1[SUSHI] - o2[SUSHI];
        });

        // 각 손님에 대한 주문 목록
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 주문 목록에 적힌 초밥 종류의 개수
            for(int j=0; j<k; j++) {
                int sushi = Integer.parseInt(st.nextToken());
                orderList.offer(new int[] {sushi, i});
            }
        }

        // 요리되는 초밥의 종류
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 요리되는 초밥 번호 오름차순 정렬
        Arrays.sort(B);
        br.close();
    }//init


}//class