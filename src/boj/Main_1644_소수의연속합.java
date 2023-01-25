package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/1644
public class Main_1644_소수의연속합 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		br.close();
        
        ArrayList<Integer> list = new ArrayList<>();
		for(int i=2; i<=n; i++) {
			boolean isPrime = true;
			for(int j=2; j*j<=i; j++) {
				if(i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) list.add(i);
		}
        
		System.out.print(getCnt(list, n));
	}//main
	
	static int getCnt(ArrayList<Integer> list, int n) {
		int cnt = 0, sum = 0;
		int start = 0, end = 0;
		int size = list.size();
		
		while(true) {
			if(sum >= n) {
				if(sum == n) cnt++;	
				sum -= list.get(start++);
			}
			else if(end == size) break;
			else sum += list.get(end++);
		}
		return cnt;
	}//getCnt
	
}// Main class
