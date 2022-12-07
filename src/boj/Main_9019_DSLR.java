package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9019
public class Main_9019_DSLR {
	static int max=9999;
	static int[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // T 개의 테스트 케이스
		
		int size = max+10;
		StringTokenizer st;
		sb = new StringBuilder();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // A는 레지스터의 초기 값
			int B = Integer.parseInt(st.nextToken()); // B는 최종 값
			visited = new int[size];

			bfs(A, B);
			sb.append("\n");
		}//while

		System.out.print(sb);
	}//main
	
	private static void bfs(int A, int B) {
		Queue<DSLR> q = new LinkedList<>();
		q.offer(new DSLR(A, ""));
		visited[A] = 1;
		
		DSLR dslr;
		while(!q.isEmpty()) {
			dslr = q.poll();
			int cur = dslr.num;
			int cnt = visited[cur];	
			String str = dslr.s;
			
			if(cur == B) {
				sb.append(str);
				return;
			}
			
			int d = cur * 2;
			if(d > max) d %= 10000;
			ds(d, cnt, 'D', q, str);
			
			int s = cur==0 ? max : cur - 1;
			ds(s, cnt, 'S', q, str);
			
			lr('L', cur, q, cnt, str);
			lr('R', cur, q, cnt, str);

		}//while
		
	}//bfs

	private static void lr(char c, int cur, Queue<DSLR> q, int cnt, String str) {

		int num = 0; // 회전시킨 결과
		int x = (int)Math.log10(cur)+1; // 자릿수
		if(cur==0) {
			num = max;
		}else {
			if(x < 4) {
				String s = "";
				for(int i=0; i<4-x; i++) {
					s += "0";
				}
				s += cur;
				if(c == 'L') {
					s = s.substring(1) + s.substring(0, 1);
				}else {
					s = s.substring(3) + s.substring(0, 3);				
				}
				num = Integer.parseInt(s);
			}else {
				if(c == 'L') num = (cur%1000)*10 + (cur/1000);
				else num = (cur%10)*1000 + (cur/10);			
			}
			
		}
		
		if(num > max) num=0;
		if(visited[num] == 0 || visited[num] > cnt) {
			str += c;
			visited[num] = cnt;
			q.offer(new DSLR(num, str));
		}

	}//lr

	private static void ds(int num, int cnt, char c, Queue<DSLR> q, String str) {
		
		if(visited[num]==0 || visited[num] > cnt) {
			str += c;
			visited[num] = cnt;
			q.offer(new DSLR(num, str));
		}	

	}//ds


	static class DSLR{
		int num;
		String s;
		public DSLR(int num, String s) {
			super();
			this.num = num;
			this.s = s;
		}		
	}//DSLR

}//class

//int n = 1234;
//System.out.println(n%1000);
//System.out.println(n/1000);
//System.out.println((n%1000)*10 + (n/1000));
//System.out.println(n%10);
//System.out.println(n/10);
//System.out.println((n%10)*1000 + (n/10));
/*

3
1234 3412
1000 1
1 16
=======
LL
L
DDDD

*/