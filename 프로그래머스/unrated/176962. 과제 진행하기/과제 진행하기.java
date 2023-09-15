import java.util.*;

class Solution {
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
        Stack<Info> stack = new Stack<>();
        
        int cnt = 0, curTime = pq.peek().start, time;
        Info cur;
        while(!pq.isEmpty()) {
        	
        	cur = pq.peek();
        	// 아직 과제 시작시간 아님
        	if(curTime < cur.start) {
        		if(stack.isEmpty()) {
        			curTime = cur.start;
        			continue;
        		}else {
        			time = stack.peek().time;
        			stack.peek().time -= cur.start - curTime;
        			time = stack.peek().time <= 0 ? time : cur.start - curTime;
        			curTime += time;
        			if(stack.peek().time <= 0) answer[cnt++] = stack.pop().name;
        		}
        	}else { // 시작시간
        		cur = pq.poll();
        		if(!pq.isEmpty()) {
        			if(pq.peek().start < cur.end) {
        				cur.time -= pq.peek().start - cur.start;
        				stack.push(cur);
        				curTime = pq.peek().start;
        			}else {
        				answer[cnt++] = cur.name;
        				curTime += cur.time;
        			}
        		}else {
        			answer[cnt++] = cur.name;
        		}
        	}//else
        	

        }//while
        
        while(!stack.isEmpty()) {
        	cur = stack.pop();
        	answer[cnt++] = cur.name;
        }
        
        return answer;
    }//work
    
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
        
    }//Info
}//class
