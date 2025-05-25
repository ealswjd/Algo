import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1920
public class Main {
    private static int N;
    private static int[] A; // N개의 정수 A[1], A[2], …, A[N]
    private static int[] X; // 이 수들이 A안에 존재하는지


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int result;

        for(int x : X) {
            result = getResult(x);

            ans.append(result).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static int getResult(int x) {
        int start = 0;
        int end = N-1;
        int mid;

        // 존재하면 1, 존재하지 않으면 0
        while(start <= end) {
            mid = (start + end) / 2;

            if(A[mid] == x) return 1;
            else if(A[mid] < x) start = mid + 1;
            else end = mid - 1;
        }

        return 0;
    }//getResult


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        // N개의 정수 A[1], A[2], …, A[N]이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        X = new int[M]; // 이 수들이 A안에 존재하는지

        // M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        br.close();
    }//init


}//class