import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9527
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		br.close();

		long cnt = getCnt(B) - getCnt(A-1);
		System.out.print(cnt);
	}//main
	
	private static long getCnt(long num) {
		if(num == 0 || num == 1) return num;
		int cnt = 0;
		long pow = 1;
		while(pow * 2 <= num) {
			pow *= 2;
			cnt++;
		}//while
		return cnt * pow / 2 + (num - pow + 1) + getCnt(num - pow);
	}//getCnt

}//class