import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		Deque<Integer> dq = new ArrayDeque<>();
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int x;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "push_front":
				x = Integer.parseInt(st.nextToken());
				dq.addFirst(x);
				break;
			case "push_back":
				x = Integer.parseInt(st.nextToken());
				dq.addLast(x);				
				break;
			case "pop_front":
				if(!dq.isEmpty()) ans.append(dq.pollFirst());
				else ans.append(-1);
				ans.append('\n');
				break;
			case "pop_back":
				if(!dq.isEmpty()) ans.append(dq.pollLast());
				else ans.append(-1);
				ans.append('\n');				
				break;
			case "size":
				ans.append(dq.size()).append('\n');
				break;
			case "empty":
				if(dq.isEmpty()) ans.append(1);
				else ans.append(0);
				ans.append('\n');
				break;
			case "front":
				if(!dq.isEmpty()) ans.append(dq.peekFirst());
				else ans.append(-1);
				ans.append('\n');
				break;
			case "back":
				if(!dq.isEmpty()) ans.append(dq.peekLast());
				else ans.append(-1);
				ans.append('\n');
				break;
			}//switch
			
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

}//class