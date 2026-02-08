import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2026
public class Main {
    private static int K, N; // 소풍 보내는 학생 수, 전체 학생 수
    private static int[] selected; // 소풍 가능 학생들
    private static int[] count; // i번 학생의 친구 수
    private static boolean[][] isFriend; // 친구 정보
    private static boolean found; // K명의 친구 관계인 학생들이 존재

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        found = false; //  K명의 친구 관계인 학생들이 존재

        for(int i=1; i<=N; i++) {
            // i번 학생의 친구들로 K명의 무리를 만들 수 없음
            if (count[i] < K) continue;

            selected[0] = i;
            dfs(i, 1);
            if (found) break;
        }

        StringBuilder ans = new StringBuilder();
        if (found) {
            for(int n : selected) {
                ans.append(n).append('\n');
            }
        } else {
            ans.append(-1);
        }

        System.out.print(ans);
    }//sol

    private static void dfs(int cur, int cnt) {
        if (found) return;
        if (cnt == K) {
            found = true;
            return;
        }

        for(int next=cur+1; next<=N; next++) {
            // K명 무리 만들 수 없음
            if (count[next] < K) continue;
            // 둘이 친구 아님
            if (!isFriend[cur][next]) continue;

            // 현재까지 선택된 학생들이랑 친구인지 확인
            if (isPossible(next, cnt)) {
                selected[cnt] = next;
                dfs(next, cnt+1);
                if (found) return;
            }
        }

    }//dfs

    private static boolean isPossible(int target, int cnt) {
        for(int i=0; i<cnt; i++) {
            if (!isFriend[target][selected[i]]) {
                return false;
            }
        }

        return true;
    }//isPossible

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 세 정수 K, N, F가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());

        selected = new int[K]; // 소풍을 갈 학생들
        count = new int[N+1]; // 각 학생들의 친구 수
        isFriend = new boolean[N+1][N+1]; // 친구 정보

        for(int i=0; i<=N; i++) {
            isFriend[i][i] = true;
            count[i] = 1;
        }

        while(F-- > 0) {
            // 서로 친구 관계인 두 사람의 번호가 주어진다
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 친구 관계는 상호적인 관계
            isFriend[a][b] = isFriend[b][a] = true;
            count[a]++;
            count[b]++;
        }

        br.close();
    }//init

}//class