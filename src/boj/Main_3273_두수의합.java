package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3273_두수의합 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        int x = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
		System.out.println( getCnt(arr, x, n) );
	}//main
    
    static int getCnt(int[] arr, int x, int n) {
		int start = 0;
		int end = n-1;
		int cnt = 0;
		int sum = 0;
		
		while(start < end) {
			sum = arr[start] + arr[end];
			
			if(sum == x) cnt++;			
			if(sum <= x) start++;
			else end--;
		}		
		return cnt;
	}//getCnt

}// Main class
