import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/9012
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 데이터 개수
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		char[] bracket; // 괄호
		Stack<Character> stack;
		tc:while(T-->0) {
			bracket = br.readLine().toCharArray();
			stack = new Stack<>();
			for(char c : bracket) {
				switch (c) {
				case '(':
					stack.add(c);
					break;
				case ')':
					if(stack.isEmpty()) {
						ans.append("NO").append("\n");
						continue tc;
					}
					stack.pop();
					break;
				}//switch
			}//for
			if(stack.isEmpty()) ans.append("YES").append("\n");
			else ans.append("NO").append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

}//class