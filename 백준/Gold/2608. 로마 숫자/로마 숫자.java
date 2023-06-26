import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/2608
public class Main {
	static StringBuilder ans;
	static Map<Integer, Character> arabiaMap;
	static Map<Character, Integer> romeMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 	
		
		int[] arabia = {1, 5, 10, 50, 100, 500, 1000};
		char[] rome = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		arabiaMap = new HashMap<>();
		romeMap = new HashMap<>();
		for(int i=0; i<arabia.length; i++) {
			arabiaMap.put(arabia[i], rome[i]);
			romeMap.put(rome[i], arabia[i]);
		}
		
		ans = new StringBuilder();
		char[] numbers; // 로마 숫자
		int result = 0;
		for(int i=0; i<2; i++) {
			numbers = br.readLine().toCharArray();
			result += romeToArabia(numbers);			
		}
		br.close();
		ans.append(result).append("\n");
		
		arabiaToRome(String.valueOf(result));		
		
		System.out.print(ans);
	}//main

	// 로마 -> 아라비아
	private static int romeToArabia(char[] numbers) {
		int len = numbers.length;
		int prev = romeMap.get(numbers[len-1]);
		int sum = prev;		
		int cur;
		
		for(int i=len-2; i>=0; i--) {
			cur = romeMap.get(numbers[i]);
			if(prev > cur) sum -= cur;
			else sum += cur;
			prev = cur;
		}//for
		
		return sum;
	}//romeToArabia

	// 아라비아 -> 로마
	private static void arabiaToRome(String numbers) {
		int len = numbers.length();
		int n;
		
		for(int i=0; i<len; i++) {
			n = numbers.charAt(i) - '0'; // 숫자 하나씩
			switch (len-i) {
			case 4: // 1000의 자리
				while(n-->0) ans.append(arabiaMap.get(1000));
				break;
			case 3: // 100의 자리
				addRome(n, 100);				
				break;
			case 2: // 10의 자리
				addRome(n, 10);				
				break;
			case 1: // 1의 자리
				addRome(n, 1);	
				break;
			}//switch
		}//for

	}//arabiaToRome

	// 숫자 한자리씩 문자로 변경
	private static void addRome(int n, int key) {
		int cnt;
		if(n==1 || n==5) {
			ans.append(arabiaMap.get(key*n));
		}else if(n==4 || n==9) {
			ans.append(arabiaMap.get(key));
			ans.append(arabiaMap.get((n+1)*key));
		}else {
			if(n<4) {
				while(n-->0) ans.append(arabiaMap.get(key));
			}else {
				cnt = n-5;
				ans.append(arabiaMap.get(key*5));
				while(cnt-->0) ans.append(arabiaMap.get(key));
			}
		}		

	}//getRome

}//class