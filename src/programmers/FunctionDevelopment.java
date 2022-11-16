package programmers;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/* https://programmers.co.kr/learn/courses/30/lessons/42586
 * Lv.2) 스택/큐 > 기능개발   */
public class FunctionDevelopment {
	
	public int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> q = new LinkedList<>(); // 남은 작업일을 담아줄 큐
		ArrayList<Integer> list = new ArrayList<>(); // 몇개의 기능이 배포 되는지 담아줄 리스트
		
		for(int i=0; i<progresses.length; i++) {
			double remain = (100 - progresses[i]) / (double) speeds[i]; 
			int date = (int) Math.ceil(remain); // 현재 작업의 남은 작업일 
			
			// 큐에 작업이 있고, 앞의 작업의 작업일보다 현재 작업의 작업일이 더 크다면
			if(!q.isEmpty() && q.peek() < date) {
				list.add(q.size()); // 먼저 있던 작업들 배포
				q.clear(); // 비워주기
			}
			q.offer(date); // 현재 작업의 남은 작업일 담아주기
		}//for		
		list.add(q.size()); // 남은 작업 배포
		
		int[] answer = new int[list.size()];
		for(int i=0; i<answer.length; i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}//solution
	
}//class
