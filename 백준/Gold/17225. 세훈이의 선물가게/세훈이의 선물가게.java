import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17225
public class Main {
    private static final int S = 0; // 상민이 포장지 색
    private static int aTime, bTime; // 상민 포장 시간, 지수 포장 시간
    private static List<Integer> aGift, bGift; // 상민이 포장한 선물, 지수가 포장한 선물
    private static Queue<Gift> order; // 주문내역


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();

        packaging(); // 포장
        append(aGift, ans); // 상민이
        append(bGift, ans); // 지수

        System.out.print(ans);
    }//sol


    private static void append(List<Integer> gift, StringBuilder ans) {
        // 포장한 선물의 개수를 출력
        ans.append(gift.size()).append('\n');
        // 포장한 선물들의 번호를 오름차순으로 공백으로 구분하여 출력
        for(int num : gift) {
            ans.append(num).append(' ');
        }

        ans.append('\n');
    }//append


    private static void packaging() {
        int num = 1; // 선물 번호
        Deque<Gift> a = new ArrayDeque<>(); // 상민
        Deque<Gift> b = new ArrayDeque<>(); // 지수
        PriorityQueue<Gift> result = new PriorityQueue<>();

        Gift cur;
        int time;

        while(!order.isEmpty()) {
            cur = order.poll(); // 현재 주문
            time = cur.start; // 주문 시간

            if (cur.color == S) { // 상민
                if (!a.isEmpty()) time = Math.max(time, a.peekLast().end);
                a.add(new Gift(time, time + aTime, cur.color));
                result.add(a.peekLast());
            } else { // 지수
                if (!b.isEmpty()) time = Math.max(time, b.peekLast().end);
                b.add(new Gift(time, time + bTime, cur.color));
                result.add(b.peekLast());
            }
        }

        while(!result.isEmpty()) {
            cur = result.poll();
            
            if(cur.color == S) { // 상민
                aGift.add(num++);
            }
            else { // 지수
                bGift.add(num++);
            }
        }
    }//packaging


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        aTime = Integer.parseInt(st.nextToken()); // 상민이가 선물 하나를 포장하는 데 걸리는 시간
        bTime = Integer.parseInt(st.nextToken()); // 지수가 선물 하나를 포장하는 데 걸리는 시간
        int N = Integer.parseInt(st.nextToken()); // 세훈이 가게의 손님 수

        aGift = new ArrayList<>(); // 상민이 포장한 선물
        bGift = new ArrayList<>(); // 지수가 포장한 선물
        order = new LinkedList<>(); // 주문 내역

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken()); // 주문한 시간
            int color = st.nextToken().charAt(0) == 'B' ? S : 1; // 포장지의 색깔
            int cnt = Integer.parseInt(st.nextToken()); // 주문한 선물의 개수

            for(int j=0; j<cnt; j++) {
                order.add(new Gift(time, time, color));
            }
        }

        br.close();
    }//init


    private static class Gift implements Comparable<Gift>{
        int start; // 시작 시간
        int end; // 끝나는 시간
        int color; // 포장 색
        Gift(int start, int end, int color) {
            this.start = start;
            this.end = end;
            this.color = color;
        }
        @Override
        public int compareTo(Gift o) {
            // 두 사람이 동시에 선물을 가져올 때는 상민이가 먼저 가져옴 
            if(this.start == o.start) return this.color - o.color;
            return this.start - o.start;
        }
    }//Gift


}//class