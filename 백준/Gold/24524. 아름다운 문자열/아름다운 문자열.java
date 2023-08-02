import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// https://www.acmicpc.net/problem/24524
public class Main {
	static char[] S, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		T = br.readLine().toCharArray();
		
		br.close();
		
		int maxCnt = getMaxCnt();
		System.out.print(maxCnt);
	}//main

	private static int getMaxCnt() {
		Map<Character, Queue<Integer>> tMap = new HashMap<>();	
		int sLen = S.length, tLen = T.length;
		for(char t : T) {
			Queue<Integer> q = new LinkedList<>();
			for(int i=0; i<sLen; i++) {
				if(t == S[i]) q.add(i);
			}//for s
			tMap.put(t, q);
		}//for t
		
		Queue<Integer> q = tMap.get(T[0]);
		Queue<Integer> tmp;
		int cnt = 0;
		int prev=0, next=0;
		boolean flag;
		first:while(!q.isEmpty()) {
			flag = true;
			prev = q.poll();
			for(int t=1; t<tLen; t++) {
				tmp = tMap.get(T[t]);
				if(tmp.isEmpty()) break first;
				next = tmp.poll();
				while(!tmp.isEmpty() && next < prev) {
					next = tmp.poll();
				}
				if(next > prev) prev = next;
				else flag = false;
			}//for
			if(flag) cnt++;
		}//while
		
		return cnt;
	}//getMaxCnt

}//class