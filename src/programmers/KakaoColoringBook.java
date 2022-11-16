package programmers;

import java.util.LinkedList;
import java.util.Queue;
// https://programmers.co.kr/learn/courses/30/lessons/1829
// 2017 카카오코드 예선 > 카카오프렌즈 컬러링북
public class KakaoColoringBook {
	static int max; // 가장 넓은 영역
	static int[][] checked; // 각 영역의 넓이 확인, 방문 여부

	public static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0; // 영역 개수
		max = 0; // 가장 넓은 영역
		checked = new int[m][n]; // 각 영역의 넓이 확인, 방문 여부

		for(int x=0; x<m; x++) {
			for(int y=0; y<n; y++) {
				if(picture[x][y] > 0 && checked[x][y] == 0) { // 색칠 되어 있고 방문한 적 없다면
					bfs(new Position(x, y), picture, m, n);
					numberOfArea++; // 영역 개수 증가
				}
			}
		}

		return new int[] {numberOfArea, max};
	}//solution

	static void bfs(Position pos, int[][] picture, int m, int n) {
		int[] dx = {0, 0, 1, -1}; // 상하
		int[] dy = {1, -1, 0, 0}; // 좌우
		Queue<Position> q = new LinkedList<>();
		checked[pos.x][pos.y] = 1;
		q.offer(pos);

		while(!q.isEmpty()) {
			Position p = q.poll();
			int x = p.x;
			int y = p.y;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i]; // 다음 x
				int ny = y + dy[i]; // 다음 y

				// 그림의 영역을 벗어났는지 확인
				if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
				// 이미 방문을 했는지 같은 영역인지 확인
				if(checked[nx][ny] > 0 || picture[nx][ny] != picture[x][y]) continue;

				checked[nx][ny] = ++checked[pos.x][pos.y]; // 영역 넓이 변경			
				q.offer(new Position(nx, ny)); // 큐에 담아주기
			}
			max = Math.max(max, checked[x][y]); // 가장 넓은 영역 비교
		}//while		

	}//bfs

}//class KakaoColoringBook

class Position{
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}//class Position
