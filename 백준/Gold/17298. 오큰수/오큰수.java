import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17298
public class Main {
	static int N;
	static int[] A;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();
		
		getBig();
	}//main

	private static void getBig() {
		StringBuilder ans = new StringBuilder();
		int[] result = new int[N];
		Stack<Integer> stack = new Stack<>();
		
		for(int i=N-1; i>=0; i--) {
			while(!stack.isEmpty() && stack.peek() <= A[i]) {
				stack.pop();
			}//while
			
			if(stack.isEmpty()) result[i] = -1;
			else result[i] = stack.peek();
			
			stack.add(A[i]);
		}//for
		
		for(int num : result) {
			ans.append(num).append(' ');
		}//for
		
		System.out.print(ans);
	}//getBig

}//class