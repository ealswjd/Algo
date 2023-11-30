import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1327
public class Main {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		char[] origin = br.readLine().replaceAll(" ", "").toCharArray();
		char[] tmp = Arrays.copyOf(origin, N);
		br.close();
		
		Arrays.sort(origin); // 오름차순
		int cnt = bfs(origin, tmp);
		System.out.print(cnt);
	}//main
	
	private static int bfs(char[] origin, char[] tmp) {
		Set<String> visited = new HashSet<>(); // 중복 체크
		Queue<Number> q = new LinkedList<>();
		q.offer(new Number(String.valueOf(tmp), 0)); // 기존 순열
		String target = String.valueOf(origin); // 목표 : 오름차순 순열
		
		Number cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.num.equals(target)) return cur.cnt; // 오름차순 완료
			
			if(visited.contains(cur.num)) continue; // 중복됨
			visited.add(cur.num);
			
			for(int i=0, max=N-K; i<=max; i++) {
				q.offer(new Number(reverseNum(cur.num, i, i+K), cur.cnt+1));
			}//for
			
		}//while
		
		return -1;
	}//bfs

	private static String reverseNum(String num, int s, int e) {
		StringBuilder n = new StringBuilder();
		n.append(num.substring(0, s));
		
		for(int i=s+K-1; i>=s; i--) {
			n.append(num.charAt(i));
		}//for
		
		n.append(num.substring(e));
		return String.valueOf(n);
	}//reverseNum

	static class Number {
		String num;
		int cnt;
		public Number(String num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}//Number

}//class