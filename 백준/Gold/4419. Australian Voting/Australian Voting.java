import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 후보의 수(n<= 20)
	static int totalCnt; // 총 투표 수
	static String[] names; // 후보자들 이름
	static StringBuilder winner; // 당선자
	static Map<Integer, ArrayList<int[]>> voteList; // 투표
	static Map<Integer, Integer> voteCnt; // 후보자들의 득표 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 후보의 수(n<= 20)
		voteCnt = new HashMap<>(); // 후보자들의 득표 수
		voteList = new HashMap<>(); // 투표
		names = new String[N]; // 후보자들 이름
		
		for(int i=0; i<N; i++) {
			names[i] = br.readLine();
			voteCnt.put(i, 0);
			voteList.put(i, new ArrayList<>());
		}//for
		
		int key;
		StringTokenizer st;
		totalCnt = 0;
		String line;	
		while((line = br.readLine()) != null && line.length() > 0) {
			st = new StringTokenizer(line); // 각 줄에는 투표 내역이 입력된다. 
			totalCnt++; // 총 투표 수
			key = Integer.parseInt(st.nextToken()) - 1; // 후보 번호
			voteCnt.put(key, voteCnt.get(key) + 1);// 1 순위로 투표 
			// 2순위 부터 기록
			int[] tmp = new int[N-1];
			for(int i=0; i<N-1; i++) {
				tmp[i] = Integer.parseInt(st.nextToken()) - 1; // 후보 번호
			}//for
			voteList.get(key).add(tmp);
		}//while
		br.close();
		
		winner = new StringBuilder();
		startCounting(); // 투표 결과 집계

		// 당선된 후보의 이름, 또는 동률을 이룬 후보들의 이름이 들어있는 여러 줄을 출력한다.
		System.out.print(winner);
	}//main

	// 투표 결과 집계
	private static void startCounting() {
		PriorityQueue<Candidate> pq; // 높은 득표수순으로 정렬된 후보자들
		ArrayList<Integer> loserList; // 탈락 리스트
		int minCnt;
		Candidate candidate;
		
		while(true) {
			pq = new PriorityQueue<>(); // 높은 득표수순으로 정렬된 후보자들
			minCnt = totalCnt; // 가장 적은 득표수
			loserList = new ArrayList<>(); // 탈락 리스트
			for(int key : voteCnt.keySet()) {
				pq.offer(new Candidate(key, voteCnt.get(key)));
				minCnt = Math.min(minCnt, voteCnt.get(key));
			}//for
			
			double max = (pq.peek().cnt / (double)totalCnt) * 100 ;
			// 50% 이상을 얻는 후보가 나오거나
			if(max > 50) {
				winner.append(names[pq.peek().key]); // 당선자
				return;
			}//if
			
			// 탈락되지 않은 모든 후보가 동률이 될 때까지
			if(minCnt == pq.peek().cnt) { 
				// 득표수가 가장 많은 후보가 가장 적은 득표수랑 동일하다면 모든 후보가 동률임.
				for(int key : voteCnt.keySet()) {
					winner.append(names[key])
					.append("\n");					
				}
				return;
			}//if
			
			while(!pq.isEmpty()) {
				candidate = pq.poll();
				// 가장 적은표를 받은 후보라면 탈락자 리스트에 추가해줌.
				if(candidate.cnt == minCnt) loserList.add(candidate.key);
			}//while

			ArrayList<int[]> tmp;
			for(int key : loserList) {
				tmp = voteList.get(key);
				if(tmp == null) continue;
				for(int i=0, size=tmp.size(); i<size; i++) {
					for(int num : tmp.get(i)) {
						if(voteCnt.containsKey(num) && voteCnt.get(num) > minCnt) {
							voteCnt.put(num, voteCnt.get(num) + 1);
							break;
						}//if
					}
				}
				voteCnt.remove(key); // 후보자 탈락
				voteList.remove(key);
			}//for
			
		}//while
		
	}//startCounting
	
	// 흐보자
	static class Candidate implements Comparable<Candidate>{
		int key; // 후보자 번호
		int cnt; // 득표수
		public Candidate(int key, int cnt) {
			this.key = key;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Candidate c) {
			return c.cnt - this.cnt; // 득표수 내림차순
		}
	}//Candidate

}//class
