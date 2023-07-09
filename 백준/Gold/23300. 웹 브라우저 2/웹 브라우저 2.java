import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23300
public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		char c;
		int page=0, cur=0;
		Deque<Integer> back = new LinkedList<>();
		Deque<Integer> front = new LinkedList<>();
		ArrayList<Integer> tmp;
		StringBuilder ans = new StringBuilder();
		while(Q-->0) {
			st = new StringTokenizer(br.readLine());
			c = st.nextToken().charAt(0);
			
			switch (c) {
			case 'B': // 뒤로
				if(back.isEmpty()) break;
				if(cur != 0) front.offerFirst(cur);
				cur = back.pollFirst();
				
				break;
			case 'F': // 앞으로
				if(front.isEmpty()) break;
				if(cur != 0) back.offerFirst(cur);
				cur = front.pollFirst();
				
				break;
			case 'C': // 압축
				Iterator<Integer> it = back.iterator();
				if(!it.hasNext()) break;
				
				int prev = it.next();
				tmp = new ArrayList<>();
				tmp.add(prev);
				int num;
				while(it.hasNext()) {
					num = it.next();
					if(num == prev) continue;
					tmp.add(num);
					prev = num;
				}//while
				back = new LinkedList<>();
				for(int n : tmp) {
					back.offer(n);
				}//for
				
				break;

			default: // 접속
				page = Integer.parseInt(st.nextToken()); // 접속할 페이지
				if(!front.isEmpty()) front = new LinkedList<>();
				if(cur != 0) back.offerFirst(cur);
				cur = page;
				
				break;
			}//switch
			
		}//while
		br.close();
		
		// 1. 현재 접속 중인 페이지 번호
		ans.append(cur).append("\n"); // 현재 접속 중인 페이지 번호
		
		// 2. 뒤로 가기 공간에서 가장 최근에 방문한 순서대로 페이지의 번호를 출력한다. 저장된 페이지가 없다면 -1 을 출력
		if(back.isEmpty()) ans.append(-1);
		else {
			while(!back.isEmpty()) ans.append(back.pollFirst()).append(" ");
		}
		ans.append("\n");
		
		// 3. 앞으로 가기 공간에서 가장 최근에 방문한 순서대로 페이지의 번호를 출력한다. 저장된 페이지가 없다면 -1 을 출력
		if(front.isEmpty()) ans.append(-1);
		else {
			while(!front.isEmpty()) ans.append(front.pollFirst()).append(" ");
		}
		ans.append("\n");
		
		System.out.print(ans);
	}//main

}//class