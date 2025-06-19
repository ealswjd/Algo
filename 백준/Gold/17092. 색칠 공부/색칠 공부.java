import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17092
public class Main {
    private static final long MAX = 1_000_000_000;
    private static final int CNT = 9; // 3×3 크기의 부분 모눈종이
    private static int H, W; // 모눈종이의 크기 H, W
    private static long[] count; // 0 ~ 9 개인 부분 모눈종이 개수
    private static Map<Long, Integer> blackCells; // 검은칸이 있는 부분모는종이


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();

        count[0] = (long) (H - 2) * (W - 2); // 검은칸 0개

        for(long key : blackCells.keySet()) {
            int cnt = blackCells.get(key); // 부분 모눈종이의 검은칸 개수
            count[cnt]++;
            count[0]--;
        }

        for(long cnt : count) {
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 모눈종이의 크기 H, W와 검정색 칸의 개수 N이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        count = new long[CNT + 1]; // 0 ~ 9 개인 부분 모눈종이 개수
        blackCells = new HashMap<>();

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            // 해당 검정칸은 최대 9개의 부분 모눈종이에 영향을 줄 수 있음
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(r-2+i < 0 || c-2+j < 0 || r+i >= H || c+j >= W) continue;

//                    int idx = i * 3 + j; // 해당 검정칸 위치 (0 ~ 8)
                    long key = (r - 2 + i) * MAX + (c - 2 + j);
                    blackCells.put(key, blackCells.getOrDefault(key, 0) + 1);
                }
            }
        }

        br.close();
    }//init


}//class