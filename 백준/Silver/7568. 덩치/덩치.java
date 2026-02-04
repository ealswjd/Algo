import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7568
public class Main {
    private static int N; // 사람의 수
    private static int[] height, weight; // 각 사람의 키와 몸무게

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int cnt;

        for(int cur=0; cur<N; cur++) {
            cnt = 1;
            for(int i=0; i<N; i++) {
                if (cur == i) continue;
                if (height[cur] < height[i] && weight[cur] < weight[i]) {
                    cnt++;
                }
            }

            ans.append(cnt).append(' ');
        }

        System.out.print(ans);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        weight = new int[N];
        height = new int[N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            height[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class