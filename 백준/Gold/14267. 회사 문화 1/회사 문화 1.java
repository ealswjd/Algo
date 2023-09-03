import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14267
public class Main {
	static int N; // 회사의 직원 수
	static int[] cnt; // 1번부터 n번의 직원까지 칭찬을 받은 정도
	static int[] parent; // 직속상사

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 회사의 직원 수
		int M = Integer.parseInt(st.nextToken()); // 최초의 칭찬의 횟수
		
		st = new StringTokenizer(br.readLine());
		parent = new int[N+1];

		for(int i=1; i<=N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
		}//for
		
		cnt = new int[N+1];
		int num, w;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken()); // 직속 상사로부터 칭찬을 받은 직원 번호 i
			w = Integer.parseInt(st.nextToken()); // 칭찬의 수치 w
			cnt[num] += w;
		}//while
		br.close();
		
		for(int i=2; i<=N; i++) {
			cnt[i] += cnt[parent[i]];
		}//for

		StringBuilder ans = new StringBuilder();
		for(int i=1; i<=N; i++) {
			ans.append(cnt[i]).append(" ");
		}//for
		System.out.print(ans);
	}//main


}//class