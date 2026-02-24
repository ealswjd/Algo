import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1497
// 최대한 많은 곡을 연주하려고 할 때, 필요한 기타의 최소 개수 구하기
public class Main {
    private static int N; // 기타의 개수
    private static int min; // 필요한 기타의 최소 개수
    private static long max; // 연주 가능한 곡의 최대 개수
    private static long[] guitars; // 각 기타가 연주할 수 있는 곡 정보

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0, 0);

        System.out.print(min);
    }//main

    private static void dfs(int idx, int cnt, long music) {
        if (max == music) {
            // 곡의 최대 개수가 같으면 기타의 최소 개수 갱신
            min = Math.min(min, cnt);
        } else if(max < music) {
            // 현재 조합으로 연주할 수 있는 곡이 더 많음
            max = music;
            min = cnt;
        }
        // 탐색 완료
        if (idx == N) return;

        // 현재 기타를 선택하여 다른 곡 연주 가능
        if ((music ^ guitars[idx]) != 0) {
            dfs(idx + 1, cnt + 1, music | guitars[idx]);
        }

        // 선택 x
        dfs(idx + 1, cnt, music);
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 기타의 개수
        int M = Integer.parseInt(st.nextToken()); // 곡의 개수

        guitars = new long[N];
        min = -1;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            String music = st.nextToken();
            for(int j=0; j<M; j++) {
                if (music.charAt(j) == 'Y') {
                    guitars[i] |= (1L << j);
                }
            }
        }

        br.close();
    }//init

}//class