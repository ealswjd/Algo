import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16432
public class Main {
	static int N; // 떡을 팔아야 할 날의 수
	static int[] result, riceCake, visited;
	static boolean possible;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 떡을 팔아야 할 날의 수
		result = new int[N]; // 호랑이에게 주어야 할 떡
		riceCake = new int[N]; // i번째 날에 만든 떡
		visited = new int[N]; // 떡 체크
		
		StringTokenizer st;
		for(int i=0,cnt=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			while(cnt-->0) {
				riceCake[i] |= 1 << Integer.parseInt(st.nextToken());
			}//while
		}//for
		br.close();
		
		dfs(0, 0);
		
		if(!possible) System.out.print(-1);
		else {
			StringBuilder ans = new StringBuilder();
			for(int r : result) {
				ans.append(r).append('\n');
			}//for
			System.out.print(ans);
		}//else
		
	}//main

	private static void dfs(int day, int prev) {
		if(possible) return;
		if(day == N) {
			possible = true;
			return;
		}//if
		
		for(int i=1; i<10; i++) {
			if(checked(day, prev, i)) continue;
			result[day] = i;
			visited[day] |= 1 << i;
			dfs(day+1, i);
		}//for
		
	}//dfs

	private static boolean checked(int day, int prev, int i) {
		return i==prev || (riceCake[day] & (1 << i)) == 0 
            || (visited[day] & (1 << i)) != 0 || possible;
	}//checked

}//class