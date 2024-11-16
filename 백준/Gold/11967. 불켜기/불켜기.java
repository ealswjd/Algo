import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11967
public class Main {
    private static int N, total;
    private static List<List<Integer>> switchList;

    
    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int cnt = 1; // 불을 켤 수 있는 방 개수
        Queue<Integer> q = new LinkedList<>(); // 불 켜진 방
        List<Integer> waiting = new ArrayList<>(); // 대기 방
        boolean[] visited = new boolean[total+1]; // 방문 체크
        boolean[] isLightOn = new boolean[total+1]; // 불 켜졌는지 체크

        int room = 1; // 시작 방 번호
        int row;
        q.offer(room);
        visited[room] = true;
        isLightOn[room] = true;

        int[] dir = {-1, 1, -N, N};

        while(!q.isEmpty()) {
            room = q.poll(); // 현재 방
            row = (room - 1) / N;

            for(int next : switchList.get(room)) {
                if(isLightOn[next]) continue;
                // 불 켜기
                isLightOn[next] = true;
                cnt++;
            }

            // 인접한 방 방문할 수 있는지 확인
            for(int i=0; i<4; i++) {
                int next = room + dir[i]; // 인접한 방
                int nextRow = (next - 1) / N;

                if(rangeCheck(next) || visited[next]) continue;
                if(i < 2 && row != nextRow) continue; // 좌우

                if(isLightOn[next]) {
                    // 불 켜진 방 : 이동 가능
                    q.offer(next);
                    visited[next] = true;
                }
                else waiting.add(next);
            }

            // 대기 중인 방 방문할 수 있는지 확인
            for(int next : waiting) {
                if (visited[next] || !isLightOn[next]) continue;

                visited[next] = true;
                q.offer(next);
            }

        }//while

        
        return cnt;
    }//getCnt


    private static boolean rangeCheck(int room) {
        return room <= 0 || room > total;
    }//rangeCheck

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        total = N * N;
        switchList = new ArrayList<>();
        for(int i=0; i<=total; i++) {
            switchList.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int from = x * N + y + 1;
            int to = a * N + b + 1;

            switchList.get(from).add(to);
        }

        br.close();
    }//init

    
}//class

/*
4 6
1 1 1 2
1 1 1 3
1 2 1 4
1 4 2 4
2 4 3 1
3 1 4 2
---
6
 */