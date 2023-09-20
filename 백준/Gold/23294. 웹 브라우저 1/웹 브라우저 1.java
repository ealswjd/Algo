import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23294
public class Main {
	static int N, C, curPage, curSize; 
	static int[] cSize; // 페이지 캐시 크기
	static Deque<Integer> back; // 뒤로 가기 공간
	static Deque<Integer> front; // 앞으로 가기 공간
	static ArrayList<Integer> tmp; // 임시 공간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접속할 수 있는 웹페이지의 종류의 수
		int Q = Integer.parseInt(st.nextToken()); // 사용자가 수행하는 작업의 개수
		C = Integer.parseInt(st.nextToken()); // 최대 캐시 용량
		
		cSize = new int[N+1]; // 페이지 캐시 크기
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
		ans.append(curPage).append("\n"); // 현재 페이지 번호 출력		
		outputInOrder(back, ans); // 뒤로 가기 공간		
		outputInOrder(front, ans); // 앞으로 가기 공간 
		System.out.print(ans);
	}//print

	private static void outputInOrder(Deque<Integer> cache, StringBuilder ans) {
		// 가장 최근에 방문한 순서대로 페이지 번호 출력
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
			
			Iterator<Integer> it = back.iterator();			
			int prev = it.next();
			tmp = new ArrayList<>();
			tmp.add(prev);
			int cur;
			
			while(it.hasNext()) {
				cur = it.next();
				/* 삭제된 페이지가 차지하고 있던 용량만큼 현재 사용 캐시에서 줄어든다. */
				if(cur == prev) {
					curSize -= cSize[cur];
					continue;
				}//if
				tmp.add(cur);
				prev = cur;
			}//while
			
			back.clear();
			for(int n : tmp) {
				back.offer(n);
			}//for
			
			break;
		default :  // 접속
			int page = Integer.parseInt(st.nextToken());
			/* 앞으로 가기 공간에 저장된 페이지가 모두 삭제 */
			while(!front.isEmpty()) {
				curSize -= cSize[front.poll()];
			}//while

			if(curPage != 0) back.add(curPage);
			curPage = page;
			curSize += cSize[curPage];

			if(curSize > C) {
				while(!back.isEmpty() && curSize > C) {
					curSize -= cSize[back.pollFirst()];
				}//while
			}//if
			break;				
		}//switch
		
	}//work

	private static void move(Deque<Integer> cache1, Deque<Integer> cache2) {
		/* cache1 공간에 저장된 페이지가 0개일 때 이 작업은 무시 */
		if(cache1.isEmpty()) return;
		/* 현재 보고 있던 웹페이지를 cache2 공간에 저장 */
		cache2.add(curPage);
		/* cache1 공간에서 방문한지 가장 최근의 페이지에 접속 */
		curPage = cache1.pollLast();		
	}//move

}//class