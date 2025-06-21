import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/15662
 톱니바퀴 (2)
 총 K번 회전시킨 이후에 12시방향이 S극인 톱니바퀴의 개수를 출력
 양 옆 톱니바퀴 회전은 회전 시키기 전 기준으로 회전
 */
public class Main {
    private static final int W = 8; // 톱니 개수
    private static final int FORWARD = 1, S = 1; // 시계방향, S극
    private static final int[] L = {6, 7, 0, 1, 2, 3, 4, 5};
    private static final int[] R = {2, 3, 4, 5, 6, 7, 0, 1};
    private static int T, K; // 톱니바퀴 개수, 회전 횟수
    private static int[] index; // 12시 기준 인덱스
    private static int[][] origin; // 초기 톱니바퀴의 상태
    private static int[][] rotationInfo; // 회전시킨 방법


    public static void main(String[] args) throws IOException {
        init();
        sol();

    }//main


    private static void sol() {
        int num, dir; // 회전시킨 톱니바퀴의 번호, 방향
        int cur, next, nd, left, right;

        for(int[] info : rotationInfo) {
            num = info[0]; // 회전시킨 톱니바퀴의 번호
            dir = info[1]; // 방향
            
            // 회전 가능한 왼쪽 톱니바퀴 인덱스 찾기
            left = getLeftIdx(num);
            // 회전 가능한 오른쪽 톱니바퀴 인덱스 찾기
            right = getRightIdx(num);

            // 현재 톱니바퀴 회전
            rotation(num, dir);

            // 왼쪽 톱니 회전
            if(left != -1 && left != num) {
                nd = dir^1;
                for(int i=num-1; i>=left; i--) {
                    rotation(i, nd);
                    nd ^= 1;
                }
            }

            // 오른쪽 톱니 회전
            if(right != -1 && right != num) {
                nd = dir^1;
                for(int i=num+1; i<=right; i++) {
                    rotation(i, nd);
                    nd ^= 1;
                }
            }
        }

        // 12시방향이 S극인 톱니바퀴의 개수를 출력
        int cnt = 0;
        for(int i=0; i<T; i++) {
            if(origin[i][index[i]] == S) cnt++;
        }

        System.out.print(cnt);
    }//sol


    private static int getLeftIdx(int cur) {
        if(cur == 0) return -1;

        int next = cur;
        int l = L[index[cur]];
        int r;

        while(cur > 0) {
            next = cur - 1;
            r = R[index[next]];
            if(origin[cur][l] == origin[next][r]) return cur;

            cur = next;
            l = L[index[cur]];
        }

        return next;
    }//getLeftIdx


    private static int getRightIdx(int cur) {
        if(cur == T - 1) return -1;

        int next = cur;
        int r = R[index[cur]];
        int l;

        while(cur < T - 1) {
            next = cur + 1;
            l = L[index[next]];
            if(origin[cur][r] == origin[next][l]) return cur;

            cur = next;
            r = R[index[cur]];
        }

        return next;
    }//getRightIdx


    private static void rotation(int num, int dir) {
        // 1. 현재 톱니 회전
        if(dir == FORWARD) {// 시계 방향
            index[num]--;
            if(index[num] == -1) index[num] = W - 1;
        }
        else { // 반 시계 방향
            index[num] = (index[num] + 1) % W;
        }
    }//rotation


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 톱니바퀴의 개수 T (1 ≤ T ≤ 1,000)가 주어진다.
        T = Integer.parseInt(br.readLine()); // 톱니바퀴의 개수
        origin = new int[T][W]; // 초기 톱니바퀴의 상태
        index = new int[T];

        // T개의 줄에 톱니바퀴의 상태가 가장 왼쪽 톱니바퀴부터 순서대로 주어진다.
        for(int i=0; i<T; i++) {
            char[] status = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                origin[i][j] = status[j] - '0';
            }
        }

        // 회전 횟수 K(1 ≤ K ≤ 1,000)가 주어진다.
        K = Integer.parseInt(br.readLine());
        rotationInfo = new int[K][2]; // 회전시킨 방법

        // K개 줄에는 회전시킨 방법이 순서대로 주어진다.
        StringTokenizer st;
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            rotationInfo[i][0] = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
            rotationInfo[i][1] = Integer.parseInt(st.nextToken()); // 방향
            if(rotationInfo[i][1] == -1) rotationInfo[i][1] = 0;
        }

        br.close();
    }//init


}//class