import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11723
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());

		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int x=0, s=0;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "add":
				x = Integer.parseInt(st.nextToken()) - 1;
				s |= 1 << x;
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken()) - 1;
				if((s & (1 << x)) == (1 << x)) s ^= 1 << x;	
				break;
			case "check":
				x = Integer.parseInt(st.nextToken()) - 1;
				if((s & (1 << x)) == (1 << x)) ans.append(1);
				else ans.append(0);
				ans.append("\n");
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken()) - 1;
				s ^= 1 << x;
				break;
			case "all":
				for(int i=1; i<=20; i++) {
					s |= 1 << (i-1);
				}//for
				break;
			case "empty":
				s = 0;
				break;
			}//switch
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

}//class