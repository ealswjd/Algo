import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15980
public class Main {
    private static int N, M; // 새는 N마리, 스승님은 총 M초간 명상
    private static int[] directions; // 각 새의 방향 정보
    private static int[][] birds; // 각 새의 지저귐


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[] times = new int[M]; // 시간별 정보

        for(int t=0; t<M; t++) {
            int sum = 0;
            for(int i=0; i<N; i++) {
                if(birds[i][t] == 1) {
                    sum += directions[i];
                }
            }
            times[t] = sum;
            if(t > 0) times[t] += times[t-1];
        }

        int number = 0;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            int max = 0;
            int sum = 0;
            for(int t=0; t<M; t++) {
                sum += directions[i] * birds[i][t];
                int cur = Math.abs(times[t] - sum);
                max = Math.max(max, cur);
            }

            if(max < min) {
                number = i + 1;
                min = max;
            }
        }

        System.out.printf("%d\n%d", number, min);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 새는 N마리
        M = Integer.parseInt(st.nextToken()); // 스승님은 총 M초간 명상

        directions = new int[N]; // 각 새의 방향 정보
        birds = new int[N][M]; // 각 새의 지저귐

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char d = st.nextToken().charAt(0); // 새의 방향
            char[] bird = st.nextToken().toCharArray(); // 새가 지저귀는 시간
            directions[i] = (d == 'L') ? -1 : 1;

            for(int t=0; t<M; t++) {
                birds[i][t] = bird[t] - '0';
            }
        }

        br.close();
    }//init


}//class