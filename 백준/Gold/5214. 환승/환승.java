import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5214
public class Main {
    private static int N; // 역의 수
    private static int M; // 하이퍼튜브 개수
    private static List<List<Integer>> hyperTube; // 하이퍼튜브 연결 정보
    private static List<List<Integer>> tubeIndex; // 역마다 연결된 하이퍼튜브의 인덱스


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int num = 1; // 역 번호
        int cnt = 1; // 방문한 역의 개수
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        boolean[] tubeChecked = new boolean[M];

        q.offer(new int[] {num, cnt});
        visited[num] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            num = cur[0]; // 역 번호
            cnt = cur[1]; // 방문한 역의 개수

            // N번역 도착
            if(num == N) return cnt;

            for(int idx : tubeIndex.get(num)) {
                if(tubeChecked[idx]) continue;
                tubeChecked[idx] = true;
                
                for(int next : hyperTube.get(idx)) {
                    if(visited[next]) continue;

                    visited[next] = true;
                    q.offer(new int[] {next, cnt+1});
                }
            }
        }

        // 만약, 갈 수 없다면 -1을 출력
        return -1;
    }//getCnt

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 역의 수 N과 한 하이퍼튜브가 서로 연결하는 역의 개수 K, 하이퍼튜브의 개수 M
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hyperTube = new ArrayList<>(M); // 하이퍼튜브 연결 정보
        tubeIndex = new ArrayList<>(N+1); // 역마다 연결된 하이퍼 튜브의 인덱스

        for(int i=0; i<=N; i++) {
            tubeIndex.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            List<Integer> tube = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<K; j++) {
                int num = Integer.parseInt(st.nextToken()); // i번 튜브에 연결된 역 번호
                tube.add(num);
                tubeIndex.get(num).add(i);
            }

            hyperTube.add(tube);
        }

        br.close();
    }//init

    
}//class