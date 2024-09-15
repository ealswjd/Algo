import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18808
public class Main {
    static int N, M, K;
    static int R, C;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노트북 세로
        M = Integer.parseInt(st.nextToken()); // 노트북 가로
        K = Integer.parseInt(st.nextToken()); // 스티커 개수

        map = new int[N][M]; // 노트북

        int total = 0;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[R][C]; // 스티커
            int cnt = 0; // 스티커 칸 수

            for(int i=0; i<R; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                    if(sticker[i][j] == 1) cnt++;
                }
            }

            // 부착 가능하면 칸 수 더해줌
            if(isAttachable(sticker)) total += cnt;
        }

        
        System.out.print(total);
    }//main


    // 회전시키며 부착 가능한지 확인
    private static boolean isAttachable(int[][] sticker) {
        int rotationCnt = 0; // 회전 횟수

        // 0, 90, 180, 270
        while(rotationCnt++ < 4) {
            if(isAvailable(sticker)) return true;

            sticker = rotation(sticker); // 회전
            R = sticker.length;
            C = sticker[0].length;
        }

        return false;
    }//isAttachable


    // 시계 방향 회전
    private static int[][] rotation(int[][] sticker) {
        int[][] rotation = new int[C][R]; // 회전시킨 스티커

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                rotation[c][R-r-1] = sticker[r][c];
            }
        }

        return rotation;
    }//rotation


    // 해당 스티커를 붙일 수 있는지 확인
    private static boolean isAvailable(int[][] sticker) {

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isComplete(i, j, sticker)) return true;
            }
        }

        return false;
    }//isAvailable


    // 스티커 붙일 수 있으면 붙이기
    private static boolean isComplete(int r, int c, int[][] sticker) {
        if(r+R > N || c+C > M) return false; // 노트북 범위 벗어남

        // 붙일 수 있는지 확인
        for(int i=r, sr=0; i<r+R; i++, sr++) {
            for(int j=c, sc=0; j<c+C; j++, sc++) {
                if(sticker[sr][sc] == 0) continue;
                if(map[i][j] == 1) return false; // 다른 스티커랑 겹침
            }
        }

        // 스티커 붙이기
        for(int i=r, sr=0; i<r+R; i++, sr++) {
            for(int j=c, sc=0; j<c+C; j++, sc++) {
                if(sticker[sr][sc] == 1) map[i][j] = 1;
            }
        }

        return true;
    }//isComplete


}//class