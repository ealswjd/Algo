import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 https://www.acmicpc.net/problem/2001
 1번 섬에서 빈손으로 출발하여 최대한 많은 보석을 줍고 1번 섬으로 돌아오려 한다.
 다리가 무너지지 않는 한도 내에서 보석을 주워야 한다.
 한 번 지난 적이 있는 다리와 섬을 여러 번 지날 수 있으며,
 보석이 있는 섬을 지날 때에 그 보석을 줍지 않을 수도 있다고 하자.

3 2 3
1
2
3
1 2 100
2 3 100
 */
public class Main {
    private static int N; // 섬의 개수
    private static int K; // 보석이 있는 섬의 개수
    private static List<List<Node>> roadList; // 다리 연결 정보
    private static Map<Integer, Integer> jewelMap; // K개의 보석 번호


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int max = 0;
        int total = (1 << K) - 1;
        boolean[][] visited = new boolean[N+1][total + 1];
        Queue<int[]> q = new LinkedList<>();
        int cur = 1;
        int cnt = 0;
        int v = 0;

        visited[cur][v] = true;
        q.offer(new int[] {cur, cnt, v});

        int[] tmp;
        while(!q.isEmpty()) {
            tmp = q.poll();
            cur = tmp[0];
            cnt = tmp[1];
            v = tmp[2];

            if(cur == 1) {
                max = Math.max(max, cnt);
            }

            for(Node next : roadList.get(cur)) {
                if(next.weight < cnt) continue;

                // 다음 섬에 보석 있음
                if(jewelMap.containsKey(next.to)) {
                    int num = jewelMap.get(next.to);
                    int nv = v | (1 << num);

                    // 보석 주울 수 있으면 줍기
                    if((v & (1 << num)) == 0) {
                        if(!visited[next.to][nv]) {
                            visited[next.to][nv] = true;
                            q.offer(new int[] {next.to, cnt+1, nv});
                        }
                    }
                }

                // 안 줍고 그냥 가기
                if(!visited[next.to][v]) {
                    visited[next.to][v] = true;
                    q.offer(new int[] {next.to, cnt, v});
                }
            }
        }

        System.out.print(max);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 섬의 개수
        int M = Integer.parseInt(st.nextToken()); // 다리 개수
        K = Integer.parseInt(st.nextToken()); // 보석이 있는 섬의 개수
        jewelMap = new HashMap<>();

        for(int i=0; i<K; i++) {
            int num = Integer.parseInt(br.readLine());
            jewelMap.put(num, i);
        }

        roadList = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            roadList.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a번 섬과 b번 섬이 다리로 연결, 최대 c개의 보석만을 견딜 수 있다는 의미
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            roadList.get(a).add(new Node(b, c));
            roadList.get(b).add(new Node(a, c));
        }

        br.close();
    }//init


    private static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }//Node


}//class