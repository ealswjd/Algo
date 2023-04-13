package programmers;

import java.util.*;

class Solution_과제진행 {
    PriorityQueue<Info> pq;
    int len;
    
    public String[] solution(String[][] plans) {
        len = plans.length;
        pq = new PriorityQueue<>();
        
        for(int i=0; i<len; i++){
            pq.offer(new Info(plans[i][0], parseSec(plans[i][1])
                              , parseSec(plans[i][2])));
        }//for
        
        String[] answer = work();
        return answer;
    }//solution
    
    private String[] work() {
        String[] answer = new String[len];
        Stack<Info> stack = new Stack<>(); // 멈춘 과제
        
        int cnt = 0, 
        	curTime = pq.peek().start, 
        	time;
        Info cur;
        while(!pq.isEmpty()) {
        	
        	cur = pq.peek(); // 진행 예정 과제
        	// 아직 과제 시작시간 아님
        	if(curTime < cur.start) {
        		if(stack.isEmpty()) {
        			curTime = cur.start; // 멈춘 과제 없으면 현재 시간 -> 과제 예정 시간으로 변경
        			continue;
        		}else { // 멈춘 과제 있음
        			time = stack.peek().time; // 멈춘 과제의 남은 시간
        			stack.peek().time -= cur.start - curTime; // 멈춘 과제 시간 감소
        			time = stack.peek().time <= 0 ? time : cur.start - curTime; // 0보다 작으면 기존 시간 : 아니면 예정과제 시간-현재시간
        			curTime += time; // 현재 시간 갱신
        			if(stack.peek().time <= 0) answer[cnt++] = stack.pop().name; // 멈춘 과제 끝났으면 스택에서 제거, 끝난 순서에 추가
        		}
        	}else { // 시작시간
        		cur = pq.poll(); // 과제 시작
        		if(!pq.isEmpty()) { // 남은 과제가 있으면
        			if(pq.peek().start < cur.end) { // 다음 과제 시작전에 현재 과제 못끝냄
        				cur.time -= pq.peek().start - cur.start; // 다음 과제 시작 전까지만 과제 진행
        				stack.push(cur); // 멈추고 스택에 담아주기
        				curTime = pq.peek().start; // 현재 시간 갱신
        			}else { // 현재 과제를 다음 과제 전에 끝낼 수 있음
        				answer[cnt++] = cur.name; // 과제 끝
        				curTime += cur.time; // 현재 시간 갱신
        			}
        		}else { // 남은 과제 없으면
        			answer[cnt++] = cur.name; // 과제 그냥 끝내버려
        		}
        	}//else
        	

        }//while
        
        // 멈춘 과제들
        while(!stack.isEmpty()) {
        	cur = stack.pop();
        	answer[cnt++] = cur.name;
        }
        
        return answer;
    }//work
    
    // 시간 초단위로 변환
    private int parseSec(String str) {
        String[] times = str.split(":");
        int sec = 0;
        if(times.length == 1) sec = Integer.parseInt(str) * 60;
        else{
            sec += Integer.parseInt(times[0]) * 60 * 60;
            sec += Integer.parseInt(times[1]) * 60;
        }
        
        return sec;
    }//parseSec
    
    static class Info implements Comparable<Info>{
        String name;
        int start;
        int time;
        int end;
        public Info(String name, int start, int time) {
            this.name = name;
            this.start = start;
            this.time = time;
            this.end = start+time;
        }
        @Override
        public int compareTo(Info o) {
        	return this.start - o.start;       		
        }
		@Override
		public String toString() {
			return "[name=" + name + ", start=" + start + ", time=" + time + ", end=" + end
					+ "]";
		}
        
    }//Info
}//class
