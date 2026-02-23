import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1799
public class Main {
    private static int blackMax, whiteMax; // 검은색/ 흰색 최댓값
    private static List<Bishop> blackList, whiteList; // 검은색/흰색 비숍 정보
    private static boolean[] usedL, usedR; // 좌상향/우상향 대각선 사용

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        dfs(0, 0, blackList, true); // 검은칸
        dfs(0, 0, whiteList, false); // 흰색칸

        System.out.print(blackMax + whiteMax);
    }//sol

    private static void dfs(int idx, int bCnt, List<Bishop> bishops, boolean isBlack) {
        // 체스판 위에 놓을 수 있는 비숍의 최대 개수
        if (isBlack) blackMax = Math.max(blackMax, bCnt);
        else whiteMax = Math.max(whiteMax, bCnt);

        int size = bishops.size();
        int max = isBlack ? blackMax : whiteMax;

        // 남은 비숍 다 놓아도 현재 찾은 최댓값 이하
        if (bCnt + (size - idx) <= max) return;
        // 탐색 완료
        if (idx == size) return;

        Bishop cur = bishops.get(idx);
        int left = cur.left;
        int right = cur.right;

        // 선택
        if (!usedL[left] && !usedR[right]) {
            usedL[left] = true;
            usedR[right] = true;

            dfs(idx+1, bCnt+1, bishops, isBlack);

            usedL[left] = false;
            usedR[right] = false;
        }

        // 선택 x
        dfs(idx+1, bCnt, bishops, isBlack);
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 체스판의 크기

        int size = N * 2;
        int[][] map = new int[N][N]; // 체스판
        usedL = new boolean[size]; // 좌상향 대각선 사용
        usedR = new boolean[size]; // 우상향 대각선 사용
        blackList = new ArrayList<>();
        whiteList = new ArrayList<>();

        int B = 0;
        StringTokenizer st;
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 1) {
                    int left = r - c + N - 1;
                    int right = r + c;
                    Bishop cur = new Bishop(left, right);

                    if ((r + c) % 2 == 0) blackList.add(cur);
                    else whiteList.add(cur);

                    map[r][c] = B++;
                } else {
                    map[r][c] = -1;
                }
            }
        }

        br.close();
    }//init

    private static class Bishop {
        int left; // 좌상향 대각선 번호
        int right; // 우상향 대각선 번호
        Bishop(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }//Bishop

}//class