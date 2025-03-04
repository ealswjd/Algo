import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15661
public class Main {
    private static int N; // 인원수
    private static int total; // 총 인원 선택 비트
    private static int[][] score; // i와 j가 같은 팀에 속했을 때 팀에 더해지는 능력치
    private static int min; // 능력치의 차이의 최솟값


    public static void main(String[] args) throws IOException {
        init();

        // 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력
        getMin(0, 0);
        System.out.print(min);
    }//main


    private static void getMin(int cur, int checked) {
        if(1 <= checked && checked < total) {
            min = Math.min(min, getDiff(checked));
        }

        for(int i=cur; i<N; i++) {
            getMin(i + 1, checked | (1 << i));
        }
    }//getMin


    private static int getDiff(int checked) {
        int start = 0; // 스타트팀 점수
        int link = 0;  // 링크팀 점수
        boolean isStartI, isStartJ;

        for(int i=0; i<N; i++) {
            isStartI = (checked & (1 << i)) != 0; // i가 스타트팀

            for(int j=0; j<N; j++) {
                isStartJ = (checked & (1 << j)) != 0; // j가 스타트팀

                // 스타트팀
                if(isStartI && isStartJ) {
                    start += score[i][j];
                }
                // 링크팀
                else if(!isStartI && !isStartJ) {
                    link += score[i][j];
                }
            }
        }

        // 능력차이
        return Math.abs(start - link);
    }//getDiff


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 인원수

        score = new int[N][N];   // i와 j가 같은 팀에 속했을 때 팀에 더해지는 능력치
        min = Integer.MAX_VALUE; // 능력치의 차이의 최솟값
        total = (1 << N) - 1;    // 총 인원 선택 비트

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class