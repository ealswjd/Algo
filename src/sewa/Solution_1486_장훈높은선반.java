package sewa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_장훈높은선반 {
	static int N, B, S, ans;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N명의 점원들
			B = Integer.parseInt(st.nextToken()); // 탑의 높이가 B 이상인 경우 선반 위의 물건을 사용
			
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				S += arr[i];
			}
			
			ans = Integer.MAX_VALUE;
			f(0, 0);
			// 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것을 출력한다.
			ans -= B;
			sb.append(ans);
			sb.append("\n");
		}//for tc

		System.out.print(sb);
	}//main

	private static void f(int idx, int sum) {
		if(sum >= ans) return;
		if(idx == N) {
			if(sum >= B) ans = Math.min(ans, sum);
			return;
		}		
		
		f(idx+1, sum+arr[idx]);
		f(idx+1, sum);
		
	}//f

}//class

/*

10
5 16
3 1 3 5 6
2 10
7 7
3 120
83 64 36
4 416
299 239 116 128
5 1535
351 228 300 623 954
10 2780
268 61 201 535 464 168 491 275 578 153
10 1162
73 857 502 826 923 653 168 396 353 874
15 8855
3711 576 9081 3280 1413 6818 5142 2981 1266 484 5757 2451 6961 4862 2086
15 57209
8903 5737 3749 8960 724 6295 1240 4325 8023 3640 2223 639 4161 5330 4260
20 78988
3811 2307 3935 5052 4936 3473 6432 7032 1560 1992 5332 7000 4020 9344 2732 8815 9924 8998 9540 4640

#1 1
#2 4
#3 27
#4 11
#5 42
#6 32
#7 2
#8 3
#9 25
#10 0

*/