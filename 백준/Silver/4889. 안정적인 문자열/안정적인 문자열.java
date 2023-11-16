import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/4889
public class Main {
	static char[] str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder ans = new StringBuilder();
		int num = 1;
		while(true) {
			str = br.readLine().toCharArray();
			if(str[0] == '-') break;
			ans.append(num++).append(". ");
			ans.append(getCnt()).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getCnt() {
		Stack<Character> stack = new Stack<>();
		
		int cnt = 0;
		for(char c : str) {			
			if(c == '{') stack.add(c); // 열린 괄호면 스택에 넣어줌			
			else { // 닫힌 괄호면
				if(!stack.isEmpty()) stack.pop(); // 짝 제거
				else { // 짝 없으면
					stack.add('{'); // 열린 괄호로 바꾸는 연산
					cnt++; // 연산 횟수 증가
				}//else
			}//else			
		}//for
		
		// 만약 짝이 제대로 제거 안됐으면 추가적으로 연산 횟수 증가
		if(!stack.isEmpty()) cnt += stack.size() / 2;
		
		return cnt;
	}//getCnt

}//class