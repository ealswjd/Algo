import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1940
public class Main {
    static int N, M; // 재료의 개수, 필요한 수
    static int[] numbers; // 재료들이 가진 고유한 번호들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 재료의 개수
        M = Integer.parseInt(br.readLine()); // 갑옷을 만드는데 필요한 수

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int cnt = getCnt(); // 갑옷을 만들 수 있는 개수
        System.out.print(cnt);
    }//main

    private static int getCnt() {
        Arrays.sort(numbers);

        int cnt = 0; // 갑옷을 만들 수 있는 개수
        int a = 0;   // 재료1 인덱스
        int b = N-1; // 재료2 인덱스
        int sum;     // 두 번호의 합

        while(a < b) {
            sum = numbers[a] + numbers[b];

            if(sum < M) a++;
            else {
                if(sum == M) cnt++;
                b--;
            }
        }

        return cnt;
    }//getCnt

    private static void init() {
        numbers = new int[N];
    }//init

}//class