import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {
	static HashMap<Character, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		br.close();
		
		map = new HashMap<>();
		map.put(')', 2);
		map.put(']', 3);
		
		int sum = getSum(input);
		System.out.print(sum);
	}//main

	private static int getSum(char[] input) {
		Stack<Info> stack = new Stack<>();
		int sum = 0;
		int score = 0;
		
		// (()[[]])([])
		for(char c : input) {
			if(c == '(' || c == '[') {
				if(c == '(') stack.add(new Info(true, 2));
				else if(c == '[') stack.add(new Info(true, 3));	
				continue;
			}//if
			
			// 열린괄호 없는데 닫힌괄호 들어오면 
			if(stack.isEmpty()) return 0; 
			
			// 스택에 열린 괄호 들어 있으면
			if(stack.peek().isOpen) {
				if(map.get(c) != stack.peek().score) return 0; // 짝 안맞으면 올바르지 못한 괄호열				
				stack.add(new Info(false, stack.pop().score)); // 점수 줌
				if(stack.size() == 1) sum += stack.pop().score;
			}else { // 점수 들어 있으면
				while(!stack.isEmpty() && !stack.peek().isOpen) {
					score += stack.pop().score;
				}//while
				
				if(stack.isEmpty() || map.get(c) != stack.peek().score) return 0;
				
				if(score > 0) score *= stack.pop().score; 
				else score = stack.pop().score;
				
				if(stack.isEmpty()) sum += score;
				else stack.add(new Info(false, score));
				
				score = 0;
			}//else

		}//for
		
		if(!stack.isEmpty()) {
			return 0;
		}
		
		return sum;
	}//getSum
	
	static class Info {
		boolean isOpen;
		int score;
		public Info(boolean isOpen, int score) {
			this.isOpen = isOpen;
			this.score = score;
		}
	}//Info

}//class