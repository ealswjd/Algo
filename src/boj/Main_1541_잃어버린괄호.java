package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1541_잃어버린괄호 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first = br.readLine().split("-");
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<first.length; i++) {
			String[] tmp = first[i].split("\\+");
			int sum = 0;
			for(int j=0; j<tmp.length; j++) {
				sum += Integer.parseInt(tmp[j]);
			}//for j
			if(min == Integer.MAX_VALUE) min = sum;
			else min -= sum;
		}//for i

		System.out.println(min);
	}//main

}//class

/*

55-50+40
------> -35

*/