import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14890
public class Main {
    static int N, L; // 지도의 크기 N, 경사로 길이 L
    static int[][] row; // 지도
    static int[][] col; // 지도

    
    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int cnt = 0;

        for(int i=0; i<N; i++) {
            if(make(row[i])) cnt++;
            if(make(col[i])) cnt++;
        }

        return cnt;
    }//getCnt

    
    private static boolean make(int[] road) {
        int prevHight = road[0]; // 전 높이
        int size = 0; // 동일한 높이 길이
        int j=0;
        
        while(j < N) {
            if(prevHight == road[j]) { // 이전과 같은 높이
                size++; // 길이 늘려주고
                j++; // 다음으로
            }
            else if(prevHight+1 == road[j]) { // 이전높이보다 1 크면
                if(size < L) return false; // 연속된 길이가 경사로의 길이보다 짧음
                
                size = 1; // 경사로 설치, 길이 1로 변경
                prevHight++; // 이전높이 변경
                j++; // 다음으로
            }
            else if(prevHight-1 == road[j]) { // 이전높이보다 1 작음
                int count = 0;
                
                for(int i=j; i<N; i++) {
                    if(road[i] != road[j]) return false; // 이전 높이랑 다르면 안됨
                    if(++count == L) break; // 같다면 길이가 경사로의 길이를 충족할때까지
                }
                
                if(count < L) return false; // 경사로의 길이보다 짧음
                
                j += L; // 경사로의 길이만큼 이동
                size = 0; // 길이 0으로 변경
                prevHight--; // 높이 변경
            }
            else return false; // 높이가 2 이상 차이

        }//while

        return true;
    }//make

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지도의 크기
        L = Integer.parseInt(st.nextToken()); // 경사로 길이

        row = new int[N][N];
        col = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                row[i][j] = col[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init

    
}//class