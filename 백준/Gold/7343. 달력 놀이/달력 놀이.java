import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7343
public class Main {
    private static final int SY = 1900, Y = 2001, M = 12, D = 31;
    private static final int S = 19000101, E = 20011104; // 시작, 끝
    private static final int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30
                                                , 31, 31, 30, 31, 30, 31};
    private static boolean[][][] dp; //  y/m/d 에 상범이의 승리 여부


    public static void main(String[] args) throws IOException {
        init();
        sol();
//        print();
    }//main


    private static void sol() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()); // 연도
            int m = Integer.parseInt(st.nextToken()); // 월
            int d = Integer.parseInt(st.nextToken()); // 일
            int ymd = getYMD(y, m, d);

            if(ymd == E || dp[y][m][d]) ans.append("YES\n");
            else ans.append("NO\n");
        }

        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new boolean[Y+1][M+1][D+1]; // y/m/d 에 상범이의 승리 여부

        for(int y=Y; y>=SY; y--) {
            for(int m=M; m>=1; m--) {
                for(int d=D; d>=1; d--) {
                    if(!isValidDate(y, m, d)) continue;

                    // 현재 날짜의 다음 날로 이동하거나,
                    boolean nextDay = getNextDay(y, m, d);
                    // 다음 달의 현재 날짜와 동일한 날짜를 갖는 날로 이동하는 것 중 한 가지
                    boolean nextMonth = getNextMonth(y, m, d);

                    // 둘 중 하나라도 이길 수 있으면 승리
                    dp[y][m][d] = nextDay || nextMonth;
                }
            }
        }

    }//init


    private static boolean getNextDay(int y, int m, int d) {
        // 다음날
        d++;

        // 달의 마지막 날일 경우 그 다음달 1일
        if(daysInMonth[m] < d) {
            d = 1;
            m++;
            // 12월이면 다음해 1월
            if(M < m) {
                m = 1;
                y++;
            }
        }

        return isWin(y, m, d);
    }//getPrevDay


    private static boolean getNextMonth(int y, int m, int d) {
        // 다음 달
        m++;

        // 12월이면 다음해 1월
        if(M < m) {
            m = 1;
            y++;
        }

        return isWin(y, m, d);
    }//getNextMonth


    private static boolean isWin(int y, int m, int d) {
        // 유효한 날짜가 아님
        if(!isValidDate(y, m, d)) return false;

        // 이동할 수 있는 날짜가 지는 상태거나 목표에 도달하면 이김
        return !dp[y][m][d] || getYMD(y, m, d) == E;
    }//isWin


    private static boolean isValidDate(int y, int m, int d) {
        int ymd = getYMD(y, m, d);

        // 날짜 범위 체크
        if(ymd < S || E < ymd) return false;
        // 윤년 체크
        if(m == 2 && isLeapYear(y)) return d <= daysInMonth[m] + 1;

        // 해당 달의 마지막날 체크
        return d <= daysInMonth[m];
    }//isValidDate


    private static int getYMD(int y, int m, int d) {
        return y * 10000 + m * 100 + d;
    }//getYMD


    // 윤년은 연도가 4의 배수이면서, 100의 배수가 아닐 때 또는 400의 배수일 때
    private static boolean isLeapYear(int y) {
        if(y % 400 == 0) return true;
        return y % 4 == 0 && y % 100 != 0;
    }//isLeapYear


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int y=SY; y<=Y; y++) {
            for(int m=1; m<=M; m++) {
                for(int d=1; d<=D; d++) {
                    if(!isValidDate(y, m, d)) continue;

                    ans.append(y).append(' ');
                    ans.append(m).append(' ');
                    ans.append(d).append(' ');
                    ans.append(dp[y][m][d]).append('\n');
                }
            }
        }

        System.out.print(ans);
    }//print


}//class