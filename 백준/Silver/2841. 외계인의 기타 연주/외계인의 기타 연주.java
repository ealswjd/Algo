import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2841
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Stack<Integer>> melody = new ArrayList<>();
		// 1번 줄부터 6번 줄까지 총 6개의 줄이 있고
		for(int i=0; i<7; i++) {
			melody.add(new Stack<>());
		}//for
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 멜로디에 포함되어 있는 음의 수
		int P = Integer.parseInt(st.nextToken()); // 한 줄에 있는 프렛의 수 
		
		int cnt = 0; // 멜로디를 연주하는데 필요한 최소 손가락 움직임
		int lineNum, fret;
		Stack<Integer> line;
		while(N-->0) {
			st = new StringTokenizer(br.readLine()); // 멜로디의 한 음을 나타내는 두 정수
			lineNum = Integer.parseInt(st.nextToken()); // 줄의 번호
			fret = Integer.parseInt(st.nextToken()); // 줄에서 눌러야 하는 프렛의 번호
			line = melody.get(lineNum);
			
			while(!line.isEmpty() && line.peek() > fret) {
				line.pop();
				cnt++;
			}//while
			if(!line.isEmpty() && line.peek() == fret) continue;
			
			line.add(fret);
			cnt++;
		}//while
		br.close();

		System.out.print(cnt);
	}//main

}//class
