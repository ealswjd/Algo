import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26091
public class Main {
    static int N, M; // 학회원의 수, 팀의 최소 능력치
    static int[] power; // 능력치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 견학을 희망하는 학회원의 수
        M = Integer.parseInt(st.nextToken()); // 팀의 최소 능력치

        power = new int[N]; // 능력치
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }//for
        br.close();

        int cnt = getCnt(); // 최대 팀 수
        System.out.print(cnt);
    }//main

    private static int getCnt() {
        if(N < 2) return 0; // 조건 : 팀원이 두 명

        Arrays.sort(power);
        int cnt = 0;
        int start = 0;
        int end = N-1;
        int sum;

        while(start < end) {
            sum = power[start] + power[end];

            if(sum < M) start++;
            else { // 능력치 만족
                cnt++;
                start++;
                end--;
            }

        }//while

        return cnt;
    }//getCnt

}//class