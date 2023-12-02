import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/1874
public class Main {
	static int N, max;
	static Stack<Integer> stack;
	static StringBuilder ans;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		N = T;

		flag = true;
		stack = new Stack<>();
		ans = new StringBuilder();
		int num;
		while(T-->0) {
			num = Integer.parseInt(br.readLine());
			sol(num);
		}//while
		br.close();

		if(flag) System.out.print(ans);
		else System.out.print("NO");
	}//main

	private static void sol(int num) {
		if(!flag) return;
		int n = max+1;
		
		if(stack.isEmpty()) {
			stack.add(n);
			max = n;
			ans.append("+\n");
			n++;
		}//if
		
		while(!stack.isEmpty()) {
			if(stack.peek().equals(num)) {
				stack.pop();
				ans.append("-\n");
				return;
			}else if(stack.peek() < num){	
				if(n > N) {
					flag = false;
					return;
				}
				stack.add(n);
				ans.append("+\n");
				max = Math.max(n, max);
				n++;
			}else {
				stack.pop();
				ans.append("-\n");
			}
		}//while
		
		flag = false;		
	}//sol

}//class