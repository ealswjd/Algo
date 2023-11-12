import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1339
public class Main {
	static int N;
	static Map<Character, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 단어 개수
		map = new HashMap<>();
		
		int num;
		for(int i=0; i<N; i++) {
			char[] word = br.readLine().toCharArray();
			for(int j=0, len=word.length; j<len; j++) {
				num = (int) (map.getOrDefault(word[j], 0) + Math.pow(10, len-j-1));
				map.put(word[j], num);
			}//for
		}//for
		br.close();

		int sum = convertToNumber();
		System.out.print(sum);
	}//main		

	private static int convertToNumber() {
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for(char key : map.keySet()) {
			pq.offer(map.get(key));
		}//for
		
		int sum = 0;
		int num = 9;
		while(!pq.isEmpty()) {
			sum += pq.poll() * num--;
		}//while
		
		return sum;
	}//convertToNumber


}//class