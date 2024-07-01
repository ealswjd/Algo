import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23061
public class Main {
    static int N, M;
    static int[] W, V, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물품의 수
        M = Integer.parseInt(st.nextToken()); // 가방의 수

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken()); // 물건의 무게
            V[i] = Integer.parseInt(st.nextToken()); // 물건의 가치
        }

        int max = 0;
        for(int i=0; i<M; i++) {
            K[i] = Integer.parseInt(br.readLine()); // 가방이 버틸 수 있는 최대 무게
            max = Math.max(max, K[i]);
        }

        int number = getNumber(max);
        bw.write(String.valueOf(number));
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int getNumber(int max) {
        int[] dp = new int[max+1];

        for(int i=0; i<N; i++) {
            for(int k=max; k>=W[i]; k--) {
                dp[k] = Math.max(dp[k], dp[k-W[i]] + V[i]);
            }
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]) return Double.compare(o1[0], o2[0]);
            return Double.compare(o2[1], o1[1]);
        });
        
        for(int i=0; i<M; i++) {
            double val = (double) dp[K[i]] / K[i];
            pq.offer(new double[] {i+1, val});
        }

        return (int) (pq.poll()[0]);
    }//getNumber

    private static void init() {
        W = new int[N]; // 각 물건의 무게
        V = new int[N]; // 각 물건의 가치
        K = new int[M]; // 가방이 버틸 수 있는 최대 무게
    }//init

}//class