import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20303
public class Main {
    private static int N, K;
    private static int[] parent; // 친구
    private static int[] count; // 인원수
    private static int[] candies; // 사탕 개수

    
    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int[] dp = new int[K];

        for(int i=0; i<N; i++) {
            if(i != parent[i]) continue;

            int cnt = count[i];
            for(int k=K-1; k>=cnt; k--) {
                dp[k] = Math.max(dp[k], dp[k - cnt] + candies[i]);
            }
        }

        return dp[K-1];
    }//getMax

    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a <= b) parent[b] = a;
        else parent[a] = b;
    }//union

    
    private static int find(int n) {
        if(n == parent[n]) return n;

        return parent[n] = find(parent[n]);
    }//find

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이들의 수
        int M = Integer.parseInt(st.nextToken()); // 관계의 수
        K = Integer.parseInt(st.nextToken()); // 울음소리가 공명하기 위한 최소 아이의 수

        int[] candyArr = new int[N];
        parent = new int[N];

        // 아이들이 받은 사탕의 수가 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            candyArr[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            union(a, b);
        }

        br.close();

        count = new int[N];
        candies = new int[N];
        for(int i=0; i<N; i++) {
            int p = find(i);
            count[p]++;
            candies[p] += candyArr[i];
        }

    }//init

    
}//class