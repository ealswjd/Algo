package programmers;
// https://programmers.co.kr/learn/courses/30/lessons/62048
// Summer/Winter Coding(2019) > 멀쩡한 사각형
public class IntactSquare {	
	
	static long solution(int w, int h) {
		long answer = (long) w * h; // 사각형 전체 개수
		if(w == h) return answer - w; // 정사각형이면 전체 개수 - 한 변의 길이
		
		int min = Math.min(w, h); // 더 짧은 길이
		int gcd = 0; // 최대 공약수
		for(int i=1; i<=min; i++) {
			if(w % i == 0 && h % i == 0) gcd = i;
		}
		
		answer -= w + h - gcd;
		return answer;
	}

}//class
