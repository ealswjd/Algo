import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 오늘 하루 동안 팔린 책의 개수
		
		HashMap<String, Integer> books = new HashMap<>(); // 책 팔린 개수
		PriorityQueue<Book> pq = new PriorityQueue<>();   // 정렬
		while(N-->0) {
			String key = br.readLine(); // 책 제목
			books.put(key, books.getOrDefault(key, 0) + 1);
			pq.offer(new Book(key, books.get(key)));
		}//while
		br.close();
		
		System.out.print(pq.poll().name);
	}//main
	
	static class Book implements Comparable<Book> {
		String name; // 제목
		int cnt; // 팔린 개수
		public Book(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Book b) {
			if(this.cnt != b.cnt) return b.cnt - this.cnt; // 가장 많이 팔린 책
			return this.name.compareTo(b.name); // 사전 순으로 가장 앞서는 제목
		}
	}//Book

}//class