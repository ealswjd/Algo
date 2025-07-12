import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16927
public class Main {
    private static int N, M; // 배열의 크기
    private static int R; // 회전의 수
    private static int[][] arr; // N×M 배열


    public static void main(String[] args) throws IOException {
        init();
        rotation();
        print();
    }//main


    private static void rotation() {
        int boxCnt = Math.min(N, M) / 2; // 부분 배열 개수
        int r = N, c = M;

        for(int i=0; i<boxCnt; i++) {
            rotation(i, r * 2 + c * 2 - 4);
            r -= 2;
            c -= 2;
        }

    }//rotation


    private static void rotation(int start, int len) {
        int[] line = getLine(start, len);
        int idx = R % len;

        // 상
        for(int c=start; c<M-start; c++) {
            arr[start][c] = line[idx%len];
            idx++;
        }
        // 우
        for(int r=start+1; r<=N-start-2; r++) {
            arr[r][M-start-1] = line[idx%len];
            idx++;
        }
        // 하
        for(int c=M-start-1; c>=start; c--) {
            arr[N-start-1][c] = line[idx%len];
            idx++;
        }
        // 좌
        for(int r=N-start-2; r>start; r--) {
            arr[r][start] = line[idx%len];
            idx++;
        }

    }//rotation


    private static int[] getLine(int start, int len) {
        int[] line = new int[len];

        int idx = 0;

        // 상
        for(int c=start; c<M-start; c++) {
            line[idx++] = arr[start][c];
        }
        // 우
        for(int r=start+1; r<=N-start-2; r++) {
            line[idx++] = arr[r][M-start-1];
        }
        // 하
        for(int c=M-start-1; c>=start; c--) {
            line[idx++] = arr[N-start-1][c];
        }
        // 좌
        for(int r=N-start-2; r>start; r--) {
            line[idx++] = arr[r][start];
        }

        return line;
    }//getLine


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                ans.append(arr[i][j]).append(' ');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()); // 회전 횟수

        arr = new int[N][M]; // 배열

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class