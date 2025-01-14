import java.util.*;
import java.util.Map.Entry;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genreMap = new HashMap<>(); // 장르별 총 재생 횟수
        
		for(int i=0; i<genres.length; i++) { // 장르별 재생횟수 담기
			genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
		}

		// 재생횟수 내림차순 정렬
		List<Entry<String, Integer>> entryList = new LinkedList<>(genreMap.entrySet());
		entryList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        
		ArrayList<String> genreList = new ArrayList<>(); 
        
		for(Entry<String, Integer> entry : entryList) {			
			genreList.add(entry.getKey());
		}
		
		ArrayList<Music> result = new ArrayList<>(); // 장르별로 2개씩 담을 결과
        
		for(String g : genreList) {
			ArrayList<Music> list = new ArrayList<>(); // 장르에 해당하는 모든 노래
			for(int i=0; i<genres.length; i++) {
				if(g.equals(genres[i])) { 
					list.add(new Music(i, genres[i], plays[i])); 
				}				
			}
            
			Collections.sort(list, (o1, o2) -> o2.play - o1.play); // 재생 횟수 내림차순 정렬
			result.add(list.get(0)); 
            
			if(list.size() > 1) { 
				result.add(list.get(1)); 
			}
		}
		
		int[] answer = new int[result.size()];
        
		for(int i=0; i<answer.length; i++) {
			answer[i] = result.get(i).num; 
		}
		
		return answer; 
    }
}

class Music{
	String genre;
	int num;
	int play;
	
	public Music(int num, String genre, int play) {
		this.num = num;
		this.genre = genre;
		this.play = play;
	}
}