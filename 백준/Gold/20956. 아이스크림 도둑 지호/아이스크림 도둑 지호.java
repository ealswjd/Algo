import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20956
public class Main {
    private static final int MINT = 7; // 민트초코는 7의 배수
    private static int N, M; // 아이스크림 개수, 먹을 아이스크림 개수
    private static IceCream[] iceCreams; // 아이스크림
    

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main
    

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        boolean isReversed = false; // 반대 방향
        int idx = 0; // 현재 인덱스
        int curAmount; // 현재 양
        Deque<IceCream> sameAmount = new ArrayDeque<>(); // 같은양끼리

        while(M > 0 && idx < N) {
            curAmount = iceCreams[idx].amount; // 현재 가장 많은 아이스크림 양

            // 같은 양 다 담아주기
            while(idx < N && iceCreams[idx].amount == curAmount) {
                sameAmount.add(iceCreams[idx]);
                idx++;
            }

            while(!sameAmount.isEmpty() && M > 0) {
                IceCream cur;

                if (isReversed) { // 반대 방향
                    cur = sameAmount.pollLast();
                } else { // 정방향
                    cur = sameAmount.pollFirst();
                }

                // 아이스크림 먹음
                M--;
                ans.append(cur.idx).append('\n');

                // 민초 먹었으면 방향 전환
                if (cur.amount % MINT == 0) {
                    isReversed = !isReversed;
                }
            }

        }

        System.out.print(ans);
    }//sol
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 전체 아이스크림의 개수 N과 지호가 먹을 아이스크림의 개수 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceCreams = new IceCream[N];

        // N개의 정수 A1, A2, ..., AN이 주어진다. 이때 Ai는 i번 아이스크림의 양을 의미
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int amount = Integer.parseInt(st.nextToken());
            iceCreams[i] = new IceCream(i+1, amount);
        }

        Arrays.sort(iceCreams);

        br.close();
    }//init

    private static class IceCream implements Comparable<IceCream> {
        int idx;
        int amount;

        IceCream(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }

        @Override
        public int compareTo(IceCream i) {
            // 양이 가장 많은 아이스크림이 여러 개라면 가장 왼쪽에 있는 것
            if (this.amount == i.amount) {
                return this.idx - i.idx;
            }
            // 양이 가장 많은 아이스크림을 선택
            return i.amount - this.amount;
        }
    }//IceCream

}//class