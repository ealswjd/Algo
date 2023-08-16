import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10951
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		String str;
		while( (str=br.readLine()) != null && !str.equals("")){
			st = new StringTokenizer(str);
			int a = Integer.parseInt(
					st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ans.append(a + b).append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

}//class