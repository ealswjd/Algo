import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15787
public class Main {
    private static final int X = 20;
    private static int N; // N개의 기차
    private static int[][] orders; // 명령


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int cnt = 0; // 은하수를 건널 수 있는 기차의 수
        int seat = 1 << X; // 좌석
        int[] trains = new int[N]; // 기차
        boolean[] checked = new boolean[seat]; // 기차 좌석 상태 기록

        for(int[] order : orders) {
            command(order, trains);
        }

        for(int train : trains) {
            if(checked[train]) continue;

            checked[train] = true;
            cnt++;
        }

        System.out.print(cnt);
    }//sol


    private static void command(int[] order, int[] trains) {
        int o = order[0]; // 명령 종류
        int i = order[1]; // 기차 번호
        int x = order[2]; // 좌석 번호
        int train = trains[i]; // i번째 기차 좌석

        switch (o) {
            case 1 :
                // i번째 기차 x번째 좌석에 사람을 태워라.
                train |= (1 << x);
                break;
            case 2 :
                // i번째 기차 x번째 좌석에 앉은 사람은 하차
                train &= ~(1 << x);
                break;
            case 3 :
                // 만약 20번째 자리에 사람이 앉아있었다면 그 사람은 하차
                train &= ~(1 << (X-1));
                // i번째 기차에 앉아있는 승객들이 모두 한칸씩 뒤로
                train <<= 1;
                break;
            case 4 :
                // 만약 1번째 자리에 사람이 앉아있었다면 그 사람은 하차
                train &= ~1;
                // i번째 기차에 앉아있는 승객들이 모두 한칸씩 앞으로
                train >>= 1;
                break;
        }

        trains[i] = train;
    }//command


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N개의 기차
        int M = Integer.parseInt(st.nextToken()); // M개의 명령

        orders = new int[M][3]; // 명령

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken()); // 명령의 종류
            int n = Integer.parseInt(st.nextToken()) - 1; // n번째 기차

            orders[i][0] = o; // 명령의 종류
            orders[i][1] = n; // 번째 기차

            if(o < 3) orders[i][2] = Integer.parseInt(st.nextToken()) - 1; // x번째 좌석
        }

        br.close();
    }//init


    private static void print(int[] trains) {
        StringBuilder ans = new StringBuilder();
        int i = 1;
        for(int train : trains) {
            ans.append(i++).append(' ');
            ans.append(Integer.toBinaryString(train)).append('\n');
        }

        System.out.println(ans);
    }//print


}//class