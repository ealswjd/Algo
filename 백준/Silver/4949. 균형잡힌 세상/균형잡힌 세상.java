import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://www.acmicpc.net/problem/4949
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Character, Character> map = new HashMap<>();
		map.put('[', ']');
		map.put('(', ')');
		
		StringBuilder ans = new StringBuilder();
		Stack<Character> stack;
		String line;
		char c;
		out:while(true) {
			line = br.readLine();
			if(line.length() == 1 && line.charAt(0) == '.') break;
			stack = new Stack<>();
			for(int i=0, len=line.length(); i<len; i++) {
				c = line.charAt(i);
				if(!check(c)) continue;
				if(c == '[' || c == '(') {
					stack.add(c);
				}else {
					if(stack.isEmpty() || (!stack.isEmpty() && map.get(stack.peek()) != c)) {
						ans.append("no\n");
						continue out;
					}
					if(map.get(stack.peek()) == c) stack.pop();
				}
			}//for
			if(stack.isEmpty()) ans.append("yes\n");
			else ans.append("no\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static boolean check(char c) {
		return c == '[' || c == ']' || c == '(' || c == ')';
	}//check

}//class