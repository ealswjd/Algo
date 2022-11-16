package programmers;

/* https://programmers.co.kr/learn/courses/30/lessons/42895
 * 동적계획법(Dynamic Programming) > N으로 표현   */
public class ExpressWithN {
	private int min = 9; // 최솟값이 8보다 크면 -1 리턴
	private int target; // 목표 값
	private int n; // 사용할 숫자
	
	public int solution(int N, int number) {
		target = number;
        	n = N;
        	dfs(0, 0); // 연산 횟수, 연산 결괏값
        	return (min > 8) ? -1 : min; // 최솟값이 8보다 크면 -1 반환 작으면 min 반환
        }//solution
	
	void dfs(int depth, int prev) {
		if(depth > 8) return;
		if(prev == target) { // 목표값 달성하면
			min = Math.min(min, depth); // min 값 변경 
			return;
		}
		
		int temp = n;
		for(int i=0; i<8-depth; i++) {
			int ndepth = depth + i + 1;
			// 사칙연산 
			dfs(ndepth, prev + temp);
			dfs(ndepth, prev - temp);
			dfs(ndepth, prev / temp);
			dfs(ndepth, prev * temp);
			
			temp = temp * 10 + n; // 자릿수 늘려주기
		}
	}//dfs

}//class
