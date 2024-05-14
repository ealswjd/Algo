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
    static final int X=0, Y=1;
    static int N, M;
    static int[][] xPos, yPos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 네잎 클로버의 개수
        M = Integer.parseInt(st.nextToken()); // 외계인이 전송한 명령의 수

        xPos = new int[N][2]; // x 기준 정렬
        yPos = new int[N][2]; // y 기준 정렬

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xPos[i][X] = yPos[i][X] = x;
            xPos[i][Y] = yPos[i][Y] = y;
        }

        getXY(br.readLine().toCharArray());
    }//main

    
    private static void getXY(char[] orders) {
        // x 기준 정렬
        Arrays.sort(xPos, (pos1, pos2) -> {
            if(pos1[X] == pos2[X]) return pos1[Y] - pos2[Y];
            return pos1[X] - pos2[X];});

        // y 기준 정렬
        Arrays.sort(yPos, (pos1, pos2) -> {
            if(pos1[Y] == pos2[Y]) return pos1[X] - pos2[X];
            return pos1[Y] - pos2[Y];
        });

        int x = 0, y = 0;
        int idxX, idxY; // X 인덱스, Y 인덱스

        for(char c : orders) {
            if(c == 'U' || c == 'D') { // 상하
                idxX = binarySearch(x, y, xPos, X, Y); // 현재 위치 인덱스 찾아서
                if(c == 'U') idxX++; // 위로 이동
                else idxX--; // 아래로 이동

                // 위치 갱신
                x = xPos[idxX][X];
                y = xPos[idxX][Y];

            }else { // 좌우
                idxY = binarySearch(y, x, yPos, Y, X); // 현재 위치 인덱스 찾아서
                if(c == 'R') idxY++; // 오른쪽으로 이동
                else idxY--; // 왼쪽으로 이동

                // 위치 갱신
                x = yPos[idxY][X];
                y = yPos[idxY][Y];
            }
        }

        System.out.print(x + " " + y); // 마지막 위치 출력
    }//getXY

    
    private static int binarySearch(int first, int second, int[][] pos, int fIdx, int sIdx) {
        int start = 0;
        int end = N-1;
        int mid = (start+end) / 2;

        while(start <= end) {
            mid = (start+end) / 2;
            if(pos[mid][fIdx] < first) {
                start = mid+1;
            }else if(pos[mid][fIdx] > first){
                end = mid-1;
            }else {
                if(pos[mid][sIdx] < second) {
                    start = mid+1;
                }else if(pos[mid][sIdx] > second){
                    end = mid-1;
                }else break;
            }
        }

        return mid;
    }//binarySearch

}//class