import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i=0; i<5; i++){
            int ans = 1;
            String[] place = places[i];
            r:for(int r=0; r<5; r++){
                for(int c=0; c<5; c++){
                    if(place[r].charAt(c) == 'P'){
                        ans = bfs(r, c, place);
                    }
                    if(ans == 0) break r;
                }
            }
            answer[i] = ans;
        }
        return answer;
    }

    private int bfs(int r1, int c1, String[] place){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r1, c1});
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!q.isEmpty()){
            int[] pos = q.poll();
            for(int i=0; i<4; i++){
                int r2 = pos[0] + dx[i];
                int c2 = pos[1] + dy[i];
                if(r2 < 0 || c2 < 0 || r2 >= 5 || c2 >= 5 || (r1==r2 && c1==c2)) continue;

                int distance = Math.abs(r1-r2) + Math.abs(c1-c2);
                if(place[r2].charAt(c2) == 'P' && distance <= 2){
                    return 0;
                }else if(place[r2].charAt(c2) == 'O' && distance < 2){
                    q.offer(new int[] {r2, c2});
                }
            }
        }

        return 1;
    }

}