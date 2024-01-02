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
		Stack<Integer> result = new Stack<>();
		Stack<Integer> tmp = new Stack<>();
		
		for(int i=N-1; i>=0; i--) {
			while(!tmp.isEmpty() && tmp.peek() <= A[i]) {
				tmp.pop();
			}//while
			
			if(tmp.isEmpty()) result.add(-1);
			else result.add(tmp.peek());
			
			tmp.add(A[i]);
		}//for
		
		while(!result.isEmpty()) {
			ans.append(result.pop()).append(' ');
		}//while
		
		System.out.print(ans);
	}//getBig


}//class