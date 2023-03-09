package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2457_공주님의정원 {
	static int N;
	static int[] monthArr = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static int[] dayArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 꽃들의 총 개수
		
		init(); // dayArr 초기화
		
		PriorityQueue<Flower> pq = new PriorityQueue<>();
		StringTokenizer st;
		int startMonth,startDay,endMonth,endDay;
		Flower f;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			startMonth = Integer.parseInt(st.nextToken());
			startDay = Integer.parseInt(st.nextToken());
			endMonth = Integer.parseInt(st.nextToken());
			endDay = Integer.parseInt(st.nextToken());
			f = getFlower(startMonth, startDay, endMonth, endDay);
			if(f.start==300) continue; // 3/1 ~ 11/30 해당 안되면 넘어가
			pq.offer(f);
		}//for
		br.close();

		System.out.print( getMin(pq) );
	}//main
	
	private static int getMin(PriorityQueue<Flower> pq) {
		if(pq.isEmpty()) return 0; // 해당되는 꽃 없으면 0 리턴

		Flower cur=null, prev;
		int cnt=1;
		prev=pq.poll();

		if(prev.start > 1) return 0; // 3/1 이후부터 피면 0 리턴
		if(prev.end > dayArr[11]) return 1; // 11/30이후까지 피어있으면 하나로 충분
		
		while(!pq.isEmpty()) {
			// 11월 30일 지나면 그만 || 시작일이 이전 종료일보다 크면 공백이 생기므로 종료
			if(prev.end > dayArr[11] || pq.peek().start > prev.end) break; 
			cur = pq.peek(); // 현재 꽃
			// 이전 종료일 이전에 핀 꽃들 일단 빼
			while(!pq.isEmpty() && prev.end >= pq.peek().start) {
				// 현재 꽃 시작일이랑 같거나 현재 종료일보다 더 일찍 지면 버려
				if(pq.peek().start == cur.start || pq.peek().end <= cur.end) {
					pq.poll();
					continue;
				}
				cur = pq.poll(); // 현재 꽃 갱신
			}//while
			
			cnt++; // 개수 증가
			prev = cur; // 이전 꽃 갱신
		}//while
		
		// 11/30일까지 안 피어있으면 0
		if(prev.end <= dayArr[11]) return 0;
		return cnt; // 꽃 최소 개수
	}//getMin

	private static Flower getFlower(int sm, int sd, int em, int ed) {
		int start=0; // 시작일
		int end=0; // 종료일

		if(sm==12 || em==2) return new Flower(300, end); // 3/1 ~ 11/30 해당 안되면 300 넣어버려
		if(sm < 3) { // 시작이 3월 이전이면 3월부터로 설정
			sm = 3;
			sd = 1;
		}		
		
		start += sd;
		start += dayArr[sm-1];
		
		end += dayArr[em-1] + ed;
		
		return new Flower(start, end);
	}//getFlower
	
	private static void init() {
		dayArr = new int[13];
		
		for(int i=3; i<13; i++) {
			dayArr[i] += dayArr[i-1];
			dayArr[i] += monthArr[i];
		}//for
		
	}//init

	static class Flower implements Comparable<Flower>{
		int start; // 시작일(피는날)
		int end;   // 종료일(지는날)

		public Flower(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Flower f) {
			if(this.start == f.start) return f.end - this.end;
			return this.start - f.start;
		}

	}//Flower

}//class

/*

4
1 1 5 31
1 1 6 30
5 15 8 31
6 10 12 10
--> 2

*/