import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/5568
public class Main {
	static int N, K;
	static int[] num;
	static Set<String> set;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}//for
		br.close();
		
		set = new HashSet<>();
		dfs(0, "", 0);
		
		System.out.print(set.size());
	}//main

	private static void dfs(int cnt, String sum, int pick) {
		if(cnt == K) {
			set.add(sum);
			return;
		}//if
		
		for(int i=0; i<N; i++) {
			if((pick & (1 << i)) != 0) continue;
			dfs(cnt+1, sum + num[i], pick | 1 << i);
		}//for
	}//dfs

}//class