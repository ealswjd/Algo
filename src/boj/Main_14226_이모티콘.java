package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/14226
public class Main_14226_이모티콘 {
	static int S, max=2002;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());// S개의 이모티콘
		visited = new boolean[max][max]; // 방문체크(화면 이모티콘 개수, 클립보드 이모티콘 개수)
		br.close();
		int time = bfs(); // S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값
		System.out.print(time);
	}//main

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {1, 0, 0}); // 화면, 클립보드, 시간
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int screen = now[0];
			int clip = now[1];
			int time = now[2];
			
			if(screen == S) return time;
			
			if(screen > 0 && screen < max) {
				// 복사
				if(!visited[screen][screen]) {
					visited[screen][screen] = true;
					q.offer(new int[] {screen, screen, time+1});
				}
				// 삭제
				if(!visited[screen-1][clip]) {
					visited[screen-1][clip] = true;
					q.offer(new int[] {screen-1, clip, time+1});
				}
			}//if
			
			if(screen > 0 && screen + clip < max) {
				// 붙여넣기
				if(!visited[screen+clip][clip]) {
					visited[screen+clip][clip] = true;
					q.offer(new int[] {screen+clip, clip, time+1});
				}				
			}//if
			
		}//while
		
		return 0;
	}//bfs

	

}//class

/*

# N [정답,오답]
217 [18, 19]
325 [19, 21]
497 [20, 21]
505 [20, 21]
553 [21, 22]
651 [21, 22]
687 [21, 22]
973 [23, 24]
975 [22, 23]
994 [22, 23]

*/
