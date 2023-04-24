import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2631
public class Main {
	static int N; // 아이들의 수
	static int[] kids; // 아이들
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		kids = new int[N];
		nums = new int[N+1];
		for(int i=0; i<N; i++) {
			kids[i] = Integer.parseInt(br.readLine());
		}//for
		
		int ans = solution();
		System.out.print(ans);
	}//main

	private static int solution() {
		int ans = 1;
		nums[kids[0]] = 1;
		for(int i=1; i<N; i++) {
			for(int j=i-1; j>=0; j--) {
				if(kids[i] > kids[j]) {
					nums[kids[i]] = Math.max(nums[kids[i]], nums[kids[j]]);
				}
			}//for
			nums[kids[i]] += 1;
			ans = Math.max(ans, nums[kids[i]]);
		}//for
		
		return N - ans;
	}//solution

}//class