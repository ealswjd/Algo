import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16508
public class Main {
    private static final int MAX = Integer.MAX_VALUE, ALPHA = 26;
    private static int N; // 전공책의 개수
    private static int[] targetCount; // 만들고자 하는 단어를 위해 필요한 알파벳 개수
    private static int minCost; // 전공책의 가장 적은 가격의 합
    private static Book[] books; // 전공책


    public static void main(String[] args) throws IOException {
        init();
        comb(0, 0, new int[ALPHA]);

        System.out.print(minCost == MAX ? -1 : minCost);
    }//main


    private static void comb(int cur, int used, int[] count) {
        if(cur == N) {
            if(isAvailable(count)) {
                int cost = getCost(used);
                minCost = Math.min(minCost, cost);
            }
            return;
        }

        // 현재 책 사용 x
        comb(cur+1, used, count);

        // 현재 책 사용 o
        int[] newCount = Arrays.copyOf(count, ALPHA);
        for(char c : books[cur].title) {
            newCount[c-'A']++;
        }
        comb(cur+1, used | (1 << cur), newCount);
    }//comb


    private static int getCost(int used) {
        int cost = 0; // 원하는 단어를 만들 수 있는 비용

        for(int i=0; i<N; i++) {
            if((used & (1 << i)) != 0) {
                cost += books[i].cost;
            }
        }

        return cost;
    }//getCost


    private static boolean isAvailable(int[] count) {
        // 민호가 원하는 알파벳을 만들 수 있는지 확인
        for(int i = 0; i< ALPHA; i++) {
            if(targetCount[i] > count[i]) return false;
        }

        return true;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] target = br.readLine().toCharArray(); // 민호가 만들고자 하는 단어
        targetCount = new int[ALPHA]; // 만들고자 하는 단어를 위해 필요한 알파벳 개수
        N = Integer.parseInt(br.readLine());  // 전공책의 개수
        books = new Book[N]; // 전공책
        minCost = MAX;

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken()); // 전공책 가격
            char[] title = st.nextToken().toCharArray(); // 제목

            books[i] = new Book(cost, title);
        }

        for(char c : target) {
            targetCount[c - 'A']++;
        }

        br.close();
    }//init


    private static class Book {
        int cost; // 가격
        char[] title; // 제목

        Book(int cost, char[] title) {
            this.cost = cost;
            this.title = title;
        }
    }//Book



}//class