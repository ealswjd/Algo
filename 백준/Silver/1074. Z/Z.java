import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c;
	static int result; // r행 c열을 몇 번째로 방문했는지 출력

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 2^N × 2^N
		r = Integer.parseInt(st.nextToken()); // r행 c열
		c = Integer.parseInt(st.nextToken()); // r행 c열		
		
		int size = 1<<N;// 2^N  ->  1 << N
//		int size = (int) Math.pow(2, N);

		f(size, 0, 0); 
	}//main

	private static void f(int size, int x, int y) {
		if(x==r && y==c) {
			System.out.println(result);
			return;
		}
		int half = size/2;
		if(r < x + half && c < y + half) { // 1사분면
			f(half, x, y); 
		}
		if(r < x + half && c >= y + half) { // 2사분면
			result += (size*size) / 4;
			f(half, x, y+half); 
		}
		if(r >= x + half && c < y + half) { // 3사분면
			result += ((size*size) / 4) * 2;
			f(half, x+half, y); 
		}
		if(r >= x + half && c >= y + half) { // 4사분면
			result += ((size*size) / 4) * 3;
			f(half, x+half, y+half); 	
		}
		
	}//division

}//class