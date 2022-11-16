package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
// https://programmers.co.kr/learn/courses/30/lessons/42627
// 힙(Heap) > 디스크 컨트롤러
class DiscController {
    public int solution(int[][] jobs) {
        // jobs 배열 오름차순 정렬(요청시점 기준)
		Arrays.sort(jobs, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] job1, int[] job2) {
        		return job1[0] - job2[0];
        	}
        });
        // 작업 큐 - 우선순위 큐 사용. 오름차순 정렬(작업 소요시간 기준)
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return o1.runtime - o2.runtime;
			}
		});
        // 대기 큐 - 요청시점 기준으로 오름차순 정렬된 jobs 모두 담아줌
        Queue<Job> wait = new LinkedList<>();
        for(int[] job : jobs) {
        	wait.offer(new Job(job[0], job[1]));        		
        }

        int sum = 0; // 총 작업시간
        int cnt = 0; // 작업 횟수
        int end = wait.peek().request; // 현재 작업의 완료시점이자 다음 작업의 시작시점
        while(cnt < jobs.length) {
        	// 하나의 작업이 완료되는 시점(end) 이전에 들어온 모든 요청을 작업큐에 담아주기
        	while(!wait.isEmpty() && wait.peek().request <= end) {
        		pq.offer(wait.poll());
        	}
        	if(!pq.isEmpty()) { 
        		Job job = pq.poll();
        		end += job.runtime; // 완료시점 = 그 전 완료시점 + 작업시간
        		sum += end - job.request; // 작업시간 합 += 완료시점 - 실제 요청시점 
        		cnt++; // 작업횟수 증가
        	}else {
        		end = wait.peek().request; // 작업큐가 비어있다면 완료시점을 다음 대기작업의 요청시점으로 설정
        	}
        }
        
        return sum/cnt;
    }
}//class

class Job{
	int request;
	int runtime;
	
	public Job(int request, int runtime){
		this.request = request;
		this.runtime = runtime;
	}
}//class
