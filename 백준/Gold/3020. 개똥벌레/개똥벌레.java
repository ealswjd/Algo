import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3020
public class Main {
	static int N, H;
	static int[] cnts;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동굴 길이
		H = Integer.parseInt(st.nextToken()); // 동굴 높이
		int[] top = new int[H+1];
		int[] bottom = new int[H+1];
		cnts = new int[H];
		
		for(int i=1,h=0; i<=N; i++) {
			h = Integer.parseInt(br.readLine()); // 높이
			
			if(i%2!=0) { // 석순
				bottom[1]++;
				bottom[h+1]--;
			}else { // 종유석
				top[1]++;
				top[h+1]--;
			}
		}//for
		br.close();
		
		for(int i=1; i<=H; i++) {
			top[i] += top[i-1];
			bottom[i] += bottom[i-1];
		}//for
		
		for(int i=1; i<=H; i++) {
			cnts[i-1] += bottom[i] + top[H-i+1];
		}//for
		
		getResult();
	}//main

	private static void getResult() {
		Arrays.sort(cnts);
		int min = cnts[0];
		int start=0, end=H, mid = (start+end)/2;
		
		while(start<=end) {
			mid = (start+end)/2;
			if(cnts[mid] > min) end = mid-1;
			else if(cnts[mid] <= min) start = mid+1;
		}//while				
		
		if(cnts[mid]==min) mid++;
		StringBuilder ans = new StringBuilder();
		ans.append(min).append(" ").append(mid);
		System.out.print(ans);
	}//getResult

}//class