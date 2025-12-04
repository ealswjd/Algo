import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1756
public class Main {
    private static final int MAX = 1_000_000_000;
    private static int N, D; // 피자 반죽의 개수 N, 오븐의 깊이 D
    private static int[] oven, pizza; // 깊이에 따른 오븐의 지름, 피자 반죽 지름


    public static void main(String[] args) throws IOException {
        init();
        int depth = getDepth();

        System.out.print(depth);
    }//main

    /*
     마지막 피자 반죽의 위치를 출력(오븐의 최상단이 1이고, 최하단 가장 깊은 곳이 D)
     피자가 모두 오븐에 들어가지 않는다면, 0을 출력
     */
    private static int getDepth() {
        int lastDepth = 0; // 마지막 피자 반죽의 위치
        int depth = D; // 최하단부터 채우기
        int cur = 0; // 현재 피자 번호

        while (depth > 0 && cur < N) {
            if (oven[depth] >= pizza[cur]) {
                lastDepth = depth;
                cur++; // 다음 피자로               
            }
            depth--;
        }

        return cur == N ? lastDepth : 0;
    }//getDepth


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 오븐의 깊이 D와 피자 반죽의 개수 N이 공백을 사이에 두고 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        oven = new int[D+1]; // 깊이에 따른 오븐의 지름
        pizza = new int[N]; // 피자 반죽 지름

        oven[0] = MAX;
        // 오븐의 최상단부터 시작하여 깊이에 따른 오븐의 지름이 차례대로 주어진다
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=D; i++) {
            oven[i] = Math.min(oven[i-1], Integer.parseInt(st.nextToken()));
        }

        // 피자 반죽이 완성되는 순서대로, 그 각각의 지름이 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class