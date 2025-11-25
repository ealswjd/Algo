import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5676
public class Main {
    private static int N; // 수열의 크기
    private static int[] numbers; // N개의 숫자
    private static int[] seg; // 구간곱


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        String input = "";

        while((input = br.readLine()) != null && !input.isEmpty()) {
            // 수열의 크기 N과 게임의 라운드 수 K가 주어진다. (1 ≤ N, K ≤ 105)
            st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken()); // 수열의 크기
            int K = Integer.parseInt(st.nextToken()); // 게임의 라운드 수

            init(br.readLine()); // 초기화

            // K번 명령 실행
            while(K-- > 0) {
                st = new StringTokenizer(br.readLine());
                char q = st.nextToken().charAt(0); // C:변경 P:곱셈
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(q == 'C') { // 변경 : a번째 수를 b로 변경
                    b = Integer.compare(b, 0);
                    if(b != numbers[a]) {
                        numbers[a] = b;
                        update(1, 1, N, a, b);
                    }
                } else { // 곱셈 : a번째 수부터 b번째 수까지 곱한 결과 확인
                    int resultNum = getNum(1, 1, N, a, b);
                    char result = '0';

                    if(resultNum > 0) result = '+'; // 양수인 경우에는 +
                    else if(resultNum < 0) result = '-'; // 음수인 경우에는 -

                    ans.append(result);
                }
            }

            ans.append("\n");
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static int getNum(int cur, int start, int end, int a, int b) {
        if(b < start || end < a) {
            return 1;
        }
        if(a <= start && end <= b) {
            return seg[cur];
        }

        int mid = (start + end) / 2;

        return getNum(cur * 2, start, mid, a, b)
                * getNum(cur * 2 + 1, mid + 1, end, a, b);
    }//getNum


    private static int update(int cur, int start, int end, int idx, int val) {
        if(idx < start || end < idx) {
            return seg[cur];
        }
        if(start == end) {
            return seg[cur] = val;
        }

        int mid = (start + end) / 2;

        return seg[cur] = update(cur * 2, start, mid, idx, val)
                            * update(cur * 2 + 1, mid + 1, end, idx, val);
    }//update


    private static void build(int cur, int start, int end) {
        if(start == end) {
            seg[cur] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;

        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);

        seg[cur] = seg[cur*2] * seg[cur*2+1];
    }//build


    private static void init(String input) {
        numbers = new int[N+1]; // N개의 숫자
        seg = new int[N*4]; // 구간곱

        // 총 N개의 숫자 Xi가 주어진다. (-100 ≤ Xi ≤ 100)
        StringTokenizer st = new StringTokenizer(input);
        for(int i=1; i<=N; i++) {
            int n = Integer.parseInt(st.nextToken());
            numbers[i] = Integer.compare(n, 0);
        }

        build(1, 1, N);
    }//init


}//class