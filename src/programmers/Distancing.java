package programmers;

import java.util.LinkedList;
import java.util.Queue;

/* https://programmers.co.kr/learn/courses/30/lessons/81302
 * Lv.2) 2021 카카오 채용연계형 인턴십 > 거리두기 확인하기    */
public class Distancing {
	
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++) {
        	boolean isRight = true; // 거리두기를 지키고 있는지 판별
        	String[] place = places[i]; // i번째 대기실
        	
        	for(int r=0; r<5 && isRight; r++) {
        		for(int c=0; c<5 && isRight; c++) {
        			if(place[r].charAt(c) == 'P') // 응시자가 앉아있는 자리면
        				isRight = bfs(r, c, place); // 거리두기를 지키고 있는지 판별
        		}//for c
        	}//for r
        	
        	answer[i] = isRight ? 1 : 0; 
        }//for i
        
        return answer;
    }//solution
	
	private boolean bfs(int r1, int c1, String[] place) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r1, c1});		
		int[] dx = {-1, 1, 0, 0}; // 상하
		int[] dy = {0, 0, -1, 1}; // 좌우
		
		while(!q.isEmpty()) {
			int[] pos = q.poll(); // 응시자 위치
			
			for(int i=0; i<4; i++) { // 상하좌우 탐색
				int r2 = pos[0] + dx[i];
				int c2 = pos[1] + dy[i];
				// 범위를 벗어나거나 같은 위치인지 체크
				if(r2 < 0 || c2 < 0 || r2 >= 5 || c2 >= 5 || (r1==r2 && c1==c2)) continue;
				
				int distance = Math.abs(r1-r2) + Math.abs(c1-c2); // 맨해튼 거리
				// 맨해튼 거리 2 이하에 사람이 앉아있다면
				if(place[r2].charAt(c2) == 'P' && distance <= 2) return false; // 거리두기 지키지 않았음
				else if(place[r2].charAt(c2) == 'O' && distance < 2) { // 빈 테이블이고, 빈테이블을 제외한 맨해튼 거리가 2 미만이라면 
					q.offer(new int[] {r2, c2}); // 테이블 주변을 살피기 위해 큐에 위치 담아주기
				}
			}
		}//while
		
		return true; // 거리두기 잘 지켰음
	}//bfs

}//class
