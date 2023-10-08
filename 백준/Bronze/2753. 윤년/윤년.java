import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2753
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Y = Integer.parseInt(br.readLine());
		
		if(isLeapYear(Y)) System.out.print(1);
		else System.out.print(0);
	}//main

	private static boolean isLeapYear(int y) {
		return (y%4==0 && y%100!=0) || y%400==0;
	}//isLeap

}//class