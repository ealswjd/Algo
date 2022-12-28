package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/7682
public class Main_7682_틱택토 {
	static char[] map;
	static int xrWin, orWin, xcWin, ocWin, xcrWin, ocrWin;
	static int xCnt, oCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			map = br.readLine().toCharArray();
			if(map[0]=='e') break;
			
			xrWin = orWin = xcWin = ocWin = xcrWin = ocrWin = 0;
			xCnt = oCnt = 0;
			for(int i=0; i<9; i++) {
				if(map[i]=='X') xCnt++;
				else if(map[i]=='O') oCnt++;
			}//for

			if(oCnt>xCnt) sb.append("invalid");
			else sb.append(sol());
			
			sb.append("\n");
		}//while
		
		System.out.print(sb);
	}//main

	private static String sol() {
		int[] rows = {0,3,6};
		int[] cols = {0,1,2};
		// 행, 열
		for(int i=0; i<3; i++) {						
			check(rows[i], 1);
			check(cols[i], 3);
		}//for
		
		if(xrWin>1 || orWin>1 || xcWin>1 || ocWin>1 || (xrWin>0 && orWin>0)) return "invalid";
			
		// 대각선
		checkCross('l', map[0]);
		checkCross('r', map[2]);
		
		if(validCheck()) return "valid";	
		return "invalid";
	}//sol


	private static boolean validCheck() {
		boolean xBingo=false, oBingo=false;		

		if(xrWin==1 || xcWin==1 || xcrWin>=1) xBingo=true;
		if(orWin==1 || ocWin==1 || ocrWin>=1) oBingo=true;
		
		if(xCnt==5 && oCnt==4) {
			return ((xrWin==0&&xcrWin==0&&xcWin==0&&ocWin==0&&orWin==0&&ocrWin==0) || (xBingo&&!oBingo)) ;
		}

		return (oBingo!=xBingo && ((xCnt==oCnt+1&&xBingo) || (oCnt==xCnt&&oBingo)));
	}//validCheck

	private static void checkCross(char order, char c) {
		if(c=='.') return;
		int[] cross = {0, 4, 8};
		if(order=='r') cross = new int[] {2, 4, 6};
		
		boolean flag = true;
		for(int i=1; i<3; i++) {
			if(c!=map[cross[i]]) {
				flag = false;
				break;
			}
		}//for
		
		if(flag) {
			if(c=='X') xcrWin++;
			else ocrWin++;
		}
	}//checkCross

	private static void check(int start, int n) {
		int max = start+(n*2);
		char c = map[start];
		if(c=='.') return;
		
		boolean flag = true;		
		for(int j=start; j<=max; j+=n) {
			if(c != map[j]) {
				flag = false;
				break;
			}
		}			

		if(flag) {
			if(c=='X') {
				if(n==1) xrWin++;
				else xcWin++;
			}
			else if(c=='O') {
				if(n==1) orWin++;
				else ocWin++;
			}
		}
	}//check


}//class

/*

XXXXOOXOO
end
valid

XOXOXOXO.
XOXOXOX..
end
invalid
valid

*/