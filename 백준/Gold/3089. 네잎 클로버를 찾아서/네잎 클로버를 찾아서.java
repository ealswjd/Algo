import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 네잎 클로버를 찾아서 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/3089
 * 알고리즘 : 이분탐색 
 * */
public class Main {
    static int N, M;
    static Position[] X, Y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 네잎 클로버의 개수
        M = Integer.parseInt(st.nextToken()); // 외계인이 전송한 명령의 수

        X = new Position[N]; // x 기준 정렬
        Y = new Position[N]; // y 기준 정렬

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
        // x 기준 정렬 
        Arrays.sort(X, (pos1, pos2) -> {
            if(pos1.x == pos2.x) return pos1.y - pos2.y;
            return pos1.x - pos2.x;});
        
        // y 기준 정렬
        Arrays.sort(Y, (pos1, pos2) -> {
            if(pos1.y == pos2.y) return pos1.x - pos2.x;
            return pos1.y - pos2.y;
        });

        int x = 0, y = 0;
        int idxX, idxY; // X 인덱스, Y 인덱스

        for(char c : orders) {
            if(c == 'U' || c == 'D') { // 상하
                idxX = binarySearchX(x, y); // 현재 위치 인덱스 찾아서
                if(c == 'U') idxX++; // 위로 이동
                else idxX--; // 아래로 이동

                // 위치 갱신
                x = X[idxX].x;
                y = X[idxX].y;

            }else { // 좌우
                idxY = binarySearchY(x, y); // 현재 위치 인덱스 찾아서
                if(c == 'R') idxY++; // 오른쪽으로 이동
                else idxY--; // 왼쪽으로 이동

                // 위치 갱신
                x = Y[idxY].x;
                y = Y[idxY].y;
            }
        }

        System.out.print(x + " " + y); // 마지막 위치 출력
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
    }

}//class