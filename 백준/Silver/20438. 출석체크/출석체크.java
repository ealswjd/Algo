import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20438
public class Main {
    private static final int START = 3; // 입장 번호 3번부터
    private static final int S = 0, E = 1; // 시작, 끝
    private static int[][] query; // 구간의 범위
    private static int[] student; // 학생 출결 정보


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int s, e, cnt;

        for(int[] q : query) {
            s = q[S]; // 구간 시작
            e = q[E]; // 구간 끝
            cnt = student[e] - student[s-1]; // 구간에 대해서 출석이 되지 않은 학생들의 수

            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 학생의 수 N, 졸고 있는 학생의 수 K, 출석 코드를 보낼 학생의 수 Q, 주어질 구간의 수 M
        int N = Integer.parseInt(st.nextToken()) + START; // 학생의 수
        int K = Integer.parseInt(st.nextToken()); // 졸고 있는 학생의 수
        int Q = Integer.parseInt(st.nextToken()); // 출석 코드를 보낼 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 주어질 구간의 수

        student = new int[N]; // 학생 출결 정보
        query = new int[M][2]; // 구간의 범위
        boolean[] isDozing = new boolean[N]; // 졸고 있는 학생

        Arrays.fill(student, 1);
        student[0] = student[1] = student[2] = 0;

        // K명의 졸고 있는 학생의 입장 번호들
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int n = Integer.parseInt(st.nextToken()); // 졸고 있는 학생의 입장 번호
            isDozing[n] = true;
        }

        // Q명의 출석 코드를 받을 학생의 입장 번호들이 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            int n = Integer.parseInt(st.nextToken()); // 출석 코드를 받을 학생의 입장 번호
            if(isDozing[n]) continue; // 조느라 출석 코드 못 보냄

            // 본인의 입장 번호의 배수인 학생들에게 출석 코드를 보냄
            for(int j=n; j<N; j+=n) {
                if(isDozing[j]) continue; // 조느라 출석 X
                student[j] = 0;
            }
        }

        // M개의 줄 동안 구간의 범위 S, E가 공백을 사이에 두고 주어진다.
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][S] = Integer.parseInt(st.nextToken()); // 구간 시작
            query[i][E] = Integer.parseInt(st.nextToken()); // 구간 끝
        }

        // 누적합
        for(int i=START + 1; i<N; i++) {
            student[i] += student[i-1];
        }

        br.close();
    }//init


}//class