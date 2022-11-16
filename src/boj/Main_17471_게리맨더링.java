package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17471
//17471. 게리맨더링
public class Main_17471_게리맨더링 {
	static int N, total, min=Integer.MAX_VALUE;
	static int[] countArr; // 주민 수
	static boolean[] visitedA; // a 구역 방문 체크
	static ArrayList<ArrayList<Integer>> list; // 연결 정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		countArr = new int[N+1]; // 주민 수
		visitedA = new boolean[N+1]; // a 구역 방문 체크
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			countArr[i] = Integer.parseInt(st.nextToken());
			total += countArr[i]; // 총 주민 수
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				list.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		selectA(1, 0); // 1번 구역부터, 횟수, a구역 주민 수
		
		min = min==Integer.MAX_VALUE ? -1 : min;
		System.out.print(min);
	}//main

	private static void selectA(int num, int a_sum) {// 1번 구역부터, 횟수, a구역 주민 수
		if(num == N+1) {
			if(isAvailable()) { // 가능한 방법이라면
				int b_sum = total - a_sum;
				min = Math.min(min, Math.abs(a_sum-b_sum));
			}
			return;
		}

		// 선택
		visitedA[num] = true;
		selectA(num+1, a_sum+countArr[num]);
		
		// 미선택
		visitedA[num] = false;
		selectA(num+1, a_sum);
	}//selectA

	private static boolean isAvailable() {
		int areaA = -1, areaB = -1;
		boolean[] checked = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			if(visitedA[i]) { // a 구역
				areaA = i;
				break;
			}
		}		
		for(int i=1; i<=N; i++) {
			if(!visitedA[i]) { // b 구역
				areaB = i;
				break;
			}
		}
		
		// 무조건 2개의 구역으로 나누어져야 함
		if(areaA == -1 || areaB == -1) return false;
		
		Queue<Integer> q = new LinkedList<>();
		// a구역 검사
		q.offer(areaA);
		checked[areaA] = true;
		while(!q.isEmpty()) {
			areaA = q.poll();
			for(int i=0, size=list.get(areaA).size(); i<size; i++) {
				int idx = list.get(areaA).get(i);
				if(checked[idx] || !visitedA[idx]) continue;
				checked[idx] = true;
				q.offer(idx);
			}
		}
		
		// b구역 검사
		q.offer(areaB);
		checked[areaB] = true;
		while(!q.isEmpty()) {
			areaB = q.poll();
			for(int i=0, size=list.get(areaB).size(); i<size; i++) {
				int idx = list.get(areaB).get(i);
				if(checked[idx] || visitedA[idx]) continue;
				checked[idx] = true;
				q.offer(idx);
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(!checked[i]) return false; // 이어지지 않은 지역이 있다면 불가능한 방법
		}
		
		return true;
	}//isAvailable

	


}//class
