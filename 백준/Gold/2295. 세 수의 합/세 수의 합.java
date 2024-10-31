import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2295
public class Main {
    private static int N; // 원소 개수
    private static int[] U; // 집합 U
    private static Set<Integer> sum;

    
    public static void main(String[] args) throws IOException {
        init();

        int k = getMax();
        System.out.print(k);
    }//main

    
    private static int getMax() {
        int k = 0;

        out:for(int i=N-1; i>0; i--) {
            k = U[i];
            for(int j=i-1; j>=0; j--) {
                if(sum.contains(k - U[j])) break out;
            }
        }

        return k;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 원소 개수
        U = new int[N];
        sum = new HashSet<>();

        for(int i=0; i<N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<N; i++) {
            for(int j=i; j<N; j++) {
                sum.add(U[i] + U[j]);
            }
        }

        Arrays.sort(U);

        br.close();
    }//init

    
}//class