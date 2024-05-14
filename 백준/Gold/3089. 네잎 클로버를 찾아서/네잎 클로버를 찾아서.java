import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 네잎 클로버를 찾아서 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/3089
 * */
public class Main {
    static int N, M;
    static Position[] X, Y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 네잎 클로버의 개수
        M = Integer.parseInt(st.nextToken()); // 외계인이 전송한 명령의 수

        X = new Position[N];
        Y = new Position[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            X[i] = new Position(x, y);
            Y[i] = new Position(x, y);
        }

        getXY(br.readLine().toCharArray());
    }//main

    private static void getXY(char[] orders) {
        Arrays.sort(X, (pos1, pos2) -> {
            if(pos1.x == pos2.x) return pos1.y - pos2.y;
            return pos1.x - pos2.x;});
        Arrays.sort(Y, (pos1, pos2) -> {
            if(pos1.y == pos2.y) return pos1.x - pos2.x;
            return pos1.y - pos2.y;
        });

        int x = 0, y = 0;
        int idxX = binarySearchX(x, y);
        int idxY = binarySearchY(x, y);

        for(char c : orders) {
            if(c == 'U' || c == 'D') { // 상하
                if(c == 'U') idxX++;
                else idxX--;

                x = X[idxX].x;
                y = X[idxX].y;

                idxY = binarySearchY(x, y);
            }else { // 좌우
                if(c == 'R') idxY++;
                else idxY--;

                x = Y[idxY].x;
                y = Y[idxY].y;
                idxX = binarySearchX(x, y);
            }
        }

        System.out.print(X[idxX]);
    }//getXY

    private static int binarySearchX(int x, int y) {
        int s = 0;
        int e = N-1;
        int mid = (s+e) / 2;

        while(s <= e) {
            mid = (s+e) / 2;
            if(X[mid].x < x) {
                s = mid+1;
            }else if(X[mid].x > x){
                e = mid-1;
            }else {
                if(X[mid].y < y) {
                    s = mid+1;
                }else if(X[mid].y > y){
                    e = mid-1;
                }else break;
            }
        }

        return mid;
    }//binarySearchX

    private static int binarySearchY(int x, int y) {
        int s = 0;
        int e = N-1;
        int mid = (s+e) / 2;

        while(s <= e) {
            mid = (s+e) / 2;
            if(Y[mid].y < y) {
                s = mid+1;
            }else if(Y[mid].y > y){
                e = mid-1;
            }else {
                if(Y[mid].x < x) {
                    s = mid+1;
                }else if(Y[mid].x > x){
                    e = mid-1;
                }else break;
            }
        }

        return mid;
    }//binarySearchY

    static class Position {
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

}//class