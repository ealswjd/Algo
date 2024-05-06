import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6208
public class Main {
    static int L, N, B; // 길이, 재료 개수, 예산
    static int[] X, W, F, C; // 위치, 길이, 재미, 비용
    static ArrayList<ArrayList<Integer>> xList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 롤러코스터 길이
        N = Integer.parseInt(st.nextToken()); // 재료 개수
        B = Integer.parseInt(st.nextToken()); // 예산

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken()); // 위치
            W[i] = Integer.parseInt(st.nextToken()); // 길이
            F[i] = Integer.parseInt(st.nextToken()); // 재미
            C[i] = Integer.parseInt(st.nextToken()); // 비용
            xList.get(X[i]).add(i);
        }
        br.close();

        int maxFun = getMax();
        System.out.print(maxFun);
    }//main


    private static int getMax() {
        int max = -1;
        int[][] dp = new int[L+1][B+1];
        for(int i=0; i<=L; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for(int l=0; l<L; l++) {
            for(int b=0; b<=B; b++) {
                if(dp[l][b] != -1) {
                    for(int x : xList.get(l)) {
                        if(C[x] + b <= B) {
                            dp[l+W[x]][b+C[x]] = Math.max(dp[l+W[x]][b+C[x]], dp[l][b]+F[x]);
                        }
                    }
                }
            }
        }

        for(int i=0; i<=B; i++) {
            max = Math.max(max, dp[L][i]);
        }

        return max;
    }//getMax

    private static void init() {
        X = new int[N]; // 위치
        W = new int[N]; // 길이
        F = new int[N]; // 재미
        C = new int[N]; // 비용
        xList = new ArrayList<>(L);
        for(int i=0; i<L; i++) {
            xList.add(new ArrayList<>());
        }
    }//init

}//class

/*
2 3 100
0 1 1 1
0 1 100 100
1 1 1 1
---
2
 */