import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	private boolean[] visited;
	private int[] counts;
	private PriorityQueue<Score> pq;
	
	public int[] solution(int n, int[] info) {
        pq = new PriorityQueue<>();
        counts = new int[11];
        visited = new boolean[11];
        comb(n, info, 0, n, 0);
        
        if(pq.isEmpty()) {
        	return new int[] {-1};
        }//if

        return pq.poll().counts;
    }//solution
	
	private void comb(int n, int[] info, int start, int cnt, int sum) {
		if(cnt == 0) {
			int apeach = getApeachScore(sum, counts, info);
			if(apeach < sum) {
				int[] cnts = new int[11];
				for(int i=0; i<11; i++) cnts[i] = counts[i];
        		pq.offer(new Score(sum - apeach, cnts));
        	}
			return;
		}//if
		
		for(int i=0; i<11; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			if(info[i]+1 <= cnt) {
				counts[i] = info[i]+1;
				comb(n, info, i+1, cnt-(info[i]+1), sum+(10-i));
				counts[i] = 0;
			}else {
				counts[i] = cnt;
				comb(n, info, i+1, 0, sum);
				counts[i] = 0;				
			}
			visited[i] = false;
			
		}//for
		
	}//comb

	private int getApeachScore(int ryan, int[] counts, int[] info) {
		int apeach = 0;
		for(int i=0; i<10; i++) {
			if(info[i]!=0 && info[i] >= counts[i]) apeach += 10-i;
		}//for
		return apeach;
	}//getApeachScore

	private static class Score implements Comparable<Score> {
		int score;
		int[] counts;
		public Score(int score, int[] counts) {
			this.score = score;
			this.counts = counts;
		}
		@Override
		public int compareTo(Score s) {
			if(this.score != s.score) return s.score - this.score;
			
			for(int i=10; i>=0; i--) {
				if(this.counts[i] != s.counts[i]) {
					return s.counts[i] - this.counts[i];
				}
			}//for
			return s.score - this.score;
			
		}//compareTo
	}//Score

}//class
