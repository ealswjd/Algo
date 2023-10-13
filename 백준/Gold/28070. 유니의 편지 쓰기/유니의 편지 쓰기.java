import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int size = 96002; // 9999-12
	static int N; // 유니의 친구 수
	static int[] cnt; // 군대에 있는 친구들

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = new int[size];
		
		StringTokenizer st;
		int start, end;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			start = convertToNumber(st.nextToken()); // 입대
			end = convertToNumber(st.nextToken()); // 전역
			cnt[start]++;
			cnt[end+1]--;
		}//for
		
		int maxCnt = 0;
		int result = 0;
		for(int i=1; i<size; i++) {
			cnt[i] += cnt[i-1];
			if(maxCnt < cnt[i]) {
				maxCnt = cnt[i];
				result = i; // 가장 많은 달
			}//if
		}//for

		System.out.print(convertToDate(result));
	}//main

	private static int convertToNumber(String date) {
		int year = Integer.parseInt(date.substring(0, 4)) - 2000;
		int month = Integer.parseInt(date.substring(5));
		return 12 * year + month;
	}//convertToNumber
	
	private static String convertToDate(int date) {
		int year = date / 12 + 2000;
		int month = date % 12;
		if(month==0) month = 12;
		if(month==12) year--;
		return String.format("%d-%02d", year, month);
	}//convertToNumber

}//class
