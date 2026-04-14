import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3665
public class Main {
    private static int N; // 팀의 수
    private static int[] inDegree; // 내 팀보다 높은 순위의 팀 개수
    private static boolean[][] isWin; // i팀이 j팀을 이김

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        StringBuilder ans = new StringBuilder();
        while(T-- > 0) {
            init(br);
            solve(ans);
        }

        br.close();
        System.out.print(ans);
    }//main

    private static void solve(StringBuilder ans) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>(N);
        boolean cycle = false;
        boolean ambiguous = false;

        for(int i=1; i<=N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        for(int i=0; i<N; i++) {
            // 큐가 비어있으면 사이클 발생했다는 의미
            if (q.isEmpty()) {
                cycle = true;
                break;
            }
            // 2개 이상의 팀이 있다면 순위를 확정할 수 없음
            if (q.size() > 1) {
                ambiguous = true;
                break;
            }

            int cur = q.poll();
            result.add(cur);

            for(int next=1; next<=N; next++) {
                if (isWin[cur][next]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        if (cycle) {
            // 데이터에 일관성이 없어서 순위를 정할 수 없는 경우
            ans.append("IMPOSSIBLE\n");
        } else if (ambiguous) {
            // 확실한 순위를 찾을 수 없다면
            ans.append("?\n");
        } else {
            // 1등팀부터 순서대로 출력
            for(int team : result) {
                ans.append(team).append(' ');
            }
            ans.append('\n');
        }
    }//solve

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine()); // 팀의 수

        int[] lastYear = new int[N+1]; // 작년 등수
        inDegree = new int[N+1]; // 내 팀보다 높은 순위의 팀 개수
        isWin = new boolean[N+1][N+1]; // i팀이 j팀을 이김

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            lastYear[i] = Integer.parseInt(st.nextToken()); // 작년에 i등을 한 팀의 번호
        }

        for(int i=1; i<=N; i++) {
            for(int j=i+1; j<=N; j++) {
                int a = lastYear[i];
                int b = lastYear[j];

                isWin[a][b] = true;
                inDegree[b]++;
            }
        }

        // 상대적인 등수가 바뀐 쌍의 수 m
        int m = Integer.parseInt(br.readLine());
        while(m-- > 0) {
            // 상대적인 등수가 바뀐 두 팀이 주어진다
            st = new StringTokenizer(br.readLine());
            // 올해 a팀이 b팀보다 순위 높음
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (isWin[a][b]) {
                isWin[a][b] = false;
                isWin[b][a] = true;
                inDegree[b]--;
                inDegree[a]++;
            } else {
                isWin[b][a] = false;
                isWin[a][b] = true;
                inDegree[a]--;
                inDegree[b]++;
            }
        }

    }//init

}//class