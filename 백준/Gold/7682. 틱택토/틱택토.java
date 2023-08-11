import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] map;
	static int xCnt, oCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			map = br.readLine().toCharArray();
			if(map[0]=='e') break;

			xCnt = oCnt = 0;
			for(int i=0; i<9; i++) {
				if(map[i]=='X') xCnt++;
				else if(map[i]=='O') oCnt++;
			}//for

			if(oCnt>xCnt) sb.append("invalid");
			else if(xCnt+oCnt==9) {
				if(xCnt!=oCnt+1 || isBingo('O')) sb.append("invalid");
				else sb.append("valid");
			}
			else {
				if(xCnt == oCnt+1) { // x빙고
					if(isBingo('X') && !isBingo('O')) sb.append("valid");
					else sb.append("invalid");					
				}else if(xCnt == oCnt) { // o빙고
					if(isBingo('O') && !isBingo('X')) sb.append("valid");
					else sb.append("invalid");										
				}else sb.append("invalid");
			}
			
			sb.append("\n");
		}//while
		
		System.out.print(sb);
	}//main

	private static boolean isBingo(char c) {
		// 행
		int[] rows = {0,3,6};
		for(int i=0; i<3; i++) {
			if(map[rows[i]] == c && map[rows[i]+1] == c && map[rows[i]+2] == c) {
				return true;
			}
		}
		//열
		int[] cols = {0,1,2};
		for(int i=0; i<3; i++) {
			if(map[cols[i]] == c && map[cols[i]+3] == c && map[cols[i]+6] == c) {
				return true;
			}
		}
		//대각선
		return (map[0]==c&&map[4]==c&&map[8]==c) || (map[2]==c&&map[4]==c&&map[6]==c);
	}//isBingo

}//class