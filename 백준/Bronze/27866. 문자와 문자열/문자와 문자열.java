import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/27866
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		int i = Integer.parseInt(br.readLine()) - 1;
		System.out.print(s[i]);
	}

}