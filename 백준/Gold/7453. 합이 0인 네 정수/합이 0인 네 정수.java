import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7453
public class Main {
    private static int N, NN; // 배열의 크기
    private static int[] AB, CD; // [A,B]의 합, [C,D]의 합

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int ab = 0; // AB 인덱스 - 앞부터
        int cd = NN-1; // CD 인덱스 - 뒤부터
        long result = 0; // 합이 0이 되는 쌍의 개수

        while(ab < NN && cd >= 0) {
            int sum = AB[ab] + CD[cd]; // 배열 A, B, C, D의 합

            if (sum == 0) { // 합이 0
                int curA = AB[ab], curC = CD[cd];
                long cntA = 1, cntC = 1;

                while(++ab < NN && curA == AB[ab]) {
                    cntA++;
                }
                while(--cd >= 0 && curC == CD[cd]) {
                    cntC++;
                }

                result += cntA * cntC;
            } else if (sum < 0) { // 0보다 작음
                ab++;
            } else { // 0보다 큼
                cd--;
            }
        }

        System.out.print(result);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 배열의 크기
        NN = N * N;

        int[] A = new int[N]; int[] B = new int[N];
        int[] C = new int[N]; int[] D = new int[N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            // A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다.
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        AB = new int[NN];
        CD = new int[NN];

        setArray(A, B, AB);
        setArray(C, D, CD);

        br.close();
    }//init

    private static void setArray(int[] arr1, int[] arr2, int[] result) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int idx = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                result[idx++] = arr1[i] + arr2[j];
            }
        }

        Arrays.sort(result);
    }//setArray

}//class