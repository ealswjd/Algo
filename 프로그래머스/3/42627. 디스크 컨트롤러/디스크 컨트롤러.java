import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    
    public int solution(int[][] jobs) {
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<Job> pq = new PriorityQueue<>();
        Queue<Job> wait = new LinkedList<>();
        
        for(int[] job : jobs) {
        	wait.offer(new Job(job[0], job[1]));        		
        }

        int sum = 0;
        int cnt = 0;
        int end = wait.peek().request;
        
        while(cnt < jobs.length) {            
        	while(!wait.isEmpty() && wait.peek().request <= end) {
        		pq.offer(wait.poll());
        	}
            
        	if(!pq.isEmpty()) { 
        		Job job = pq.poll();
        		end += job.runtime;
        		sum += end - job.request; 
        		cnt++;
        	}
            else end = wait.peek().request; 
        }
        
        return sum/cnt;
    }//solution
    
}//class

class Job implements Comparable<Job> {
	int request;
	int runtime;
	
	public Job(int request, int runtime){
		this.request = request;
		this.runtime = runtime;
	}
    
    @Override
    public int compareTo(Job o) {
        return this.runtime - o.runtime;
    }
}//Job