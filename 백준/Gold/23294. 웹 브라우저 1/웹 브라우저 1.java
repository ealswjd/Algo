import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23294
public class Main {
	static int N, C, curPage, curSize; 
	static int[] cSize; // 웹페이지 캐시 크기
	static Deque<Integer> back; // 뒤로 가기 공간
	static Deque<Integer> front; // 앞으로 가기 공간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 웹페이지의 종류의 수
		int Q = Integer.parseInt(st.nextToken()); // 작업의 개수
		C = Integer.parseInt(st.nextToken()); // 최대 캐시 용량
		
		cSize = new int[N+1]; // 웹페이지 캐시 크기
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			cSize[i] = Integer.parseInt(st.nextToken());
		}//for
		
		back = new LinkedList<>(); // 뒤로 가기 공간
		front = new LinkedList<>(); // 앞으로 가기 공간
		while(Q-->0) {
			work(new StringTokenizer(br.readLine()));		
		}//while
		br.close();

		print();
	}//main

	private static void print() {
		StringBuilder ans= new StringBuilder();				
		ans.append(curPage).append("\n"); // 현재 페이지 번호		
		outputInOrder(back, ans); // 뒤로 가기 공간		
		outputInOrder(front, ans); // 앞으로 가기 공간 
		System.out.print(ans);
	}//print

	private static void outputInOrder(Deque<Integer> cache, StringBuilder ans) {
		// 가장 최근에 방문한 순서대로 페이지의 번호를 출력
		if(cache.isEmpty()) ans.append(-1);
		else {
			while(!cache.isEmpty()) {
				ans.append(cache.pollLast()).append(" ");
			}//while
		}//else		
		ans.append("\n");
	}//outputInOrder

	private static void work(StringTokenizer st) {
		char c = st.nextToken().charAt(0);
		
		switch(c) {
		case 'B' : // 뒤로
			move(back, front);
			break; 
		case 'F' : // 앞으로
			move(front, back);
			break;
		case 'C' : // 압축
			if(back.isEmpty()) break;
	
			int prev = back.poll();
			int size = back.size();
			back.add(prev);
			int cur;
			
			while(size-->0) {
				cur = back.poll();
				/* 삭제된 페이지가 차지하고 있던 용량만큼 현재 사용 캐시에서 줄어든다. */
				if(cur == prev) {
					curSize -= cSize[cur];
					continue;
				}//if
				back.add(cur);
				prev = cur;
			}//while
			
			break;
		default :  // 접속
			int page = Integer.parseInt(st.nextToken());
			/* 앞으로 가기 공간에 저장된 페이지가 모두 삭제 */
			while(!front.isEmpty()) {
				curSize -= cSize[front.poll()];
			}//while
			/* 현재 페이지를 뒤로 가기 공간에 추가하고, 다음에 접속할 페이지가 현재 페이지로 갱신*/
			if(curPage != 0) back.add(curPage);
			curPage = page;
			curSize += cSize[curPage];
			/* 뒤로 가기 공간에서 방문한 지 가장 오래된 페이지 하나를 삭제하며, 
			 * 그 페이지가 차지하고 있던 크기가 현재 사용 캐시 용량에서 줄어든다. */
			if(curSize > C) {
				while(!back.isEmpty() && curSize > C) {
					curSize -= cSize[back.pollFirst()];
				}//while
			}//if
			break;				
		}//switch
		
	}//work

	private static void move(Deque<Integer> cache1, Deque<Integer> cache2) {
		if(cache1.isEmpty()) return;
		cache2.add(curPage);		
		curPage = cache1.pollLast();		
	}//move

}//class