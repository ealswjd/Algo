import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/1213
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] hansoo = br.readLine().toCharArray(); // 임한수의 영어 이름
		br.close();
		
		getPalindrome(hansoo);
	}//main

	private static void getPalindrome(char[] hansoo) {		
		Queue<Character> tmp = new LinkedList<>(); // 짝이 안 맞는 알파벳 임시 저장
		Arrays.sort(hansoo); // 한수 영여이름 정렬	
		for(char c : hansoo) {
			tmp.offer(c);
		}
		int len = hansoo.length; // 이름 길이
		char[] palindrome = new char[len]; // 팰린드롬 이름
		int start = 0, end = len-1; // 시작 끝
		
		char cur, next;
		while(len>0) {
			cur = tmp.poll();
			if(tmp.isEmpty()) {
				palindrome[start] = cur;
				break;
			}
			next = tmp.peek();
			if(cur == next) {
				palindrome[start++] = cur;
				palindrome[end--] = tmp.poll();
				len-=2;
			}else {
				tmp.offer(cur);
				len--;
			}
		}//while
		
		StringBuilder ans = new StringBuilder();
		if(!tmp.isEmpty()) {
			if(tmp.size() == 1) {
				palindrome[start] = tmp.poll();
			}else {
				System.out.println("I'm Sorry Hansoo");
				return;
			}
		}
		
		for(char c : palindrome) {
			ans.append(c);
		}//for
		
		System.out.print(ans);
	}//getPalindrome

}//class