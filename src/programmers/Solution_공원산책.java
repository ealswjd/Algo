package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/172928
public class Solution_공원산책 {
	int H, W; // 행, 열
    char[][] map; // 공원
    int[] rc; // 강아지 위치
    
    public int[] solution(String[] park, String[] routes) {
        H = park.length; // 행
        W = park[0].length(); //열
        rc = new int[2]; // 강아지 현재 위치
        
        map = new char[H][W];
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                map[i][j] = park[i].charAt(j);
                if(map[i][j] == 'S') { // 시작 위치
                    rc[0] = i;
                    rc[1] = j;
                }
            }//for j
        }//for i
        
        char op; // 방향
        int n, nr, nc; 
        for(String route : routes){
            op = route.charAt(0);
            n = route.charAt(2) - '0';
            nr = rc[0]; // 다음 행
            nc = rc[1]; // 다음 열
            
            if(op == 'N') nr-=n; // 북쪽
            else if(op == 'S') nr+=n; // 남쪽            
            else if(op == 'W') nc-=n; // 서쪽            
            else nc+=n; // 동쪽
            
            if(!check(nr, nc)) continue; // 공원을 벗어나거나 장애물을 만나는지 확인
            // 강아지 이동
            rc[0] = nr; 
            rc[1] = nc;
        }//for
        
        return new int[] {rc[0], rc[1]};
    }//solution
    
    // 이동할 때 장애물 만나거나 공원 벗어나는지 확인
    private boolean check(int nr, int nc) {    
    	int r = rc[0];
    	int c = rc[1];
        int startR = Math.min(r, nr);
        int endR = Math.max(r, nr);
        int startC = Math.min(c, nc);
        int endC = Math.max(c, nc);
        
    	for(int i=startR; i<=endR; i++) {
            if(i<0||i>=H) return false;
    		for(int j=startC; j<=endC; j++) {
    			if(j<0 || j>=W || map[i][j] == 'X') return false;  
    		}//for j
    	}//for i
    	
        return true;
    }//check
    
}//class
