import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10819
public class Main {
    static int N; // 정수 개수
    static int max; // 식의 최댓값
    static int[] A; // 배열 A

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정수 개수

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        getMax(0, 0, 0, 0); // 개수, 총합, 이전값, 사용 여부

        System.out.print(max);
    }//main

    // 식의 최댓값 구하기
    private static void getMax(int cnt, int sum, int prev, int checked) {
        if(cnt == N) {
            max = Math.max(max, sum); // 최댓값 갱신
            return;
        }

        for(int i=0; i<N; i++) {
            if((checked & (1 << i)) != 0) continue; // 이미 사용한 숫자
            
            if(cnt == 0) {
                getMax(cnt+1, 0, A[i], checked | (1 << i));
            }
            else {
                getMax(cnt+1, sum + Math.abs(prev - A[i]), A[i], checked | (1 << i));
            }
        }
    }//getMax

    private static void init() {
        A = new int[N];
    }//init

}//class