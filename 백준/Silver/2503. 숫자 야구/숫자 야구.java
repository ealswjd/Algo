import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2503
public class Main {
	static Number[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 질문 횟수
		
		StringTokenizer st;
		numbers = new Number[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] number = st.nextToken().toCharArray(); // 민혁이가 질문한 세 자리 수
			int S = Integer.parseInt(st.nextToken());  // 스트라이크 개수
			int B = Integer.parseInt(st.nextToken());  // 볼의 개수
			numbers[i] = new Number(number, S, B);
		}//for
		br.close();
		
		int cnt = getCnt();
		System.out.print(cnt);

	}//main
	
	private static int getCnt() {
		int cnt = 0;		
		char[] num;
		for(int n=123; n<=987; n++) {
			num = getNumArr(n);
			if(num == null || wrong(num)) continue;
			cnt++;
		}//for
		
		return cnt;
	}//getCnt

	private static char[] getNumArr(int n) {
		int first = n / 100;
		n %= 100;
		int second = n / 10;
		int third = n % 10;
		
		if(first == 0 || second == 0 || third == 0) return null;
		if(first == second || first == third || second == third) return null;
		
		char[] numArr = new char[3];
		numArr[0] = (char) (first + '0');
		numArr[1] = (char) (second + '0');
		numArr[2] = (char) (third + '0');
		return numArr;
	}//getNumArr

	private static boolean wrong(char[] num) {
		int s, b;		
		char[] cur;
		for(Number number : numbers) {
			s = b = 0;
			cur = number.number;
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(cur[j] != num[i]) continue;
					
					if(i == j) s++;
					else b++;
				}//for j
			}//for i
			
			if(number.S != s || number.B != b) return true;
		}//for number
		
		return false;
	}//wrong

	static class Number {
		char[] number;
		int S;
		int B;
		public Number(char[] number, int S, int B) {
			this.number = number;
			this.S = S;
			this.B = B;
		}
	}//Number

}//class