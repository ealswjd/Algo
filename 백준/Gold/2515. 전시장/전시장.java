import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2515
public class Main {
    private static final int H = 20_000_001;
    private static int N; // 그림의 개수
    private static int S; // 판매가능 그림 높이 기준
    private static int maxH;
    private static int[] maxCost;


    public static void main(String[] args) throws IOException {
        init();

        int maxCost = getMaxCost();
        System.out.print(maxCost);
    }//main


    private static int getMaxCost() {
        for(int i=S; i<=maxH; i++) {
            maxCost[i] = Math.max(maxCost[i-1], maxCost[i - S] + maxCost[i]);
        }

        return maxCost[maxH];
    }//getMaxCost


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 그림의 개수
        S = Integer.parseInt(st.nextToken()); // 판매가능 그림 높이 기준

        maxCost = new int[H];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken()); // 높이
            int cost = Integer.parseInt(st.nextToken()); // 가격

            maxH = Math.max(maxH, height);
            maxCost[height] = Math.max(maxCost[height], cost);
        }

        br.close();
    }//init


}//class