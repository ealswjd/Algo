import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6208
public class Main {
    static final int X=0, W=1, F=2, C=3; // 위치, 길이, 재미, 비용
    static int L, N, B; // 길이, 재료 개수, 예산
    static int[][] components;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 롤러코스터 길이
        N = Integer.parseInt(st.nextToken()); // 재료 개수
        B = Integer.parseInt(st.nextToken()); // 예산
        components = new int[N][4];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            components[i][X] = Integer.parseInt(st.nextToken()); // 위치
            components[i][W] = Integer.parseInt(st.nextToken()); // 길이
            components[i][F] = Integer.parseInt(st.nextToken()); // 재미
            components[i][C] = Integer.parseInt(st.nextToken()); // 비용
        }
        br.close();

        int maxFun = getMax();
        System.out.print(maxFun);
    }//main


    private static int getMax() {
        int max = 0;
        int[][] dp = new int[L+1][B+1];
        int end;

        Arrays.sort(components, Comparator.comparingInt(o -> o[X]));
        for(int[] component : components) {
            if(component[X] == 0) {
                dp[component[W]][B - component[C]] = component[F];
            }
        }

        for(int[] component : components) {
            end=component[X]+component[W];
            for(int b=0; b<=B; b++) {
                if(dp[component[X]][b] == 0) continue;
                if(b-component[C] >= 0) {
                    dp[end][b-component[C]] = Math.max(dp[end][b-component[C]]
                            , dp[component[X]][b] + component[F]);
                }
            }
        }

        for(int i=0; i<=B; i++) {
            max = Math.max(max, dp[L][i]);
        }

        return max == 0 ? -1 : max;
    }//getMax

}//class

/*
2 3 100
0 1 1 1
0 1 100 100
1 1 1 1
---
2
 */