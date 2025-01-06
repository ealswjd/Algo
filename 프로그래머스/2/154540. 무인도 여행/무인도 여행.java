import java.util.*;

class Solution {
    private int H, W;
    private int[][] map;
    private boolean[][] visited;
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    
    public int[] solution(String[] maps) {
        H = maps.length;
        W = maps[0].length();
        map = new int[H][W];
        
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(maps[i].charAt(j) == 'X') map[i][j] = 0;  
                else map[i][j] = maps[i].charAt(j) - '0';  
            }
        }
        
        visited = new boolean[H][W];
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(map[i][j] != 0 && !visited[i][j]){
                    visited[i][j] = true;
                    list.add(dfs(i, j, map[i][j]));
                }
            }
        }
        
        if(list.size() == 0) return new int[] {-1};
        
        int[] answer = new int[list.size()];
        Collections.sort(list);
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }//solution
    
    
    private int dfs(int r, int c, int sum) {       
        
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            sum = dfs(nr, nc, sum+map[nr][nc]);
        }
        
        return sum;
    }//dfs
    
    
    private boolean rangeCheck(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W || map[r][c] == 0;
    }//rangeCheck
    
    
}//class