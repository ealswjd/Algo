import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    제목 : 전구와 스위치 (골드 5)
    링크 : https://www.acmicpc.net/problem/2138
 */
public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] cur = new int[N]; // 전구들의 현재 상태
        int[] target = new int[N]; // 만들고자 하는 전구들의 상태

        init(br.readLine(), cur);
        init(br.readLine(), target);
        br.close();

        int[] tmp = Arrays.copyOf(cur, N); // 1번 스위치 누른 상태
        tmp[0] = 1 - tmp[0];
        tmp[1] = 1 - tmp[1];

        int off = change(cur, target); // 1번 스위치 안 누름
        int on = change(tmp, target); // 1번 스위치 누름
        if(on != -1) on++;

        if(off == -1) System.out.print(on);
        else if(on == -1) System.out.print(off);
        else System.out.print(Math.min(off, on));
    }//main

    private static int change(int[] cur, int[] target) {
        int cnt = 0; // 스위치 누른 횟수

        for(int i=0; i<N-1; i++) {
            if(cur[i] != target[i]) { // 목표 상태랑 다를 경우
                cnt++; // 스위치 누름

                // 상태 변경
                cur[i] = 1 - cur[i];
                cur[i+1] = 1 - cur[i+1];
                if(i < N-2) cur[i+2] = 1 - cur[i+2];
            }
        }//for

        if(cur[N-1] != target[N-1]) return -1;

        return cnt;
    }//change

    private static void init(String str, int[] arr) {
        for(int i=0; i<N; i++) {
            arr[i] = str.charAt(i) - '0';
        }//for
    }//init

}//class