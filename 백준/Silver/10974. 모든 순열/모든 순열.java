import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/10974
public class Main {
	static int N;
	static int[] num;
	static StringBuilder ans;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		br.close();
		
		num = new int[N];
		ans = new StringBuilder();
		dfs(0, 0);
		
		System.out.print(ans);
	}//main

	private static void dfs(int cnt, int pick) {
		if(cnt == N) {
			for(int n : num) ans.append(n).append(" ");
			ans.append("\n");
			return;
		}//if
		
		for(int i=1; i<=N; i++) {
			if((pick & (1 << i)) != 0) continue;
			num[cnt] = i;
			dfs(cnt+1, pick | (1 << i));		
		}//for
		
	}//dfs

}//class