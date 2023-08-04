import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] pass;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str;
		while(true) {
			str = br.readLine();
			if(str.equals("end")) break;
			pass = str.toCharArray();
			sb.append("<").append(str).append("> ");
			
			if(acceptable()) sb.append("is acceptable.");
			else sb.append("is not acceptable.");
			
			sb.append("\n");
		}//while
		br.close();
		System.out.print(sb);
	}//main

	private static boolean acceptable() {
		int aeiouCnt = 0, aCnt=0, bCnt=0;
		char prev = pass[0];
		char cur;
		boolean flagPrev = aeiouCheck(prev);
		boolean flagCur = false;
		if(flagPrev) {
			aeiouCnt++;
			aCnt++; // 모음
		}else bCnt++;
		
		for(int i=1,size=pass.length; i<size; i++) {
			cur = pass[i];
			flagCur = aeiouCheck(cur);
			// 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
			if(flagCur) aeiouCnt++;
			// 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
			if(flagPrev == flagCur) {
				if(flagCur && ++aCnt == 3) { // 모음
					return false;
				}else if(!flagCur && ++bCnt == 3) { // 자음
					return false;
				}
			}else {
				if(flagCur) {
					aCnt++;
					bCnt = 0;
				}else {
					bCnt++;
					aCnt = 0;
				}
			}
			// 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
			if(prev==cur && cur != 'e' && cur != 'o') {
				return false;
			}
			prev = cur;
			flagPrev = flagCur;
		}//for
		
		return aeiouCnt > 0;
	}//acceptable

	private static boolean aeiouCheck(char c) {		
		return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
	}//aeiouCheck

}//class