import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/1174
public class Main {
    static final int INF = 10; // 9876543210이 최대 (10자릿수)
    static int N;
    static List<Long> numbers; // 줄어드는 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long ans = 0;

        init();

        for(int i=0; i<INF; i++) {
            comb(i, 1);
        }

        Collections.sort(numbers);

        if(N > numbers.size()) ans = -1; // N번째 줄어드는 수 없음
        else ans = numbers.get(N-1);     // 1번째 줄어드는 수는 0부터
        System.out.print(ans);
    }//main

    
    private static void comb(long num, int depth) {
        if(depth > INF) return; // 자릿수가 10을 넘을 수 없음 (9876543210)
        numbers.add(num);

        // num의 마지막 자리 숫자보다 작은 숫자를 뒤에 붙여서 줄어드는 수 생성
        for(int i=0; i<num%10; i++) {
            comb(num * 10 + i, depth + 1);
        }
    }//comb

    
    private static void init() {
        numbers = new ArrayList<>();
    }//init

    
}//class