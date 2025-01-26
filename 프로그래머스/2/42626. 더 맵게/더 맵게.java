import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 스코빌 지수를 담을 우선순위 큐
        int answer = 0; // 음식을 섞는 횟수
        
        for(int s : scoville){
            pq.offer(s);
        }
        
        while(pq.size() > 1 && pq.peek() < K) {
        	pq.offer(pq.poll() + (pq.poll() * 2)); // 섞은 음식의 스코빌 지수 담아주기
        	answer++; 
        }

        return pq.peek() >= K ? answer : -1; 
    }
}