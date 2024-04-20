import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 달력 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/20207
 * */
public class Main {
    static final int D=365;
    static int N, S, E;
    static int[] calendar;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 일정의 개수

        init();

        S = D;
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken())+1;
            calendar[s]++;
            calendar[e]--;

            S = Math.min(S, s);
            E = Math.max(E, e);
        }
        br.close();

        cumulative();
        int area = getArea();
        System.out.print(area);
    }//main


    private static int getArea() {
        int sum = 0;
        int cnt, h;

        for(int i=S; i<=E; i++) {
            cnt = 0;
            h = 0;

            while(i+1<=E && calendar[i] > 0) {
                cnt++;
                h = Math.max(h, calendar[i++]);
            }

            sum += cnt * h;
        }

        return sum;
    }//getArea


    private static void cumulative() {
        for(int i=S; i<=E; i++) {
            calendar[i] += calendar[i-1];
        }
    }//cumulative

    
    private static void init() {
        calendar = new int[D+2]; // (1 ≤ S ≤ E ≤ 365)
    }//init

}//class