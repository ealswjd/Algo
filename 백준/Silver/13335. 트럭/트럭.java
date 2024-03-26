import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, W, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 첫 번째 줄에는 세 개의 정수 n, w, L 이 주어짐
		N = getIntToken(st); // 다리를 건너는 트럭의 수
		W = getIntToken(st); // 다리의 길이
		L = getIntToken(st); // 다리의 최대하중
		
		Queue<Integer> trucks = new LinkedList<>(); // 트럭들
		//두 번째 줄에는 n개의 정수가 주어지는데, 트럭의 무게를 나타낸다.
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trucks.offer(getIntToken(st));
		}//for
		br.close();
		
		System.out.print(solution(trucks));
	}//main

	private static int solution(Queue<Integer> trucks) {
		int time = W; // 모든 트럭들이 다리를 건너는 최단시간
		int sum = 0; // 현재 다리를 건너는 트럭들의 무게 합
		Queue<Integer> bridge = new LinkedList<>(); // 다리
		
		while(!trucks.isEmpty()) {
			if(bridge.size() < W) {
				if(trucks.peek() + sum > L) {
					for(int i=bridge.size(); i<W; i++) bridge.offer(0);
				}else {
					bridge.offer(trucks.peek());
					sum += trucks.poll();
				}
			}
			else {
				sum -= bridge.poll();
				time++;
			}
		}//while
		
		return time+bridge.size();
	}//solution

	private static int getIntToken(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}//getIntToken

}//class