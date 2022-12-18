package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13335
public class Main_13335_트럭 {
	static int N, W, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 첫 번째 줄에는 세 개의 정수 n (1 ≤ n ≤ 1,000) , w (1 ≤ w ≤ 100) and L (10 ≤ L ≤ 1,000)이 주어짐
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
		
		while(!trucks.isEmpty()) { // 다리에 진입하지 못한 트럭이 있다면 반복
			if(bridge.size() < W) { // 다리를 건너고 있는 트럭 수가 다리 길이보다 적다면
				if(trucks.peek() + sum > L) { // 다음 트럭과 다리에 있는 트럭들의 무게합이 최대하중을 넘긴다면
					for(int i=bridge.size(); i<W; i++) bridge.offer(0); // 다음트럭 대기(다리 길이만큼 0으로 채워줌)
				}else { // 무게합이 최대하중을 넘기지 않는다면
					bridge.offer(trucks.peek()); // 다리에 다음 트럭 진입
					sum += trucks.poll(); //무게 갱신
				}
			}
			else { // 다리를 건너는 트럭 수가 다리 길이와 같다면
				sum -= bridge.poll(); // 맨 앞 트럭 다리에서 제거 
				time++; // 시간 증가
			}
		}//while
		
		// 위에서 다리를 건너던 중 while문을 빠져나왔을 수도 있으므로 다리에 남아있는 트럭들 더해주기
		return time+bridge.size(); 
	}//solution

	private static int getIntToken(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}//getIntToken

}//class
/*

4 2 10
7 4 5 6
out : 8

*/