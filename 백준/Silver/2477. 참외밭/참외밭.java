import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2477
public class Main {
    private static final int M = 6, E=1, W=2, DIR=0, LEN=1;
    private static int K; // 1m2의 넓이에 자라는 참외의 개수
    private static int maxH, maxW;
    private static int[][] side; // 변 정보
    

    public static void main(String[] args) throws IOException {
        init();
        int cnt = getCnt();

        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int area = maxH * maxW;
        int cnt = 0;
        int idx = 2;
        int min = side[0][LEN] * side[M-1][LEN];

        while(idx < M && cnt < 3) {
            int dir = side[idx][DIR];
            int len = side[idx][LEN];
            int prev = idx - 1;
            int next = (idx + 1) % M;

            if(dir == side[idx-2][DIR]) {
                if(side[prev][DIR] == side[next][DIR]) {
                    min = len * side[prev][LEN];
                } else {
                    min = side[idx-2][LEN] * side[prev][LEN];
                }
                break;
            }

            cnt++;
            idx += 2;
            if(idx == M) {
                idx = M - 1;
            }
        }

        return (area - min) * K;
    }//getCnt

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); // 1m2의 넓이에 자라는 참외의 개수
        side = new int[M][2]; // 변 정보

        StringTokenizer st;
        for(int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            if(dir == E || dir == W) {
                maxW = Math.max(maxW, len);
            } else {
                maxH = Math.max(maxH, len);
            }

            side[i][DIR] = dir;
            side[i][LEN] = len;
        }

        br.close();
    }//init
    

}//class