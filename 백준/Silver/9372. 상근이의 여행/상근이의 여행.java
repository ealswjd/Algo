import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9372
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); 
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 국가 수
			int M = Integer.parseInt(st.nextToken()); // 비행기 종류
			
			while(M-->0) {
				st = new StringTokenizer(br.readLine());
				Integer.parseInt(st.nextToken());
				Integer.parseInt(st.nextToken());
			}//while		
			
			ans.append(N-1).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

}//class