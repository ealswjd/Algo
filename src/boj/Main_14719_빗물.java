package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14719
public class Main_14719_빗물 {
	static int H, W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = getIntToken(st); // 2차원 세계의 세로 길이
		W = getIntToken(st); // 2차원 세계의 가로 길이
		
		st = new StringTokenizer(br.readLine());
		int sum = sol(st);
		br.close();

		// 고이는 빗물의 총량을 출력하여라.
		System.out.print(sum);
	}//main

	private static int sol(StringTokenizer st) {
		Deque<Integer> dq = new ArrayDeque<>(); // 2차원 세계
		int sum = 0; // 빗물의 총량
		int h = 0; // 현재 블록 높이

		for(int i=0; i<W; i++) {
			h = getIntToken(st);
			if(dq.isEmpty()) { // 비어 있으면
				if(h==0) continue;
				else dq.offer(h);
			}else { // 비어 있지 않다면
				if(dq.size() == 1 && (dq.peek() == h || dq.peek() < h)) {
					dq.poll(); 
				}else {
					// 맨 왼쪽 블록이 현재블록보다 높고 이전 블록보다 현재가 높다면
					if(dq.peek() > h && dq.peekLast() < h){
						int tmp = h - dq.peekLast(); // 높이차 구한 후
						sum += tmp; // 총량 갱신
						dq.offer(tmp + dq.pollLast()); // 채운 블록 넣어주기
					}else if(dq.peek() <= h) { // 맨 왼쪽이랑 같거나 높으면
						sum += work(dq, 0); // 지금까지 누적된 블록 정리(빗물 총량 갱신)
					}					
				}//else
				dq.offer(h); // 현재 블록 넣어주기
			}//else
			
		}//for
		
		sum += work(dq, 1); // 남은 블록 정리(빗물 총량 갱신)
		return sum;
	}//sol

	private static int work(Deque<Integer> dq, int order) {
		int sum = 0;
		
		if(order==0) { // 앞에서부터
			while(!dq.isEmpty()) sum += dq.peek() - dq.pollLast();			
		}else { // 뒤에서부터
			int n = dq.pollLast();
			while(dq.size() > 1) {
				if(n > dq.peekLast()) sum += n - dq.pollLast();
				else n = dq.pollLast();
			}//while
		}//else
		
		return sum;
	}//work

	private static int getIntToken(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}//getIntToken

}//class

/*

예제 입력
4 4
3 0 1 4
answer : 5


4 4
3 0 4 1
answer : 3

1 1
1
answer : 0

5 5
1 0 3 2 4
answer : 2

10 5
2 4 1 5 4
answer : 3

100 18
28 100 43 33 37 100 87 15 52 35 54 86 60 24 99 56 4 40
answer : 602

10000 76
992 3508 6427 8970 1683 2114 3762 5945 8251 8349 2672 1813 2294 4623 1089 1724 5577 2362 5035 5028 3344 9321 3104 8877 2534 5864 9791 3221 5571 8763 773 6687 4909 3330 1427 8554 9688 6293 1899 3573 8597 5976 2772 1410 5182 888 4671 8106 782 6735 2832 9642 9824 1203 858 2643 2024 3798 5114 4253 72 2427 7137 1488 7324 4837 3656 6194 2600 8530 2413 5892 6404 7682 6775 7467
answer : 316105

*/