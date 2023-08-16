import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11720
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		char[] num = br.readLine().toCharArray();
		br.close();
		
		int sum = 0;
		for(int n : num) {
			sum += n - '0';
		}//for
		
		System.out.print(sum);
	}//main

}//class