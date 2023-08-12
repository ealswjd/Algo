import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/1918
public class Main {
	static char[] notation;
	static final char OPEN = '(';
	static final char CLOSE = ')';

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		notation = br.readLine().toCharArray();

		getPostfixNotation();
	}//main

	private static void getPostfixNotation() {
		Stack<Character> stack = new Stack<>();
		StringBuilder ans = new StringBuilder();
		
		char c;
		for(int i=0, len=notation.length; i<len; i++) {
			c = notation[i];
			if('A' <= c && c <= 'Z') ans.append(c);
			else if(c == OPEN) stack.add(c);
			else if(c == CLOSE) {
				if(!stack.isEmpty()) {
					while(stack.peek() != OPEN) {
						ans.append(stack.pop());
					}//while
					stack.pop();
				}//if
			} else {
				if(c == '*' || c == '/') {
					while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
						ans.append(stack.pop());
					}//while
				}else {
					while(!stack.isEmpty() && (stack.peek() != OPEN && stack.peek() != CLOSE) ) {
						ans.append(stack.pop());						
					}//while
				}//id
				stack.add(c);
			}//else
			
		}//for
		
		while(!stack.isEmpty()) {
			ans.append(stack.pop());
		}//while
		
		System.out.print(ans);
	}//getPostfixNotation

}//class