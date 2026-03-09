import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2632
public class Main {
    private static final int MAX_SIZE = 2_000_000; // 손님이 구매하고자 하는 피자 최대값
    private static int P; // 손님이 구매하고자 하는 피자크기
    private static int[] A, B; // 피자 a, b에서 나올 수 있는 크기의 방법 수

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int result = 0; // 피자를 판매하는 방법의 가지 수

        for(int size = 0; size<= P; size++) {
            result += A[size] * B[P - size];
        }

        System.out.print(result);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine()); // 손님이 구매하고자 하는 피자크기

        // A, B 피자의 피자조각의 개수를 나타내는 정수 차례로 주어진다 (3 ≤ m, n ≤ 1000).
        StringTokenizer st = new StringTokenizer(br.readLine());
        int aCnt = Integer.parseInt(st.nextToken());
        int bCnt = Integer.parseInt(st.nextToken());

        int[] pizzaA = new int[aCnt]; // 피자 A의 피자조각의 크기
        int[] pizzaB = new int[bCnt]; // 피자 B의 피자조각의 크기
        int totalA = 0;
        int totalB = 0;

        // 피자 A의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다.
        for(int i=0; i<aCnt; i++) {
            pizzaA[i] = Integer.parseInt(br.readLine());
            totalA += pizzaA[i];
        }
        // 피자 B의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다.
        for(int i=0; i<bCnt; i++) {
            pizzaB[i] = Integer.parseInt(br.readLine());
            totalB += pizzaB[i];
        }

        br.close();

        A = new int[MAX_SIZE + 1]; // 피자 a에서 나올 수 있는 크기의 방법 수
        B = new int[MAX_SIZE + 1]; // 피자 b에서 나올 수 있는 크기의 방법 수

        setCount(aCnt, totalA, pizzaA, A);
        setCount(bCnt, totalB, pizzaB, B);
    }//init

    private static void setCount(int size, int total, int[] pizza, int[] count) {
        count[0] = count[total] = 1;

        for(int i=0; i<size; i++) {
            int sum = pizza[i];
            count[sum]++;
            for(int cnt=1; cnt<size-1; cnt++) {
                sum += pizza[(i + cnt) % size];
                count[sum]++;
            }
        }
    }//setCount

}//class