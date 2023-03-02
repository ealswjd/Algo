package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1092
public class Main_1092_배 {
	static int N, M;
	static ArrayList<Integer> box, crane;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 크레인
		N = Integer.parseInt(br.readLine());
		crane = new ArrayList<>();
		init(br, N, crane);		
		
		// 박스
		M = Integer.parseInt(br.readLine());
		box = new ArrayList<>();
		init(br, M, box);
		
		br.close();

		int time = move();
		System.out.print(time);		
	}//main
	

	private static int move() {
		int time = 0;
				
		if(box.get(0) > crane.get(0)) return -1;
		
		int idx;
		while(!box.isEmpty()) {
			idx = 0;
			for(int i=0; i<N;) {
				if(idx == box.size()) break;
				if(box.get(idx) <= crane.get(i)) {
					box.remove(idx);
					i++;
				}else {
					idx++;
				}
			}//for
			time++;
		}//while
		
		return time;
	}//move

	private static void init(BufferedReader br, int size, ArrayList<Integer> list) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}//for	
		Collections.sort(list, Collections.reverseOrder());
	}//init


}//class

/*

3
6 8 9
5
2 5 2 4 7

4
1 2 3 4
8
1 1 2 2 3 3 4 4

*/