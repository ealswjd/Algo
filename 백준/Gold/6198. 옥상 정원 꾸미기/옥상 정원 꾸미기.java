import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6198
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 빌딩의 개수

		Stack<Integer> stack = new Stack<>();
		long sum = 0;
		int cur;
		while(N-->0) {
			cur = Integer.parseInt(br.readLine());
			while(!stack.isEmpty() && stack.peek() <= cur) stack.pop();
			stack.add(cur);
			sum += stack.size()-1;
		}//while					
		br.close();		
		
		System.out.print(sum);
	}//main

}//class